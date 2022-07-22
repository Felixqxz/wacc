package instructions;

import utils.Register;

public class Multiply extends Instruction {

    private final Register destRegister1;
    private final Register destRegister2;
    private final Register sourceRegister1;
    private final Register sourceRegister2;

    public Multiply(Register destRegister1, Register destRegister2, Register sourceRegister1, Register sourceRegister2) {
        this.destRegister1 = destRegister1;
        this.destRegister2 = destRegister2;
        this.sourceRegister1 = sourceRegister1;
        this.sourceRegister2 = sourceRegister2;
    }

    @Override
    public String toString() {
        return "\t\tSMULL " + destRegister1 + ", " + destRegister2 + ", " + sourceRegister1 + ", " + sourceRegister2 + "\n";
    }
}
