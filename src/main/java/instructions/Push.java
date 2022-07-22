package instructions;

import static utils.Register.LINKER_REGISTER_NUMBER;

import utils.Register;

public class Push extends Instruction {

  private final Register destRegister;

  public Push(Register destRegister) {
    this.destRegister = destRegister;
  }

  /* PUSH {lr}. Linker register stores return address of a subroutine, so this instruction
     appears at the beginning of every function. */
  public static Push pushLinkerRegister() {
    return new Push(new Register(LINKER_REGISTER_NUMBER));
  }

  @Override
  public String toString() {
    return "\t\tPUSH {" + destRegister + "}\n";
  }
}
