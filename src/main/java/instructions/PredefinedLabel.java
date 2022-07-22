package instructions;

import static instructions.Cond.*;
import static instructions.Instruction.Link.*;
import static instructions.Pop.PopProgramCounter;
import static instructions.Push.pushLinkerRegister;
import static utils.Register.*;

import java.util.LinkedList;
import java.util.List;
import utils.Register;

public class PredefinedLabel extends Label{

    /*
     * Predefined labels are for those functions which have particular fixed formats.
     */

    public enum Predefined {
        // Labels for printing:
        P_PRINT_INT, P_PRINT_STRING, P_PRINT_BOOL, P_PRINT_LN, P_PRINT_REFERENCE,

        // Labels for reading:
        P_READ_INT, P_READ_CHAR,

        // Labels for checking:
        P_CHECK_ARRAY_BOUNDS, P_CHECK_DIVIDE_BY_ZERO, P_CHECK_NULL_POINTER,

        // Labels for throwing errors:
        P_THROW_RUNTIME_ERROR, P_THROW_OVERFLOW_ERROR,

        // Label for freeing a pair:
        P_FREE_PAIR, P_FREE_ARRAY

        ;
    }

    /* Fields for predefined labels. */
    Predefined predefinedLabel;
    private final List<Instruction> instructionList;
    private final DataSection dataSection;

