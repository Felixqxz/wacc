package instructions;

import utils.Register;

public class Move extends Instruction {

  private final Register destRegister;
  private Register sourceRegister;
  private Cond cond = Cond.AL;
  private int immediate = DEFAULT_INTEGER;
  private char character = DEFAULT_CHAR;

  public Move(Register destRegister, int immediate) {
    this.destRegister = destRegister;
    this.immediate = immediate;
  }

  public Move(Register destRegister, int immediate, Cond cond) {
    this.destRegister = destRegister;
    this.immediate = immediate;
    this.cond = cond;
  }

  public Move(Register destRegister, Register sourceRegister) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
  }

  public Move(Register destRegister, Register sourceRegister, Cond cond) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.cond = cond;
  }

  public Move(Register destRegister, char character) {
    this.destRegister = destRegister;
    this.character = character;
  }

  public Move(Register destRegister, char character, Cond cond) {
    this.destRegister = destRegister;
    this.character = character;
    this.cond = cond;
  }

  @Override
  public String toString() {
    if (immediate != DEFAULT_INTEGER) {
      return "\t\tMOV" + cond + " " + destRegister + ", #" + immediate + "\n";
    } else if (sourceRegister != null) {
      return "\t\tMOV" + cond + " " + destRegister + ", " + sourceRegister + "\n";
    } else {
      return "\t\tMOV" + cond + " " + destRegister + ", #'" + character + "'\n";
    }
  }
}
