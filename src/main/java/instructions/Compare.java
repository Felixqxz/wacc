package instructions;

import utils.Register;

public class Compare extends Instruction {

  private final Register firstRegister;
  private Register secondRegister;
  private int immediate = DEFAULT_INTEGER;
  private Shift shiftMode = Shift.NO_SHIFT;

  public Compare(Register firstRegister, Register secondRegister) {
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }

  public Compare(Register firstRegister, int immediate) {
    this.firstRegister = firstRegister;
    this.immediate = immediate;
  }

  public Compare(Register firstRegister, Register secondRegister, Shift shiftMode, int immediate) {
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
    this.shiftMode = shiftMode;
    this.immediate = immediate;
  }

  @Override
  public String toString() {
    if (shiftMode != Shift.NO_SHIFT) {
      return "\t\tCMP " + firstRegister + ", " + secondRegister + ", " + shiftMode + " #" + immediate + "\n";
    } else if (secondRegister != null) {
      return "\t\tCMP " + firstRegister + ", " + secondRegister + "\n";
    } else {
      return "\t\tCMP " + firstRegister + ", #" + immediate + "\n";
    }
  }
}
