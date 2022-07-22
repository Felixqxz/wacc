package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class NewpairNode extends ExprNode {

    private final ExprNode leftExpr;
    private final ExprNode rightExpr;

    public NewpairNode(ExprNode leftExpr, ExprNode rightExpr) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.type = Type.pairType(leftExpr.getType(), rightExpr.getType());
    }

    public ExprNode getLeftExpr() {
        return leftExpr;
    }

    public ExprNode getRightExpr() {
        return rightExpr;
    }

    @Override
    public void translate() {
        CodeGenerator.translateNewpairNode(this);

    }
}
