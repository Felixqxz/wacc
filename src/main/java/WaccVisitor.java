import node.exprNodes.*;
import node.*;
import node.statNodes.*;
import node.statNodes.PrintNode.LINE;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaccVisitor extends WaccParserBaseVisitor<Node>{

  /* utils.ErrorHandler to report error and print message */
  private final ErrorHandler errorHandler = new ErrorHandler();

  /* Top level symbol table */
  private final SymbolTable<Type> topTable = new SymbolTable<>();

  /* A symbol table to represent the current scope the visitor is in */
  private SymbolTable<Type> currentTable = topTable;

  private final Map<String, ArrayBoundAnalyser> arrayAnalyserMap = new HashMap<>();

  /* Global boolean value to enable self-recursion */
  private boolean isDefiningFunctionBody = false;

  private boolean inLhs = false;
  private final static String DEFAULT_FUNC_NAME = "MAIN";
  private static String currentFuncIdent = DEFAULT_FUNC_NAME;

  /* ------------------- Override each visit methods -------------------*/

  @Override
  public Node visitProg(WaccParser.ProgContext ctx) {
    System.out.println("Compiling ...  ");
    ProgNode progNode = new ProgNode();
    for (WaccParser.FuncContext func : ctx.func()) {
      Type declareType = getType(func.type());
      String ident = func.ident().getText();
      Function newFunction = new Function(ident, declareType);
      checkIfFunctionRedefined(ctx, ident);
      topTable.addFunction(ident, newFunction);
      if (func.param_list() != null) {
        for (WaccParser.ParamContext paramContext : func.param_list().param()) {
          newFunction.addParam(getType(paramContext.type()));
        }
      }
    }
    for (WaccParser.MacroContext macro : ctx.macro()) {
      MacroNode macroNode = (MacroNode) visit(macro);
      progNode.addMacroNode(macroNode);
    }
    for (WaccParser.FuncContext func : ctx.func()) {
      FuncNode newFuncNode = (FuncNode) visitFunc(func);
      progNode.addFuncNode(newFuncNode);
    }
    isDefiningFunctionBody = false;
    StatNode statNode = (StatNode) visit(ctx.stat());
    progNode.setStatNode(statNode);
    return progNode;
  }

  @Override
  public Node visitMacro(WaccParser.MacroContext ctx) {
    checkIfVariableRedefined(ctx, ctx.ident().getText());

    Type lhsType = getType(ctx.type());

    ExprNode rhsNode = (ExprNode) visit(ctx.assign_rhs());
    Type rhsType = rhsNode.getType();

    checkIfTypeMatch(ctx.assign_rhs(), lhsType, rhsType);
    currentTable.addVariable(ctx.ident().getText(), lhsType);

    String ident = ctx.ident().getText();

    return new MacroNode(ident, rhsNode);
  }

  @Override
  public Node visitFunc(WaccParser.FuncContext ctx) {

    openScope();

    Type declareType = getType(ctx.type());
    String ident = ctx.ident().getText();
    currentFuncIdent = ident;

    List<String> paramList = new ArrayList<>();
    if (ctx.param_list() != null) {
      for (WaccParser.ParamContext paramCtx : ctx.param_list().param()) {
        String paramName = paramCtx.ident().getText();
        Type paramType = getType(paramCtx.type());
        paramList.add(paramName);
        currentTable.addVariable(paramName, paramType);
      }
    }

    openScope();

    isDefiningFunctionBody = true;
    StatNode statNode = (StatNode) visit(ctx.stat());
    Type returnType = statNode.getReturnType();
    boolean hasExitStat = statNode.hasExitStat();
    boolean guaranteeReturn = statNode.guaranteeReturn();
    isDefiningFunctionBody = false;

    closeScope();

    if (!guaranteeReturn && !hasExitStat) {
      errorHandler.missingReturn(ident);
    }
    if (returnType != null) {
      checkIfTypeMatch(ctx, declareType, returnType);
    }

    closeScope();

    return new FuncNode(declareType, ident, statNode, paramList);
  }

  @Override
  public Node visitSkipStat(WaccParser.SkipStatContext ctx) {
    return new SkipNode();
  }

  // todo:
  private void addChildAnalyser(ArrayBoundAnalyser analyser, ArrayBoundAnalyser curr, List<Integer> exprList) {
    int index = exprList.remove(0);
    curr = curr.get(index);
    if (exprList.size() > 0) {
      addChildAnalyser(analyser, curr, exprList);
    } else {
      analyser.addChild(curr);
    }
  }

  // todo:
  private void setArrayBoundAnalyser(WaccParser.DeclareStatContext ctx, ExprNode rhsNode) {
    if (rhsNode instanceof ArrayLiterNode) {
      ArrayLiterNode arrayLiterNode = (ArrayLiterNode) rhsNode;
      ArrayBoundAnalyser analyser = new ArrayBoundAnalyser(arrayLiterNode.getSize());
      if (arrayLiterNode.getType().getType1().isBaseType()) {
        arrayAnalyserMap.put(ctx.ident().getText(), analyser);
      } else { // when the array is nested
        for (ExprNode exprNode : arrayLiterNode.getExpressions()) {
          if (exprNode instanceof IdentNode) {
            IdentNode identNode = (IdentNode) exprNode;
            ArrayBoundAnalyser childAnalyser = arrayAnalyserMap.get(identNode.getName());
            analyser.addChild(childAnalyser);
          } else { // instanceof array_elemNode
            ArrayElemNode arrayElemNode = (ArrayElemNode) exprNode;
            ArrayBoundAnalyser newAnalyser = arrayAnalyserMap.get(arrayElemNode.getIdent());
            List<Integer> exprList = new ArrayList<>();
            for (ExprNode expr : arrayElemNode.getIndex()) {
              exprList.add(((IntLiterNode) expr).getValue());
            }
            addChildAnalyser(analyser, newAnalyser, exprList);
          }
        }
        arrayAnalyserMap.put(ctx.ident().getText(), analyser);
      }
    }
  }

  @Override
  public Node visitDeclareStat(WaccParser.DeclareStatContext ctx) {
    checkIfVariableRedefined(ctx, ctx.ident().getText());

    Type lhsType = getType(ctx.type());

    ExprNode rhsNode = (ExprNode) visit(ctx.assign_rhs());
    Type rhsType = rhsNode.getType();

    // todo:
    setArrayBoundAnalyser(ctx, rhsNode); //

    checkIfTypeMatch(ctx.assign_rhs(), lhsType, rhsType);
    currentTable.addVariable(ctx.ident().getText(), lhsType);

    String ident = ctx.ident().getText();

    return new DeclareNode(ident, rhsNode);
  }

  @Override
  public Node visitAssignStat(WaccParser.AssignStatContext ctx) {
    inLhs = true;
    ExprNode lhsNode = (ExprNode) visit(ctx.assign_lhs());
    inLhs = false;
    Type lhsType = lhsNode.getType();

    ExprNode rhsNode = (ExprNode) visit(ctx.assign_rhs());
    Type rhsType = rhsNode.getType();

    // Check if assignment to a string type, element access is not permitted for strings.
    checkIfTypeMatch(ctx.assign_rhs(), lhsType, rhsType);

    return new AssignNode(lhsNode, rhsNode);
  }

  @Override
  public Node visitReadStat(WaccParser.ReadStatContext ctx) {
    ExprNode lhsNode = (ExprNode) visit(ctx.assign_lhs());
    Type lhs = lhsNode.getType();
    // The read statement can only handle character or integer input
    if (!lhs.equals(Type.intType()) && !lhs.equals(Type.charType())) {
      errorHandler.incompatibleType(ctx, lhs);
    }

    return new ReadNode(lhsNode);
  }

  @Override
  public Node visitFreeStat(WaccParser.FreeStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type exprType = exprNode.getType();
    // The statement given must be an expression that of Pair type or Array type.
    if (!exprType.isArray() && !exprType.isPair()) {
      errorHandler.incompatibleType(ctx, exprType);
    }

    return new FreeNode(exprNode);
  }

  @Override
  public Node visitReturnStat(WaccParser.ReturnStatContext ctx) {
    checkIfGlobalReturn(ctx);
    return new ReturnNode((ExprNode) visit(ctx.expr()));
  }

  @Override
  public Node visitExitStat(WaccParser.ExitStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type exprType = exprNode.getType();
    checkIfTypeMatch(ctx.getChild(1), Type.intType(), exprType);

    return new ExitNode(exprNode);
  }

  @Override
  public Node visitPrintStat(WaccParser.PrintStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    return new PrintNode(exprNode);
  }

  @Override
  public Node visitPrintlnStat(WaccParser.PrintlnStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    return new PrintNode(exprNode, LINE.WITH_LINE);
  }

  @Override
  public Node visitForStat(WaccParser.ForStatContext ctx) {
    StatNode variableIdentifyNode = null;
    ExprNode conditionNode = null;
    StatNode variableModifyNode = null;
    openScope();
    currentTable.setAsLoop();
    currentTable.setAsForLoop();

    if (ctx.stat(0) != null) {
      checkIfIsDeclareOrAssign(ctx.stat(0));
      variableIdentifyNode = (StatNode) visit(ctx.stat(0));
    }
    if (ctx.expr() != null) {
      checkIfIsCondition(ctx.expr());
      conditionNode = (ExprNode) visit(ctx.expr());
    }
    if (ctx.stat(1) != null) {
      checkIfIsAssign(ctx.stat(1));
      variableModifyNode = (StatNode) visit(ctx.stat(1));
    }

    openScope();
    StatNode bodyNode = (StatNode) visit(ctx.stat(2));
    closeScope();

    closeScope();

    return new ForNode(variableIdentifyNode, conditionNode, variableModifyNode, bodyNode);
  }

  @Override
  public Node visitIfElseStat(WaccParser.IfElseStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type condition = exprNode.getType();
    checkIfTypeMatch(ctx.expr(), Type.boolType(), condition);
    // Create a new symbol table and now we are in a new scope.
    openScope();
    // By WaccParser, ctx.getChild(3) refers to the first sub-statement in the rule.
    StatNode statNode1 = (StatNode) visit(ctx.getChild(3));
    // Exit the scope.
    closeScope();
    // Create a new symbol table and now we are in a new scope.
    openScope();
    // By WaccParser, ctx.getChild(5) refers to the second sub-statement in the rule.
    StatNode statNode2 = (StatNode) visit(ctx.getChild(5));
    // Exit the scope.
    closeScope();

    return new IfElseNode(exprNode, statNode1, statNode2);
  }

  @Override
  public Node visitBreakStat(WaccParser.BreakStatContext ctx) {
    return new BreakNode();
  }

  @Override
  public Node visitContinueStat(WaccParser.ContinueStatContext ctx) {
    return new ContinueNode();
  }

  @Override
  public Node visitIfStat(WaccParser.IfStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type condition = exprNode.getType();
    checkIfTypeMatch(ctx.expr(), Type.boolType(), condition);
    // Create a new symbol table and now we are in a new scope.
    openScope();
    // By WaccParser, ctx.getChild(3) refers to the first sub-statement in the rule.
    StatNode statNode = (StatNode) visit(ctx.getChild(3));
    // Exit the scope.
    closeScope();

    return new IfNode(exprNode, statNode);
  }

  @Override
  public Node visitWhileStat(WaccParser.WhileStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type condition = exprNode.getType();
    checkIfTypeMatch(ctx.expr(), Type.boolType(), condition);
    // Create a new symbol table and now we are in a new scope.
    openScope();
    currentTable.setAsLoop();
    StatNode statNode = (StatNode) visit(ctx.stat());
    // Exit the scope.
    closeScope();

    return new WhileNode(exprNode, statNode);
  }

  @Override
  public Node visitDoWhileStat(WaccParser.DoWhileStatContext ctx) {
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type condition = exprNode.getType();
    checkIfTypeMatch(ctx.expr(), Type.boolType(), condition);
    // Create a new symbol table and now we are in a new scope.
    openScope();
    currentTable.setAsLoop();
    StatNode statNode = (StatNode) visit(ctx.stat());
    // Exit the scope.
    closeScope();

    return new DoWhileNode(statNode, exprNode);
  }

  @Override
  public Node visitSwitchStat(WaccParser.SwitchStatContext ctx) {
    openScope();
    currentTable.setAsLoop();

    ExprNode entity = (ExprNode) visit(ctx.expr());
    SwitchNode switchNode = new SwitchNode(entity);
    Type entityType = entity.getType();
    for (WaccParser.Case_exprContext caseExpr : ctx.case_expr()) {
      ExprNode expr = (ExprNode) visit(caseExpr.expr());
      checkIfTypeMatch(caseExpr, entityType, expr.getType());
      StatNode stat = null;
      if (caseExpr.stat() != null) {
        openScope();
        stat = (StatNode) visit(caseExpr.stat());
        closeScope();
      }
      switchNode.addCase(expr, stat);
    }
    StatNode defaultStat = null;
    if (ctx.DEFAULT() != null) {
      openScope();
      defaultStat = (StatNode) visit(ctx.stat());
      closeScope();
    }
    switchNode.setDefaultStat(defaultStat);

    closeScope();
    return switchNode;
  }

  @Override
  public Node visitBeginStat(WaccParser.BeginStatContext ctx) {
    // Create a new symbol table and now we are in a new scope.
    openScope();
    StatNode statNode = (StatNode) visit(ctx.stat());
    // Exit the scope.
    closeScope();

    return new BeginNode(statNode);
  }

  @Override
  public Node visitSeqStat(WaccParser.SeqStatContext ctx) {
    StatNode firstStat = (StatNode) visit(ctx.stat(0));
    StatNode secondStat = (StatNode) visit(ctx.stat(1));

    if (ctx.stat(0) instanceof WaccParser.ReturnStatContext) {
      errorHandler.missingReturn(currentFuncIdent);
    }

    return new SeqNode(firstStat, secondStat);
  }

  @Override
  public Node visitIdentLhs(WaccParser.IdentLhsContext ctx) {
    return visit(ctx.ident());
  }

  @Override
  public Node visitArrayelemLhs(WaccParser.ArrayelemLhsContext ctx) {
    return visit(ctx.array_elem());
  }

  @Override
  public Node visitPairelemLhs(WaccParser.PairelemLhsContext ctx) {
    return visit(ctx.pair_elem());
  }

  @Override
  public Node visitExprRhs(WaccParser.ExprRhsContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Node visitArrayliterRhs(WaccParser.ArrayliterRhsContext ctx) {
    return visit(ctx.array_liter());
  }

  @Override
  public Node visitNewpairRhs(WaccParser.NewpairRhsContext ctx) {
    ExprNode leftExprNode = (ExprNode) visit(ctx.expr(0));
    ExprNode rightExprNode = (ExprNode) visit(ctx.expr(1));
    return new NewpairNode(leftExprNode, rightExprNode);
  }

  @Override
  public Node visitPairelemRhs(WaccParser.PairelemRhsContext ctx) {
    return visit(ctx.pair_elem());
  }

  @Override
  public Node visitCallRhs(WaccParser.CallRhsContext ctx) {
    String ident = ctx.ident().getText();
    if (!isDefiningFunctionBody) {
      checkIfFunctionUndefined(ctx, ident);
    }
    Function function = topTable.getFunctionMap().get(ident);

    int expected = function.getParamNumber();
    int actual = ctx.arg_list() == null ? 0 : ctx.arg_list().expr().size();
    checkArgsNumber(ctx, ident, expected, actual);

    List<ExprNode> arguments = new ArrayList<>();
    // Check the types of parameters of a function call.
    for (int i = 0; i < expected; i++) {
      ExprNode exprNode = (ExprNode) visit(ctx.arg_list().expr(i));
      arguments.add(exprNode);
      Type argType = exprNode.getType();
      checkIfTypeMatch(ctx.arg_list().expr(i), function.getParamType(i), argType);
    }
    CallNode callNode = new CallNode(ident, arguments);
    callNode.setType(function.getReturnType());
    return callNode;
  }

  @Override
  public Node visitFstPairElem(WaccParser.FstPairElemContext ctx) {
    if (!inLhs) {
      checkNullPair(ctx);
    }
    String ident = ctx.expr().getText();
    checkIfVariableUndefined(ctx, ident);
    PairElemNode fstNode = new PairElemNode(true, ident);
    fstNode.setType(currentTable.lookup(ident).getType1());
    return fstNode;
  }

  @Override
  public Node visitSndPairElem(WaccParser.SndPairElemContext ctx) {
    if (!inLhs) {
      checkNullPair(ctx);
    }
    String ident = ctx.expr().getText();
    checkIfVariableUndefined(ctx, ident);
    PairElemNode fstNode = new PairElemNode(false, ident);
    fstNode.setType(currentTable.lookup(ident).getType2());
    return fstNode;
  }

  @Override
  public Node visitIntlitExpr(WaccParser.IntlitExprContext ctx) {
    int value;
    try {
      value = Integer.parseInt(ctx.getText());
    } catch (NumberFormatException e) {
      errorHandler.integerOverflow();
      return null;
    }
    IntLiterNode intLiterNode = new IntLiterNode(value);
    intLiterNode.setType(Type.intType());
    return intLiterNode;
  }

  @Override
  public Node visitBoollitExpr(WaccParser.BoollitExprContext ctx) {
    String content = ctx.getText();
    boolean value = true;
    if (content.equals("false")) {
      value = false;
    }
    BoolLiterNode boolLiterNode = new BoolLiterNode(value);
    boolLiterNode.setType(Type.boolType());
    return boolLiterNode;
  }

  @Override
  public Node visitCharlitExpr(WaccParser.CharlitExprContext ctx) {
    char c = ctx.getText().charAt(1);
    if (ctx.getText().contains("\\")) {
      c = ctx.getText().charAt(2);
    }
    if (c > ErrorHandler.CHARACTER_MAX_VALUE) {
      errorHandler.characterOverflow();
      return null;
    }
    CharLiterNode charLiterNode = new CharLiterNode(c);
    charLiterNode.setType(Type.charType());
    return charLiterNode;
  }

  @Override
  public Node visitStringlitExpr(WaccParser.StringlitExprContext ctx) {
    StringLiterNode stringLiterNode = new StringLiterNode(ctx.getText());
    stringLiterNode.setType(Type.stringType());
    return stringLiterNode;
  }

  @Override
  public Node visitPairlitExpr(WaccParser.PairlitExprContext ctx) {
    return new PairLiterNode();
  }

  @Override
  public Node visitIdentExpr(WaccParser.IdentExprContext ctx) {
    return visit(ctx.ident());
  }

  @Override
  public Node visitArrayelemExpr(WaccParser.ArrayelemExprContext ctx) {
    return visit(ctx.array_elem());
  }

  @Override
  public Node visitUnaryOperExpr(WaccParser.UnaryOperExprContext ctx) {
    int operatorType = ((TerminalNode) ctx.getChild(0)).getSymbol().getType();
    ExprNode exprNode = (ExprNode) visit(ctx.expr());
    Type exprType = exprNode.getType();

    switch (operatorType) {
      case WaccParser.NOT:
        checkIfTypeMatch(ctx, Type.boolType(), exprType);
        return new UnaryOperNode(Type.boolType(), exprNode, UnaryOperNode.UnaryOperator.NOT);
      case WaccParser.MINUS:
        checkIfTypeMatch(ctx, Type.intType(), exprType);
        return new UnaryOperNode(Type.intType(), exprNode, UnaryOperNode.UnaryOperator.MINUS);
      case WaccParser.LEN:
        checkIfTypeMismatchWithArray(ctx, exprType);
        return new UnaryOperNode(Type.intType(), exprNode, UnaryOperNode.UnaryOperator.LEN);
      case WaccParser.ORD:
        checkIfTypeMatch(ctx, Type.charType(), exprType);
        return new UnaryOperNode(Type.intType(), exprNode, UnaryOperNode.UnaryOperator.ORD);
      case WaccParser.CHR:
        checkIfTypeMatch(ctx, Type.intType(), exprType);
        return new UnaryOperNode(Type.charType(), exprNode, UnaryOperNode.UnaryOperator.CHR);
      case WaccParser.BITNOT:
        checkIfTypeMatch(ctx, Type.intType(), exprType);
        return new UnaryOperNode(Type.intType(), exprNode, UnaryOperNode.UnaryOperator.BITNOT);
      default:
        return null;
    }
  }

  @Override
  public Node visitMultDivModExpr(WaccParser.MultDivModExprContext ctx) {
    int operatorType = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(0), Type.intType(), expr1Type);
    checkIfTypeMatch(ctx.getChild(2), Type.intType(), expr2Type);

    switch (operatorType) {
      case WaccParser.MULT:
        return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.MUL);
      case WaccParser.DIV:
        return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.DIV);
      case WaccParser.MOD:
        return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.MOD);
      default:
        return null;
    }

  }

  @Override
  public Node visitPlusMinusExpr(WaccParser.PlusMinusExprContext ctx) {
    int operatorType = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(0), Type.intType(), expr1Type);
    checkIfTypeMatch(ctx.getChild(2), Type.intType(), expr2Type);

    switch (operatorType) {
      case WaccParser.PLUS:
        return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.PLUS);
      case WaccParser.MINUS:
        return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.MINUS);
      default:
        return null;
    }
  }

  @Override
  public Node visitGreaterLessExpr(WaccParser.GreaterLessExprContext ctx) {
    int operatorType = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    if (expr1Type.equals(Type.intType())) {
      checkIfTypeMatch(ctx.getChild(2), Type.intType(), expr2Type);
    } else if (expr1Type.equals(Type.charType())) {
      checkIfTypeMatch(ctx.getChild(2), Type.charType(), expr2Type);
    } else {
      errorHandler.incompatibleType(ctx.getChild(0), expr1Type);
    }

    switch (operatorType) {
      case WaccParser.GREATER:
        return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.GT);
      case WaccParser.LESS:
        return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.LT);
      case WaccParser.GREATER_EQ:
        return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.GTE);
      case WaccParser.LESS_EQ:
        return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.LTE);
      default:
        return null;
    }
  }

  @Override
  public Node visitEqNotEqExpr(WaccParser.EqNotEqExprContext ctx) {
    int operatorType = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(2), expr1Type, expr2Type);

    switch (operatorType) {
      case WaccParser.EQ:
        return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.EQ);
      case WaccParser.NOT_EQ:
        return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.NEQ);
      default:
        return null;
    }
  }

  @Override
  public Node visitAndExpr(WaccParser.AndExprContext ctx) {
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(0), Type.boolType(), expr1Type);
    checkIfTypeMatch(ctx.getChild(2), Type.boolType(), expr2Type);

    return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.AND);
  }

  @Override
  public Node visitOrExpr(WaccParser.OrExprContext ctx) {
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(0), Type.boolType(), expr1Type);
    checkIfTypeMatch(ctx.getChild(2), Type.boolType(), expr2Type);

    return new BinaryOperNode(Type.boolType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.OR);
  }

  @Override
  public Node visitBitAndExpr(WaccParser.BitAndExprContext ctx) {
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(0), Type.intType(), expr1Type);
    checkIfTypeMatch(ctx.getChild(2), Type.intType(), expr2Type);

    return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.BITAND);
  }

  @Override
  public Node visitBitOrExpr(WaccParser.BitOrExprContext ctx) {
    ExprNode exprNode1 = (ExprNode) visit(ctx.getChild(0));
    Type expr1Type = exprNode1.getType();
    ExprNode exprNode2 = (ExprNode) visit(ctx.getChild(2));
    Type expr2Type = exprNode2.getType();

    checkIfTypeMatch(ctx.getChild(0), Type.intType(), expr1Type);
    checkIfTypeMatch(ctx.getChild(2), Type.intType(), expr2Type);

    return new BinaryOperNode(Type.intType(), exprNode1, exprNode2, BinaryOperNode.BinaryOperator.BITOR);
  }

  @Override
  public Node visitParenthesesExpr(WaccParser.ParenthesesExprContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Node visitIdent(WaccParser.IdentContext ctx) {
    String ident = ctx.getText();
    if (inLhs) {
      checkIfFunctionAssign(ctx, ident);
    }
    checkIfVariableUndefined(ctx, ident);

    return new IdentNode(ident, currentTable.lookup(ident));
  }

  // todo:
  private void checkArrayBound(WaccParser.Array_elemContext ctx, ArrayBoundAnalyser curr, List<Integer> list) {
    int currSize = list.remove(0);
    if (currSize + 1 > curr.getLength() || currSize < 0) {
      errorHandler.arrayOutOfBoundError(ctx);
    }
    if (list.size() > 0) {
      curr = curr.get(currSize);
      checkArrayBound(ctx, curr, list);
    }
  }

  @Override
  public Node visitArray_elem(WaccParser.Array_elemContext ctx) {
    String ident = ctx.ident().getText();
    checkIfVariableUndefined(ctx, ident);
    Type identType = currentTable.lookup(ident);

    List<ExprNode> exprNodes = new ArrayList<>();
    Type arrayElemType = identType;
    if (identType.equals(Type.stringType())) {
      if (inLhs) {
        errorHandler.typeMismatchArrayType(ctx, Type.stringType());
      } else {
        ExprNode exprNode = (ExprNode) visit(ctx.expr(0));
        exprNodes.add(exprNode);
        ArrayElemNode arrayElemNode = new ArrayElemNode(ident, exprNodes);
        arrayElemNode.setType(Type.charType());
        return arrayElemNode;
      }
    }

    // todo:
    ArrayBoundAnalyser analyser = arrayAnalyserMap.get(ctx.ident().getText()); //
    List<Integer> exprList = new ArrayList<>(); //

    for(WaccParser.ExprContext expr : ctx.expr()) {
      ExprNode exprNode = (ExprNode) visit(expr);
      exprNodes.add(exprNode);
      Type exprType = exprNode.getType();
      checkIfTypeMatch(ctx, Type.intType(), exprType);
      if (arrayElemType.isBaseType()) {
        errorHandler.invalidArrayAccess(ctx);
      }
      arrayElemType = arrayElemType.getType1();
      exprList.add(((IntLiterNode) exprNode).getValue()); //
    }
    checkArrayBound(ctx, analyser, exprList); //

    ArrayElemNode arrayElemNode = new ArrayElemNode(ident, exprNodes);
    arrayElemNode.setType(arrayElemType);
    return arrayElemNode;
  }

  @Override
  public Node visitArray_liter(WaccParser.Array_literContext ctx) {
    if (ctx.expr().size() != 0) {
      ExprNode exprNode = (ExprNode) visit(ctx.expr(0));
      Type type = exprNode.getType();

      List<ExprNode> exprNodes = new ArrayList<>();
      exprNodes.add((ExprNode) visit(ctx.expr(0)));
      //check if types in an array are the same.
      for(int i = 1; i < ctx.expr().size(); i++) {
        ExprNode nextExprNode = (ExprNode) visit(ctx.expr(i));
        exprNodes.add(nextExprNode);
        Type nextType = nextExprNode.getType();
        checkIfTypeMatch(ctx, type, nextType);
      }

      return new ArrayLiterNode(exprNodes, type);
    }
    return new ArrayLiterNode(new ArrayList<>(), Type.allType());
  }

  @Override
  public Node visitInt_liter(WaccParser.Int_literContext ctx) {
    int value;
    try {
      value = Integer.parseInt(ctx.getText());
    } catch (NumberFormatException e) {
      errorHandler.integerOverflow();
      return null;
    }
    IntLiterNode intLiterNode = new IntLiterNode(value);
    intLiterNode.setType(Type.intType());
    return intLiterNode;
  }

  @Override
  public Node visitBool_liter(WaccParser.Bool_literContext ctx) {
    String content = ctx.getText();
    boolean value = true;
    if (content.equals("false")) {
      value = false;
    }
    BoolLiterNode boolLiterNode = new BoolLiterNode(value);
    boolLiterNode.setType(Type.boolType());
    return boolLiterNode;
  }

  /*-------------------------------scope functions-------------------------------*/

  /* Create a new symbol table and have the current table become it's parent
   * table, and now we are in a new scope. */
  private void closeScope() {
    currentTable = currentTable.getParentTable();
  }

  /* Exit the scope by setting the current table to it's parent table. */
  private void openScope() {
    SymbolTable<Type> childTable = new SymbolTable<>(currentTable);
    currentTable.addChildTable(childTable);
    currentTable = childTable;
  }


  /*---------------------------semantic error check functions----------------------------*/

  private void checkIfTypeMatch(ParseTree ctx, Type t1, Type t2) {
    if (!t1.equals(t2)) errorHandler.typeMismatch(ctx, t1, t2);
  }

  private void checkIfTypeMismatchWithArray(ParseTree ctx, Type t1) {
    if (!t1.isArray()) errorHandler.typeMismatchArrayType(ctx, t1);
  }

  private void checkIfVariableUndefined(ParseTree ctx, String ident) {
    if (currentTable.lookup(ident) == null && !ident.equals("null")) errorHandler.variableUndefined(ctx, ident);
  }

  private void checkIfVariableRedefined(ParseTree ctx, String ident) {
    if (currentTable.lookupCurrent(ident) != null) {
      errorHandler.variableRedefined(ctx, ident);
    }
  }

  private void checkIfFunctionUndefined(ParseTree ctx, String ident) {
    if (topTable.getFunctionMap().get(ident) == null) errorHandler.functionUndefined(ctx, ident);
  }

  private void checkIfFunctionRedefined(ParseTree ctx, String ident) {
    if (topTable.getFunctionMap().get(ident) != null) errorHandler.functionRedefined(ctx, ident);
  }

  private void checkIfFunctionAssign(ParseTree ctx, String ident) {
    if (topTable.getFunctionMap().get(ident) != null) errorHandler.variableUndefined(ctx, ident);
  }

  private void checkNullPair(ParseTree ctx) {
    if (ctx.getChild(1).getText().equals("null")) errorHandler.accessingNullPair(ctx);
  }

  private void checkIfGlobalReturn(ParseTree ctx) {
    if (currentTable == topTable) errorHandler.globalReturn(ctx);
  }

  private void checkArgsNumber(ParseTree ctx, String ident, int expected, int actual) {
    if (expected != actual) errorHandler.incorrectArgsNumber(ctx, ident, expected, actual);
  }

  private void checkIfIsAssign(ParseTree ctx) {
    if (!(ctx instanceof WaccParser.AssignStatContext)) {
      errorHandler.isNotAssign(ctx);
    }
  }

  private void checkIfIsCondition(ParseTree ctx) {
    if (!(ctx instanceof WaccParser.GreaterLessExprContext || ctx instanceof WaccParser.EqNotEqExprContext)) {
      errorHandler.isNotCondition(ctx);
    }
  }

  private void checkIfIsDeclareOrAssign(ParseTree ctx) {
    if (!(ctx instanceof WaccParser.DeclareStatContext || ctx instanceof WaccParser.AssignStatContext)) {
      errorHandler.isNotDeclareOrAssign(ctx);
    }
  }

  private static Type getType(WaccParser.TypeContext ctx) {
    if (ctx.OPEN_BRACKETS() != null) {
      return Type.arrayType(getType(ctx.type()));
    }
    if (ctx.PAIR() != null) {
      return Type.pairType(getPairElemType(ctx.pair_elem_type().get(0)),
              getPairElemType(ctx.pair_elem_type().get(1)));
    }
    switch (ctx.getText()) {
      case "int": return Type.intType();
      case "bool": return Type.boolType();
      case "char": return Type.charType();
      case "string": return Type.stringType();
      default: return null;
    }
  }

  private static Type getPairElemType(WaccParser.Pair_elem_typeContext ctx) {
    if (ctx.PAIR() != null) {
      return Type.pairType(Type.allType(), Type.allType());
    }
    return getType(ctx.type());
  }

  public SymbolTable<Type> getTopTable() {
    return topTable;
  }

}
