package utils;

public class Register {

  public static final int STACK_POINTER_REGISTER_NUMBER = 13;
  public static final int LINKER_REGISTER_NUMBER = 14;
  public static final int PROGRAM_COUNTER_REGISTER_NUMBER = 15;

  private final int number;

  public Register(int number) {
    this.number = number;
  }

  public Register next() {
    assert number < 12;
    return new Register(number + 1);
  }

  public Register pred() {
    assert number > 4;
    return new Register(number - 1);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Register)) {
      return false;
    }
    Register that = (Register) obj;
    return this.number == that.number;
  }

  @Override
  public String toString() {
    if (number == STACK_POINTER_REGISTER_NUMBER) {
      return "sp";
    } else if (number == LINKER_REGISTER_NUMBER) {
      return "lr";
    } else if (number == PROGRAM_COUNTER_REGISTER_NUMBER) {
      return "pc";
    } else {
      return "r" + number;
    }
  }
}
