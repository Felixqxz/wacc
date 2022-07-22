package instructions;

public enum Cond {
    EQ,   // Equal
    NE,   // Not equal
    HS,   // Unsigned higher or same
    CS,   // carry set
    LO,   // Unsigned lower
    CC,   // carry clear
    MI,   // Negative, minus
    PL,   // Positive or zero, plus
    VS,   // Overflow
    VC,   // No Overflow
    HI,   // Unsigned higher
    LS,   // Unsigned lower or same
    GE,   // Signed greater or equal
    LT,   // Signed less than
    GT,   // Signed greater than
    LE,   // Signed less than or equal
    AL    // Always
    ;

    @Override
    public String toString() {
        if (this == AL) {
            return "";
        } else {
            return super.toString();
        }
    }
}