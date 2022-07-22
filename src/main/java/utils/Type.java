package utils;

public class Type {

    /*
     *  This utils.Type class is the internal representation of different types in Wacc language.
     */

    public enum BaseTypeEnum {
        INT, BOOL, CHAR, STRING, PAIR, ARRAY, ALL
    }

    private BaseTypeEnum baseType;
    private Type type1;
    private Type type2;

    private Type() {}

    /* Constructor for base type. */
    public static Type baseType(BaseTypeEnum baseType) {
        Type type = new Type();
        type.baseType = baseType;
        return type;
    }

    /* Constructor for pair. */
    public static Type pairType(Type type1, Type type2) {
        Type type = new Type();
        type.baseType = BaseTypeEnum.PAIR;
        type.type1 = type1;
        type.type2 = type2;
        return type;
    }

    /* Constructor for array. */
    public static Type arrayType (Type type1) {
        Type type = new Type();
        type.baseType = BaseTypeEnum.ARRAY;
        type.type1 = type1;
        return type;
    }

    /* is pair. */
    public boolean isPair() {
        return baseType == BaseTypeEnum.PAIR;
    }

    /* is array. */
    public boolean isArray() {
        return baseType == BaseTypeEnum.ARRAY;
    }

    public boolean isAllType() {
        return baseType == BaseTypeEnum.ALL;
    }

    public boolean isBaseType() {
        return baseType == BaseTypeEnum.INT
                || baseType == BaseTypeEnum.CHAR
                || baseType == BaseTypeEnum.BOOL
                || baseType == BaseTypeEnum.STRING
                ;
    }

    public Type getType1() {
        return type1;
    }

    public Type getType2() {
        return type2;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Type)) {return false;}
        Type otherType = (Type) other;
        if (this.isPair() && otherType.isPair()) {
            return this.getType1().equals(otherType.getType1()) && this.getType2().equals(otherType.getType2());
        } else if (this.isArray() && otherType.isArray()) {
            return this.getType1().equals(otherType.getType1());
        } else if (this.isAllType() || otherType.isAllType()) {
            return true;
        } else {
            return this.baseType == otherType.baseType;
        }
    }

    @Override
    public String toString() {
        if (isPair()) {
            return "pair(" + type1.toString() + ", " + type2.toString() + ")";
        } else if (isArray()) {
            return type1.toString() + "[]";
        } else {
            return baseType.toString();
        }
    }

    public static Type intType() { return Type.baseType(Type.BaseTypeEnum.INT);}
    public static Type boolType() { return Type.baseType(Type.BaseTypeEnum.BOOL);}
    public static Type charType() { return Type.baseType(Type.BaseTypeEnum.CHAR);}
    public static Type stringType() { return Type.baseType(Type.BaseTypeEnum.STRING);}
    public static Type allType() {return Type.baseType(Type.BaseTypeEnum.ALL);}
}
