package instructions;

public abstract class Instruction {

    static final int DEFAULT_INTEGER = Integer.MIN_VALUE;
    static final char DEFAULT_CHAR = Character.MIN_VALUE;

    public enum SetSymbol {
        SET,
        NO_SET;

        @Override
        public String toString() {
            if (this == NO_SET) {
                return "";
            } else {
                return "S";
            }
        }
    }

    public enum Shift {
        LSL,  // Logical shift left
        LSR,  // Logical shift right
        ASR,   // Arithmetic shift right
        NO_SHIFT
        ;
    }

    public enum Link {

        WITH_LINK,
        WITHOUT_LINK
        ;

        @Override
        public String toString() {
            switch (this) {
                case WITH_LINK: return "L";
                case WITHOUT_LINK: return "";
            }
            return "no such link mode";
        }
    }
}
