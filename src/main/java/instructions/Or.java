package instructions;

import utils.Register;

public class Or extends Instruction {

  private final Register destRegister;
  private final Register firstRegister;
  private final Register secondRegister;

  public Or(Register destRegister, Register firstRegister, Register secondRegister) {
    this.destRegister = destRegister;
    this.firstRegister = firstRegister;
    this.secondRegister = secondRegister;
  }


  @Override
  public String toString() {
    return "\t\tORR " + destRegister + ", " + firstRegister + ", " + secondRegister + "\n";
  }
}