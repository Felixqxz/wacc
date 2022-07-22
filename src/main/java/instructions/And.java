package instructions;

import utils.Register;

public class And extends Instruction {

  private Register destRegister;
  private Register firstRegister;
  private Register secondRegister;

  public And(Register destRegister, Register firstRegister, Register secondRegister) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }


  @Override
  public String toString() {
    return "\t\tAND " + destRegister + ", " + firstRegister + ", " + secondRegister + "\n";
  }
}
