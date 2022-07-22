package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class UnaryOperNode extends ExprNode {

    public enum UnaryOperator { LEN, ORD, CHR, NOT, MINUS, BITNOT}

    private final UnaryOperator oper;
    private final ExprNode exprNode;

    public UnaryOperNode(Type type, ExprNode exprNode, UnaryOperator oper) {
        this.type = type;
        this.exprNode = exprNode;
        this.oper = oper;
    }

    public UnaryOperator getOper() {
        return oper;
    }

    public ExprNode getExprNode() {
        return exprNode;
    }

    @Override
    public void translate() {
        CodeGenerator.translateUnaryOperNode(this);
    }
}
