package instructions;

import utils.Register;

public class Subtraction extends Instruction {

  private final Register destRegister;
  private final Register firstRegister;
  private Register secondRegister;
  private int immediate = DEFAULT_INTEGER;

  public Subtraction(Register destRegister, Register firstRegister, int immediate) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.immediate = immediate;
  }

  public Subtraction(Register destRegister, Register firstRegister, Register secondRegister) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }

  @Override
  public String toString() {
    if (secondRegister != null) {
      return "\t\tSUBS " + destRegister + ", " + firstRegister + ", " + secondRegister + "\n";
    } else {
      return "\t\tSUB " + destRegister + ", " + firstRegister + ", #" + immediate + "\n";
    }
  }
}
