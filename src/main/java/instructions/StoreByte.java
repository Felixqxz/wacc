package instructions;

import utils.Register;

public class StoreByte extends Instruction {

    private final Register sourceRegister;
    private final Register destRegister;
    private int immediate = DEFAULT_INTEGER;
    private boolean withImmediate = false;
    private boolean offset = false;

    public StoreByte(Register sourceRegister, Register destRegister) {
        this.sourceRegister = sourceRegister;
        this.destRegister = destRegister;
    }

    public StoreByte(Register sourceRegister, Register destRegister, int immediate) {
        this.sourceRegister = sourceRegister;
        this.destRegister = destRegister;
        this.immediate = immediate;
        this.withImmediate = true;
    }

    public StoreByte preOffset() {
        this.offset = true;
        return this;
    }

    @Override
    public String toString() {
        if (withImmediate && immediate != 0) {
            if (offset) {
                return "\t\tSTRB " + sourceRegister + ", [" + destRegister + ", #" + immediate + "]!\n";
            }
            return "\t\tSTRB " + sourceRegister + ", [" + destRegister + ", #" + immediate + "]\n";
        } else {
            return "\t\tSTRB " + sourceRegister + ", [" + destRegister + "]\n";
        }
    }

}