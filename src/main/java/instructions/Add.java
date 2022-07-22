package instructions;

import utils.Register;

public class Add extends Instruction {

  private final Register destRegister;
  private final Register firstRegister;
  private Register secondRegister;
  private SetSymbol setSymbolMode = SetSymbol.NO_SET;
  private int immediate = DEFAULT_INTEGER;
  private Shift shiftMode = Shift.NO_SHIFT;

  public Add(Register destRegister, Register firstRegister, int immediate) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.immediate = immediate;
  }

  public Add(Register destRegister, Register firstRegister, Register secondRegister) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }

  public Add(SetSymbol setSymbolMode, Register destRegister, Register firstRegister, Register secondRegister) {
    this.setSymbolMode = setSymbolMode;
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }

  public Add(Register destRegister, Register firstRegister, Register secondRegister, Shift shiftMode, int immediate) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
    this.shiftMode = shiftMode;
    this.immediate = immediate;
  }

  @Override
  public String toString() {
    if (shiftMode != Shift.NO_SHIFT) {
      return "\t\tADD" + setSymbolMode + " " + destRegister + ", " + firstRegister + ", "
              + secondRegister + ", " + shiftMode + " #" + immediate + "\n";
    } else if (secondRegister != null) {
      return "\t\tADD" + setSymbolMode + " " + destRegister + ", " + firstRegister + ", " + secondRegister + "\n";
    } else {
      return "\t\tADD " + destRegister + ", " + firstRegister + ", #" + immediate + "\n";
    }
  }
}
