package instructions;

import utils.Register;

public class LoadByte extends Instruction {

    private final Register sourceRegister;
    private final Register destRegister;
    private int immediate = DEFAULT_INTEGER;
    private boolean withImmediate = false;

    public LoadByte(Register sourceRegister, Register destRegister) {
        this.sourceRegister = sourceRegister;
        this.destRegister = destRegister;
    }

    public LoadByte(Register sourceRegister, Register destRegister, int immediate) {
        this.sourceRegister = sourceRegister;
        this.destRegister = destRegister;
        this.immediate = immediate;
        this.withImmediate = true;
    }

    @Override
    public String toString() {
        if (withImmediate && immediate != 0) {
            return "\t\tLDRSB " + sourceRegister + ", [" + destRegister + ", #" + immediate + "]\n";
        } else {
            return "\t\tLDRSB " + sourceRegister + ", [" + destRegister + "]\n";
        }
    }
}
