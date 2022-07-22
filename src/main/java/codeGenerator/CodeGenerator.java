package codeGenerator;

import static instructions.Cond.*;
import static instructions.Instruction.Link.*;
import static instructions.PredefinedLabel.Predefined.*;
import static instructions.Instruction.SetSymbol.*;

import instructions.*;
import node.FuncNode;
import node.MacroNode;
import node.ProgNode;
import node.exprNodes.*;
import node.statNodes.*;
import utils.Register;
import utils.SymbolTable;
import utils.Type;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Set;

public class CodeGenerator {

    /*
     *  CodeGenerator class is responsible for translating AST to assembly code
     */

    // This should contains all of the instructions before the "main".
    private static final DataSection dataSection = new DataSection();
    // This list contains all of the instructions of the "main" function.
    private static final List<Instruction> main = new ArrayList<>();
    // This list contains all of the instructions about other functions.
    private static final Set<Instruction> functions = new LinkedHashSet<>();


    // These are some commonly used registers.
    private static final Register r0 = new Register(0);
    private static final Register r1 = new Register(1);
    private static final Register r4 = new Register(4);
    private static final Register sp = new Register(Register.STACK_POINTER_REGISTER_NUMBER);
    private static final Register lr = new Register(Register.LINKER_REGISTER_NUMBER);
    private static final Register pc = new Register(Register.PROGRAM_COUNTER_REGISTER_NUMBER);

    // Symbol table
    private static SymbolTable<Type> topTable;
    private static SymbolTable<Type> currentTable;

    // Register that is currently available
    private static Register curReg = r4;

    // Global variables
    private static int currentFunctionStackSize = 0;
    private static boolean needToLoad = false;
    private static int labelCounter = 0;

    public CodeGenerator(SymbolTable<Type> topTable) {
        CodeGenerator.topTable = topTable;
        CodeGenerator.currentTable = topTable;
        main.add(new Label(".global main"));
    }

    /* ------------------------------------ Translate Prog & Macro & Func Node ------------------------------------ */

    public static void translateProgNode(ProgNode progNode) {

        for (FuncNode funcNode : progNode.getFuncNodes()) {
            funcNode.translate();
        }
        main.add(new Label("main"));

        calculateStackSize();
        main.add(new Push(lr));

        if (stackSize() != 0) {
            int stackSize = stackSize();
            while (stackSize >= currentTable.STACK_SIZE_LIMIT) {
                main.add(new Subtraction(sp, sp, currentTable.STACK_SIZE_LIMIT));
                stackSize -= currentTable.STACK_SIZE_LIMIT;
            }
            main.add(new Subtraction(sp, sp, stackSize));
        }

        for (MacroNode macroNode : progNode.getMacroNodes()) {
            macroNode.translate();
        }

        progNode.getStatNode().translate();

        if (stackSize() != 0) {
            int stackSize = stackSize();
            while (stackSize >= currentTable.STACK_SIZE_LIMIT) {
                main.add(new Add(sp, sp, currentTable.STACK_SIZE_LIMIT));
                stackSize -= currentTable.STACK_SIZE_LIMIT;
            }
            main.add(new Add(sp, sp, stackSize));
        }

        main.add(new Load(r0, 0));
        main.add(new Pop(pc));
        main.add(new Label(".ltorg"));

    }

    public static void translateMacroNode(MacroNode macroNode) {
        String ident = macroNode.getIdent();
        ExprNode assignRhsNode = macroNode.getAssignRhsNode();
        Type type = assignRhsNode.getType();

        if (currentTable.lookup(ident).isPair() && assignRhsNode instanceof NewpairNode) {
            main.add(new Load(r0, 8));
            main.add(new Branch(WITH_LINK, new Label("malloc")));
            main.add(new Move(curReg, r0));
        }

        needToLoad = true;
        assignRhsNode.translate();

        currentTable.reverselyAddAddress(ident, type);
        int address = currentTable.lookupAddress(ident);

        if (getBytesLength(type) == 1) {
            main.add(new StoreByte(curReg, sp, address));
        } else {
            main.add(new Store(curReg, sp, address));
        }
    }

