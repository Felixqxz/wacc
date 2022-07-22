package instructions;

import utils.Register;
import static utils.Register.PROGRAM_COUNTER_REGISTER_NUMBER;

public class Pop extends Instruction {

  private final Register destRegister;

  public Pop(Register destRegister) {
    this.destRegister = destRegister;
  }

  /* POP {pc}. Pop {pc} pops 'lr' from the stack and places it in 'pc' which is effectively the
     pop of 'lr' and a jump to it. It's a 'return' instruction when 'lr' has been pushed
     to the stack. */
  public static Pop PopProgramCounter() {
    return new Pop(new Register(PROGRAM_COUNTER_REGISTER_NUMBER));
  }

  @Override
  public String toString() {
    return "\t\tPOP {" + destRegister + "}\n";
  }
}
