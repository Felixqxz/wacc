package instructions;

import utils.Register;

public class Load extends Instruction {

  private Cond cond = Cond.AL;
  private final Register destRegister;
  private Register sourceRegister;
  private int immediate = DEFAULT_INTEGER;
  private Label message;
  private boolean withImmediate = false;

  public Load(Register destRegister, Register sourceRegister) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
  }

  public Load(Register destRegister, Register sourceRegister, Cond cond) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.cond = cond;
  }

  public Load(Register destRegister, int immediate) {
    this.destRegister = destRegister;
    this.immediate = immediate;
  }

  public Load(Register destRegister, int immediate, Cond cond) {
    this.destRegister = destRegister;
    this.immediate = immediate;
    this.cond = cond;
  }

  public Load(Register destRegister, Label message) {
    this.destRegister = destRegister;
    this.message = message;
  }

  public Load(Register destRegister, Label message, Cond cond) {
    this.destRegister = destRegister;
    this.message = message;
    this.cond = cond;
  }

  public Load(Register destRegister, Register sourceRegister, int immediate) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.immediate = immediate;
    this.withImmediate = true;
  }

  public Load(Register destRegister, Register sourceRegister, int immediate, Cond cond) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.immediate = immediate;
    this.withImmediate = true;
    this.cond = cond;
  }


  @Override
  public String toString() {
    if (withImmediate && immediate != 0) {
      return "\t\tLDR" + cond + " " + destRegister + ", [" + sourceRegister + ", #" + immediate + "]\n";
    } else if (message != null) {
      return "\t\tLDR" + cond + " " + destRegister + ", =" + message.getLabel() + "\n";
    } else if (sourceRegister == null) {
      return "\t\tLDR" + cond + " " + destRegister + ", =" + immediate + "\n";
    } else {
      return "\t\tLDR" + " " + destRegister + ", [" + sourceRegister + "]\n";
    }
  }
}
