package instructions;

import utils.Register;

public class ExclusiveOr extends Instruction {

  private final Register destRegister;
  private final Register firstRegister;
  private Register secondRegister;
  private int immediate = DEFAULT_INTEGER;

  public ExclusiveOr(Register destRegister, Register firstRegister, Register secondRegister) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }

  public ExclusiveOr(Register destRegister, Register firstRegister, int immediate) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.immediate = immediate;
  }

  @Override
  public String toString() {
    if (secondRegister != null) {
      return "\t\tEOR " + destRegister + ", " + firstRegister + ", " + secondRegister + "\n";
    } else {
      return "\t\tEOR " + destRegister + ", " + firstRegister + ", #" + immediate + "\n";
    }
  }
}