    /* Constructor for predefined labels. */
    public PredefinedLabel(Predefined predefinedLabel, DataSection dataSection) {
        super(predefinedLabel.name().toLowerCase());
        this.dataSection = dataSection;
        this.instructionList = new LinkedList<>();
        this.predefinedLabel = predefinedLabel;
        switch (predefinedLabel) {
            case P_PRINT_INT:
                printInt(instructionList);
                break;
            case P_PRINT_STRING:
                printString(instructionList);
                break;
            case P_PRINT_BOOL:
                printBool(instructionList);
                break;
            case P_PRINT_LN:
                printLn(instructionList);
                break;
            case P_READ_INT:
                readInt(instructionList);
                break;
            case P_READ_CHAR:
                readChar(instructionList);
                break;
            case P_CHECK_ARRAY_BOUNDS:
                checkArrayBounds(instructionList);
                break;
            case P_CHECK_DIVIDE_BY_ZERO:
                checkDivideByZero(instructionList);
                break;
            case P_CHECK_NULL_POINTER:
                checkNullPointer(instructionList);
                break;
            case P_THROW_RUNTIME_ERROR:
                throwRuntimeError(instructionList);
                break;
            case P_THROW_OVERFLOW_ERROR:
                throwOverflowError(instructionList);
                break;
            case P_PRINT_REFERENCE:
                printReference(instructionList);
                break;
            case P_FREE_PAIR:
                freePair(instructionList);
                break;
            case P_FREE_ARRAY:
                freeArray(instructionList);
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(label + ":");
        stringBuilder.append("\n");
        for (Instruction instruction : instructionList) {
            stringBuilder.append(instruction);
        }
        return stringBuilder.toString();
    }

    /*---------------------------------Helper functions---------------------------------*/
    private Register r0 = new Register(0);
    private Register r1 = new Register(1);
    private Register r2 = new Register(2);
    private Register sp = new Register(STACK_POINTER_REGISTER_NUMBER);
    private Register lr = new Register(Register.LINKER_REGISTER_NUMBER);
    private Register pc = new Register(PROGRAM_COUNTER_REGISTER_NUMBER);

    private void printInt(List<Instruction> instructionList) {
        String message = "%d\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Move(r1, r0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK,new Label("printf")));
        instructionList.add(new Move(r0, 0));
        instructionList.add(new Branch(WITH_LINK,new Label("fflush")));
        instructionList.add(PopProgramCounter());
    }

    private void printString(List<Instruction> instructionList) {
        String message = "%.*s\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Load(r1, r0));
        instructionList.add(new Add(r2, r0, 4));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("printf")));
        instructionList.add(new Move(r0, 0));
        instructionList.add(new Branch(WITH_LINK, new Label("fflush")));
        instructionList.add(PopProgramCounter());
    }

    private void printBool(List<Instruction> instructionList) {
        String trueMessage = "true\\0";
        String falseMessage = "false\\0";
        dataSection.addMessageLabels(new MessageLabel(trueMessage));
        dataSection.addMessageLabels(new MessageLabel(falseMessage));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Compare(r0, 0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(trueMessage), NE));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(falseMessage), EQ));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("printf")));
        instructionList.add(new Move(r0, 0));
        instructionList.add(new Branch(WITH_LINK, new Label("fflush")));
        instructionList.add(PopProgramCounter());
    }

    private void printLn(List<Instruction> instructionList) {
        String message = "\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("puts")));
        instructionList.add(new Move(r0, 0));
        instructionList.add(new Branch(WITH_LINK, new Label("fflush")));
        instructionList.add(PopProgramCounter());
    }

    private void printReference(List<Instruction> instructionList) {
        String message = "%p\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(new Push(lr));
        instructionList.add(new Move(r1, r0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("printf")));
        instructionList.add(new Move(r0, 0));
        instructionList.add(new Branch(WITH_LINK, new Label("fflush")));
        instructionList.add(PopProgramCounter());
    }

    private void readInt(List<Instruction> instructionList) {
        String message = "%d\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Move(r1, r0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("scanf")));
        instructionList.add(PopProgramCounter());
    }

    private void readChar(List<Instruction> instructionList) {
        String message = " %c\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Move(r1, r0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Add(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("scanf")));
        instructionList.add(PopProgramCounter());
    }

    private void checkArrayBounds(List<Instruction> instructionList) {
        String message1 = "ArrayIndexOutOfBoundsError: negative index\\n\\0";
        String message2 = "ArrayIndexOutOfBoundsError: index too large\\n\\0";
        dataSection.addMessageLabels(new MessageLabel(message1));
        dataSection.addMessageLabels(new MessageLabel(message2));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Compare(r0, 0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message1), LT));
        instructionList.add(new Branch(WITH_LINK, LT, new Label("p_throw_runtime_error")));
        instructionList.add(new Load(r1, r1, 0));
        instructionList.add(new Compare(r0, r1));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message2), CS));
        instructionList.add(new Branch(WITH_LINK, CS, new Label("p_throw_runtime_error")));
        instructionList.add(PopProgramCounter());
    }

    private void checkDivideByZero(List<Instruction> instructionList) {
        String message = "DivideByZeroError: divide or modulo by zero\\n\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Compare(r1, 0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message), EQ));
        instructionList.add(new Branch(WITH_LINK, EQ, new Label("p_throw_runtime_error")));
        instructionList.add(PopProgramCounter());
    }

    private void checkNullPointer(List<Instruction> instructionList) {
        String message = "NullReferenceError: dereference a null reference\\n\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Compare(r0, 0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message), EQ));
        instructionList.add(new Branch(WITH_LINK, EQ, new Label("p_throw_runtime_error")));
        instructionList.add(PopProgramCounter());
    }

    private void throwRuntimeError(List<Instruction> instructionList) {
        instructionList.add(new Branch(WITH_LINK, new Label("p_print_string")));
        instructionList.add(new Move(r0, -1));
        instructionList.add(new Branch(WITH_LINK, new Label("exit")));
    }

    private void throwOverflowError(List<Instruction> instructionList) {
        String message = "OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message)));
        instructionList.add(new Branch(WITH_LINK, new Label("p_throw_runtime_error")));
    }

    private void freePair(List<Instruction> instructionList) {
        String message = "NullReferenceError: dereference a null reference\\n\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Compare(r0, 0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message), EQ));
        instructionList.add(new Branch(EQ, new Label("p_throw_runtime_error")));
        instructionList.add(new Push(r0));
        instructionList.add(new Load(r0, r0));
        instructionList.add(new Branch(WITH_LINK, new Label("free")));
        instructionList.add(new Load(r0, sp));
        instructionList.add(new Load(r0, r0, 4));
        instructionList.add(new Branch(WITH_LINK, new Label("free")));
        instructionList.add(new Pop(r0));
        instructionList.add(new Branch(WITH_LINK, new Label("free")));
        instructionList.add(PopProgramCounter());
    }

    private void freeArray(List<Instruction> instructionList) {
        String message = "NullReferenceError: dereference a null reference\\n\\0";
        dataSection.addMessageLabels(new MessageLabel(message));
        instructionList.add(pushLinkerRegister());
        instructionList.add(new Compare(r0, 0));
        instructionList.add(new Load(r0, dataSection.getMessageLabel(message), EQ));
        instructionList.add(new Branch(EQ, new Label("p_throw_runtime_error")));
        instructionList.add(new Branch(WITH_LINK, new Label("free")));
        instructionList.add(PopProgramCounter());
    }

}