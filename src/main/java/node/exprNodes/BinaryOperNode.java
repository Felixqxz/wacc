package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class BinaryOperNode extends ExprNode {

    public enum BinaryOperator {
        MUL, DIV, MOD, PLUS, MINUS, GT, GTE, LT, LTE, EQ, NEQ, AND, OR, BITAND, BITOR
    }

    private final ExprNode lhs;
    private final ExprNode rhs;
    private final BinaryOperator oper;

    public BinaryOperNode(Type type, ExprNode lhs, ExprNode rhs, BinaryOperator oper) {
        this.type = type;
        this.lhs = lhs;
        this.rhs = rhs;
        this.oper = oper;
    }

    @Override
    public int getWeight() {
        return lhs.getWeight() + rhs.getWeight();
    }

    public ExprNode getLhs() {
        return lhs;
    }

    public ExprNode getRhs() {
        return rhs;
    }

    public BinaryOperator getOper() {
        return oper;
    }

    @Override
    public void translate() {
        CodeGenerator.translateBinaryOperNode(this);
    }

}
