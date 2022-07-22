package instructions;

import utils.Register;

public class ReverseSubtraction extends Instruction {

    private final Register destRegister;
    private final Register firstRegister;
    private Register secondRegister;
    private int immediate = DEFAULT_INTEGER;

    public ReverseSubtraction(Register destRegister, Register firstRegister, Register secondRegister) {
        this.destRegister = destRegister;
        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
    }

    public ReverseSubtraction(Register destRegister, Register firstRegister, int immediate) {
        this.destRegister = destRegister;
        this.firstRegister = firstRegister;
        this.immediate = immediate;
    }

    @Override
    public String toString() {
        if (secondRegister != null) {
            return "\t\tRSBS " + destRegister + ", " + firstRegister + ", " + secondRegister + "\n";
        } else {
            return "\t\tRSBS " + destRegister + ", " + firstRegister + ", #" + immediate + "\n";
        }
    }
}