    public static void translateFuncNode(FuncNode funcNode) {
        currentTable = getNextChildTable();

        List<String> arguments = funcNode.getParamList();
        calculateStackSize();
        currentTable.addStackSizeBy(Type.intType());
        currentTable.addAddress("ADDRESS", Type.intType());
        for (String argument : arguments) {
            currentTable.addAddress(argument, currentTable.lookup(argument));
        }

        main.add(new Label("f_" + funcNode.getIdent()));
        main.add(new Push(lr));

        currentTable = getNextChildTable();
        calculateStackSize();
        currentFunctionStackSize = stackSize();

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));

        funcNode.getStatNode().translate();

        currentTable = getParentTable();
        currentTable = getParentTable();

        main.add(new Label(".ltorg"));
    }

    /* ------------------------------------ Translate Statements Node ------------------------------------ */

    public static void translateDeclareNode(DeclareNode declareNode) {
        String ident = declareNode.getIdent();
        ExprNode assignRhsNode = declareNode.getAssignRhsNode();
        Type type = assignRhsNode.getType();

        if (currentTable.lookup(ident).isPair() && assignRhsNode instanceof NewpairNode) {
            main.add(new Load(r0, 8));
            main.add(new Branch(WITH_LINK, new Label("malloc")));
            main.add(new Move(curReg, r0));
        }

        needToLoad = true;
        assignRhsNode.translate();

        currentTable.reverselyAddAddress(ident, type);
        int address = currentTable.lookupAddress(ident);

        if (getBytesLength(type) == 1) {
            main.add(new StoreByte(curReg, sp, address));
        } else {
            main.add(new Store(curReg, sp, address));
        }

    }

    public static void translateAssignNode(AssignNode assignNode) {
        ExprNode assignLhs = assignNode.getAssignLhsNode();
        ExprNode assignRhs = assignNode.getAssignRhsNode();
        if (assignLhs instanceof IdentNode) {
            IdentNode identLhs = (IdentNode) assignLhs;
            Type type = identLhs.getType();
            String lhsName = identLhs.getName();
            int lhsAddress = currentTable.lookupAddress(lhsName);

            needToLoad = true;
            assignRhs.translate();

            if (getBytesLength(type) == 1) {
                main.add(new StoreByte(curReg, sp, lhsAddress));
            } else {
                main.add(new Store(curReg, sp, lhsAddress));
            }
        } else { // lhs is arrayElem or pairElem
            Type type = assignLhs.getType();

            needToLoad = true;
            assignRhs.translate();

            curReg = curReg.next();
            needToLoad = false;
            assignLhs.translate();
            curReg = curReg.pred();

            if (getBytesLength(type) == 1) {
                main.add(new StoreByte(curReg, curReg.next()));
            } else {
                main.add(new Store(curReg, curReg.next()));
            }
        }
    }

    public static void translateForNode(ForNode forNode) {
        Label label1 = getNextLabel();
        Label label2 = getNextLabel();
        Label label3 = getNextLabel();

        currentTable = getNextChildTable();
        calculateStackSize();

        Label breakLabel = null;
        if (forNode.hasBreakStat()) {
            breakLabel = getNextLabel();
            currentTable.setBreakLabel(breakLabel);
        }

        if (forNode.hasContinueStat()) {
            currentTable.setContinueLabel(label3);
        }

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        forNode.getVariableIdentify().translate();

        main.add(new Branch(WITH_LINK, label1));
        main.add(label2);

        currentTable = getNextChildTable();
        calculateStackSize();

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        forNode.getBody().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();

        main.add(label3);

        forNode.getVariableModify().translate();

        main.add(label1);

        forNode.getCondition().translate();
        main.add(new Compare(curReg, 0));
        main.add(new Branch(NE, label2));

        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        if (forNode.hasBreakStat()) {
            main.add(breakLabel);
        }

        currentTable = getParentTable();
    }

    public static void translateBreakNode(BreakNode breakNode) {
        int loopSize = currentTable.getLoopSize();
        if (loopSize != 0) main.add(new Add(sp, sp, loopSize));
        main.add(new Branch(currentTable.getBreakLabel()));
    }

    public static void translateContinueNode(ContinueNode continueNode) {
        int loopSize = currentTable.getLoopSizeWithCondition();
        if (loopSize != 0) main.add(new Add(sp, sp, loopSize));
        main.add(new Branch(currentTable.getContinueLabel()));
    }

    public static void translateBeginNode(BeginNode beginNode) {
        currentTable = getNextChildTable();
        calculateStackSize();

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        beginNode.getBody().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();
    }

    public static void translateIfElseNode(IfElseNode ifElseNode) {
        Label label1 = getNextLabel();
        Label label2 = getNextLabel();
        ifElseNode.getCondition().translate();
        main.add(new Compare(curReg, 0));
        main.add(new Branch(EQ, label1));

        currentTable = getNextChildTable();
        calculateStackSize();

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        ifElseNode.getBody1().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();

        main.add(new Branch(label2));
        main.add(label1);

        currentTable = getNextChildTable();
        calculateStackSize();

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        ifElseNode.getBody2().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();

        main.add(label2);
    }

    public static void translateIfNode(IfNode ifNode) {
        Label label1 = getNextLabel();
        ifNode.getCondition().translate();
        main.add(new Compare(curReg, 0));
        main.add(new Branch(EQ, label1));

        currentTable = getNextChildTable();
        calculateStackSize();

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        ifNode.getBody1().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();

        main.add(label1);
    }

    public static void translateWhileNode(WhileNode whileNode) {
        Label label1 = getNextLabel();
        Label label2 = getNextLabel();

        main.add(new Branch(label1));
        main.add(label2);

        currentTable = getNextChildTable();
        calculateStackSize();

        Label breakLabel = null;
        if (whileNode.hasBreakStat()) {
            breakLabel = getNextLabel();
            currentTable.setBreakLabel(breakLabel);
        }

        if (whileNode.hasContinueStat()) {
            currentTable.setContinueLabel(label1);
        }

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        whileNode.getBody().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();

        main.add(label1);
        whileNode.getCondition().translate();
        main.add(new Compare(curReg, 1));
        main.add(new Branch(EQ, label2));

        if (whileNode.hasBreakStat()) {
            main.add(breakLabel);
        }

    }

    public static void translateDoWhileNode(DoWhileNode doWhileNode) {
        Label label1 = getNextLabel();
        Label label2 = getNextLabel();
        main.add(label1);

        currentTable = getNextChildTable();
        calculateStackSize();

        Label breakLabel = null;
        if (doWhileNode.hasBreakStat()) {
            breakLabel = getNextLabel();
            currentTable.setBreakLabel(breakLabel);
        }

        if (doWhileNode.hasContinueStat()) {
            currentTable.setContinueLabel(label2);
        }

        if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
        currentFunctionStackSize += stackSize();
        doWhileNode.getBody().translate();
        currentFunctionStackSize -= stackSize();
        if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

        currentTable = getParentTable();

        main.add(label2);

        doWhileNode.getCondition().translate();
        main.add(new Compare(curReg, 1));
        main.add(new Branch(EQ, label1));

        if (doWhileNode.hasBreakStat()) {
            main.add(breakLabel);
        }
    }

    public static void translateSwitchNode(SwitchNode switchNode) {

        currentTable = getNextChildTable();

        Label breakLabel = null;
        if (switchNode.hasBreakStat()) {
            breakLabel = getNextLabel();
            currentTable.setBreakLabel(breakLabel);
        }

        Map<ExprNode, StatNode> cases = switchNode.getCases();
        ExprNode entity = switchNode.getEntity();

        entity.translate();

        List<Label> labelList = new ArrayList<>();
        int labelCounter = 0;

        for (Map.Entry<ExprNode, StatNode> entry : cases.entrySet()) {
            curReg = curReg.next();
            entry.getKey().translate();
            curReg = curReg.pred();

            Label label = getNextLabel();
            labelList.add(label);

            main.add(new Compare(curReg, curReg.next()));
            main.add(new Branch(EQ, label));
        }

        Label defaultLabel = null;
        if (switchNode.getDefaultStat() != null) {
            defaultLabel = getNextLabel();
            main.add(new Branch(defaultLabel));
        }

        for (Map.Entry<ExprNode, StatNode> entry : cases.entrySet()) {
            main.add(labelList.get(labelCounter++));

            currentTable = getNextChildTable();
            calculateStackSize();
            if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
            currentFunctionStackSize += stackSize();
            entry.getValue().translate();
            currentFunctionStackSize -= stackSize();
            if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

            currentTable = getParentTable();
        }

        if (switchNode.getDefaultStat() != null) {
            main.add(defaultLabel);

            currentTable = getNextChildTable();
            calculateStackSize();
            if (stackSize() != 0) main.add(new Subtraction(sp, sp, stackSize()));
            currentFunctionStackSize += stackSize();
            switchNode.getDefaultStat().translate();
            currentFunctionStackSize -= stackSize();
            if (stackSize() != 0) main.add(new Add(sp, sp, stackSize()));

            currentTable = getParentTable();
        }

        if (switchNode.hasBreakStat()) {
            main.add(breakLabel);
        }

        currentTable = getParentTable();
    }

    public static void translateFreeNode(FreeNode freeNode) {

        ExprNode exprNode = freeNode.getExprNode();

        needToLoad = true;
        exprNode.translate();

        main.add(new Move(r0, curReg));
        if (exprNode.getType().isPair()) {
            main.add(new Branch(WITH_LINK, getLabel(P_FREE_PAIR)));
        } else { // exprNode is array
            main.add(new Branch(WITH_LINK, getLabel(P_FREE_ARRAY)));
        }
    }

    public static void translatePrintNode(PrintNode printNode) {
        ExprNode exprNode = printNode.getExprNode();
        Type type = exprNode.getType();

        needToLoad = true;
        exprNode.translate();

        main.add(new Move(r0, curReg));
        if (type.equals(Type.boolType())) {
            main.add(new Branch(WITH_LINK, getLabel(P_PRINT_BOOL)));
        } else if (type.equals(Type.intType())) {
            main.add(new Branch(WITH_LINK, getLabel(P_PRINT_INT)));
        } else if (type.equals(Type.charType())) {
            main.add(new Branch(WITH_LINK, new Label("putchar")));
        } else if (type.equals(Type.stringType()) || type.isArray() && type.getType1().equals(Type.charType())) { // if (type.equals(Type.stringType()))
            main.add(new Branch(WITH_LINK, getLabel(P_PRINT_STRING)));
        } else { // isArray or isPair
            main.add(new Branch(WITH_LINK, getLabel(P_PRINT_REFERENCE)));
        }

        if (printNode.getLineMode() == PrintNode.LINE.WITH_LINE) {
            main.add(new Branch(WITH_LINK, getLabel(P_PRINT_LN)));
        }
    }

    public static void translateExitNode(ExitNode exitNode) {
        exitNode.getExprNode().translate();
        main.add(new Move(r0, curReg));
        main.add(new Branch(WITH_LINK, new Label("exit")));
    }

    public static void translateReturnNode(ReturnNode returnNode) {
        returnNode.getExprNode().translate();
        main.add(new Move(r0, curReg));
        if (currentFunctionStackSize != 0) {
            main.add(new Add(sp, sp, currentFunctionStackSize));
        }
        main.add(new Pop(pc));
    }

    public static void translateReadNode(ReadNode readNode) {
        ExprNode exprNode = readNode.getExprNode();
        Type type = exprNode.getType();

        if (exprNode instanceof IdentNode) {
            String name = ((IdentNode) exprNode).getName();
            int address = currentTable.lookupAddress(name);
            main.add(new Add(curReg, sp, address));
        } else { // exprNode is array or pair
            needToLoad = false;
            exprNode.translate();
        }

        main.add(new Move(r0, curReg));
        if (type.equals(Type.intType())) {
            Label readIntLabel = new PredefinedLabel(P_READ_INT, dataSection);
            functions.add(readIntLabel);
            main.add(new Branch(WITH_LINK, readIntLabel));
        } else { // is char type
            Label readCharLabel = new PredefinedLabel(P_READ_CHAR, dataSection);
            functions.add(readCharLabel);
            main.add(new Branch(WITH_LINK, readCharLabel));
        }
    }

    public static void translateCallNode(CallNode callNode) {
        List<ExprNode> arguments = callNode.getArguments();
        int spOffsetValue = 0;
        for (int i = arguments.size() - 1; i >= 0 ; i--) {
            arguments.get(i).translate();
            Type type = arguments.get(i).getType();
            if (type.equals(Type.boolType()) || type.equals(Type.charType())) {
                main.add(new StoreByte(curReg, sp, -1).preOffset());
                currentTable.addOffSetValueBy(1);
                spOffsetValue ++;
            } else {
                main.add(new Store(curReg, sp, -4).preOffset());
                currentTable.addOffSetValueBy(4);
                spOffsetValue += 4;
            }
        }
        Label funcLabel = new Label("f_" + callNode.getIdent());
        main.add(new Branch(WITH_LINK, funcLabel));
        currentTable.addOffSetValueBy(-spOffsetValue);
        if (spOffsetValue != 0) {
            main.add(new Add(sp, sp, spOffsetValue));
        }
        main.add(new Move(curReg, r0));
    }

    /* ------------------------------------ Translate Expression Node ------------------------------------ */

    public static void translateIdentNode(IdentNode identNode) {
        Type type = identNode.getType();
        String name = identNode.getName();
        int address = currentTable.lookupAddress(name);

        if (getBytesLength(type) == 1) {
            main.add(new LoadByte(curReg, sp, address));
        } else {
            main.add(new Load(curReg, sp, address));
        }
    }

    public static void translateIntLiterNode(IntLiterNode intLiterNode) {
        main.add(new Load(curReg, intLiterNode.getValue()));
    }

    public static void translateBoolLiterNode(BoolLiterNode boolLiterNode) {
        if (boolLiterNode.getBool()) {
            main.add(new Move(curReg, 1));
        } else {
            main.add(new Move(curReg, 0));
        }
    }

    public static void translateCharLiterNode(CharLiterNode charLiterNode) {
        main.add(new Move(curReg, charLiterNode.getCharacter()));
    }

    public static void translateStringLiterNode(StringLiterNode stringLiterNode) {
        String string = stringLiterNode.getValue();
        dataSection.addMessageLabels(new MessageLabel(string));
        Label label = dataSection.getMessageLabel(string);
        main.add(new Load(curReg, label));
    }

    public static void translateUnaryOperNode(UnaryOperNode unaryOperNode) {
        ExprNode exprNode = unaryOperNode.getExprNode();
        UnaryOperNode.UnaryOperator operator = unaryOperNode.getOper();
        switch (operator) {
            case ORD:
            case CHR:
                exprNode.translate();
                break;
            case NOT:
            case BITNOT:
                exprNode.translate();
                main.add(new ExclusiveOr(curReg, curReg, 1));
                break;
            case MINUS:
                exprNode.translate();
                main.add(new ReverseSubtraction(curReg, curReg, 0));
                main.add(new Branch(WITH_LINK, VS, getLabel(P_THROW_OVERFLOW_ERROR)));
                break;
            case LEN:
                exprNode.translate();
                main.add(new Load(curReg, curReg));
        }
    }

    public static void translateBinaryOperNode(BinaryOperNode binaryOperNode) {
        BinaryOperNode.BinaryOperator oper = binaryOperNode.getOper();
        ExprNode lhs = binaryOperNode.getLhs();
        ExprNode rhs = binaryOperNode.getRhs();
        Register reg1, reg2;

        if (lhs.getWeight() >= rhs.getWeight()) {
            reg1 = curReg;
            reg2 = curReg.next();
            lhs.translate();
            curReg = curReg.next();
            rhs.translate();
            curReg = curReg.pred();
        } else {
            reg1 = curReg.next();
            reg2 = curReg;
            rhs.translate();
            curReg = curReg.next();
            lhs.translate();
            curReg = curReg.pred();
        }

        switch (oper) {
            case AND:
            case BITAND:
                main.add(new And(curReg, reg1, reg2));
                break;
            case OR:
            case BITOR:
                main.add(new Or(curReg, reg1, reg2));
                break;
            case GT:
                main.add(new Compare(reg1, reg2));
                main.add(new Move(reg1, 1, GT));
                main.add(new Move(reg1, 0, LE));
                break;
            case GTE:
                main.add(new Compare(reg1, reg2));
                main.add(new Move(reg1, 1, GE));
                main.add(new Move(reg1, 0, LT));
                break;
            case LT:
                main.add(new Compare(reg1, reg2));
                main.add(new Move(reg1, 1, LT));
                main.add(new Move(reg1, 0, GE));
                break;
            case LTE:
                main.add(new Compare(reg1, reg2));
                main.add(new Move(reg1, 1, LE));
                main.add(new Move(reg1, 0, GT));
                break;
            case EQ:
                main.add(new Compare(reg1, reg2));
                main.add(new Move(reg1, 1, EQ));
                main.add(new Move(reg1, 0, NE));
                break;
            case NEQ:
                main.add(new Compare(reg1, reg2));
                main.add(new Move(reg1, 1, NE));
                main.add(new Move(reg1, 0, EQ));
                break;
            case PLUS:
                main.add(new Add(SET, curReg, reg1, reg2));
                main.add(new Branch(WITH_LINK, VS, getLabel(P_THROW_OVERFLOW_ERROR)));
                break;
            case MINUS:
                main.add(new Subtraction(curReg, reg1, reg2));
                main.add(new Branch(WITH_LINK, VS, getLabel(P_THROW_OVERFLOW_ERROR)));
                break;
            case MUL:
                main.add(new Multiply(curReg, curReg.next(), reg1, reg2));
                main.add(new Compare(curReg.next(), curReg, Instruction.Shift.ASR, 31));
                main.add(new Branch(WITH_LINK, NE, getLabel(P_THROW_OVERFLOW_ERROR)));
                break;
            case DIV:
                main.add(new Move(r0, reg1));
                main.add(new Move(r1, reg2));
                main.add(new Branch(WITH_LINK, getLabel(P_CHECK_DIVIDE_BY_ZERO)));
                main.add(new Branch(WITH_LINK, new Label("__aeabi_idiv")));
                main.add(new Move(curReg, r0));
                break;
            case MOD:
                main.add(new Move(r0, reg1));
                main.add(new Move(r1, reg2));
                main.add(new Branch(WITH_LINK, getLabel(P_CHECK_DIVIDE_BY_ZERO)));
                main.add(new Branch(WITH_LINK, new Label("__aeabi_idivmod")));
                main.add(new Move(curReg, r1));
                break;
        }
    }

    public static void translateNewpairNode(NewpairNode newpairNode) {
        curReg = curReg.next();

        ExprNode leftExprNode = newpairNode.getLeftExpr();
        int leftByteLength = getBytesLength(leftExprNode.getType());
        ExprNode rightExprNode = newpairNode.getRightExpr();
        int rightByteLength = getBytesLength(rightExprNode.getType());

        leftExprNode.translate();
        main.add(new Load(r0, leftByteLength));
        main.add(new Branch(WITH_LINK, new Label("malloc")));
        if (leftByteLength == 1) {
            main.add(new StoreByte(curReg,r0));
        } else {
            main.add(new Store(curReg, r0));
        }
        main.add(new Store(r0, curReg.pred()));

        rightExprNode.translate();
        main.add(new Load(r0, rightByteLength));
        main.add(new Branch(WITH_LINK, new Label("malloc")));
        if (rightByteLength == 1) {
            main.add(new StoreByte(curReg,r0));
        } else {
            main.add(new Store(curReg, r0));
        }
        main.add(new Store(r0, curReg.pred(), 4));

        curReg = curReg.pred();
    }

    public static void translatePairLiterNode(PairLiterNode pairLiterNode) {
        main.add(new Load(curReg, 0));
    }

    public static void translatePairElemNode(PairElemNode pairElemNode) {
        int offSet = pairElemNode.isFirst() ? 0 : 4;
        int address = currentTable.lookupAddress(pairElemNode.getIdent());
        main.add(new Load(curReg, sp, address));
        main.add(new Move(r0, curReg));
        main.add(new Branch(WITH_LINK, getLabel(P_CHECK_NULL_POINTER)));
        main.add(new Load(curReg, curReg, offSet));
        if (needToLoad) {
            main.add(new Load(curReg, curReg));
        }
    }

    public static void translateArrayLiterNode(ArrayLiterNode arrayLiterNode) {
        int size = arrayLiterNode.getSize();
        Type arrayType = arrayLiterNode.getType();

        int bytesLength = getBytesLength(arrayType.getType1());

        main.add(new Load(r0, bytesLength * size + 4));
        main.add(new Branch(WITH_LINK, new Label("malloc")));
        main.add(new Move(curReg, r0));

        int arrayIndex = 4;

        for (ExprNode exprNode : arrayLiterNode.getExpressions()) {
            curReg = curReg.next();
            exprNode.translate();
            curReg = curReg.pred();
            if (bytesLength == 1) {
                main.add(new StoreByte(curReg.next(), curReg, arrayIndex));
            } else {
                main.add(new Store(curReg.next(), curReg, arrayIndex));
            }
            arrayIndex += bytesLength;
        }
        main.add(new Load(curReg.next(), size));
        main.add(new Store(curReg.next(), curReg));
    }

    public static void translateArrayElemNode(ArrayElemNode arrayElemNode) {
        int address = currentTable.lookupAddress(arrayElemNode.getIdent());
        main.add(new Add(curReg, sp, address));
        for (int i = 0; i < arrayElemNode.getIndex().size(); i++) {
            curReg = curReg.next();
            arrayElemNode.getIndex().get(i).translate();
            curReg = curReg.pred();
            main.add(new Load(curReg, curReg));
            main.add(new Move(r0, curReg.next()));
            main.add(new Move(r1, curReg));
            main.add(new Branch(WITH_LINK, getLabel(P_CHECK_ARRAY_BOUNDS)));
            main.add(new Add(curReg, curReg, 4));
            if (getBytesLength(arrayElemNode.getType()) == 1) {
                main.add(new Add(curReg, curReg, curReg.next()));
            } else {
                main.add(new Add(curReg, curReg, curReg.next(), Instruction.Shift.LSL, 2));
            }
        }
        if (needToLoad) {
            if (getBytesLength(arrayElemNode.getType()) == 1) {
                main.add(new LoadByte(curReg, curReg));
            } else {
                main.add(new Load(curReg, curReg));
            }
        }
    }

    /* ------------------------------------ Helper Functions ------------------------------------ */

    /* Create a new file and write all of the instructions to it. */
    public void createAndWriteFile(String name) throws IOException {

        // create a new file ending in ".s".
        File file = new File(name + ".s");

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        // use buffer writer to write every instructions to the new file.
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(dataSection.toString());

        bw.write(".text\n\n");

        for (Instruction instruction : main) {
            bw.write(instruction.toString());
        }
        for (Instruction instruction : functions) {
            bw.write(instruction.toString());
        }
        bw.close();
    }

    /* Find the bytes length of the given array type */
    private static int getBytesLength(Type type) {
        if (type.equals(Type.charType()) || type.equals(Type.boolType())) {
            return 1;
        } else {
            return 4;
        }
    }

    /* Get predefined labels */
    private static Label getLabel(PredefinedLabel.Predefined predefined) {
        Label label = new PredefinedLabel(predefined, dataSection);
        functions.add(label);
        switch (predefined) {
            case P_PRINT_STRING:
            case P_PRINT_INT:
            case P_PRINT_BOOL:
            case P_PRINT_REFERENCE:
                break;
            case P_THROW_OVERFLOW_ERROR:
            case P_CHECK_DIVIDE_BY_ZERO:
            case P_CHECK_ARRAY_BOUNDS:
            case P_CHECK_NULL_POINTER:
            case P_FREE_PAIR:
            case P_FREE_ARRAY:
                getLabel(P_THROW_RUNTIME_ERROR);
                break;
            case P_THROW_RUNTIME_ERROR:
                functions.add(getLabel(P_PRINT_STRING));
                break;
        }
        return label;
    }

    private static Label getNextLabel() {
        return new Label("L" + labelCounter++);
    }

    private static SymbolTable<Type> getNextChildTable() {
        return currentTable.getChildren().get(0);
    }

    private static SymbolTable<Type> getParentTable() {
        currentTable = currentTable.getParentTable();
        currentTable.getChildren().remove(0);
        return currentTable;
    }

    private static int stackSize() {
        return currentTable.getStackSize();
    }

    /* Find the maximum size required in this scope without adding up stack pointer */
    private static void calculateStackSize() {
        for (Map.Entry<String, Type> entry : currentTable.getStringTypeMap().entrySet()) {
            currentTable.addStackSizeBy(entry.getValue());
        }
    }
}
