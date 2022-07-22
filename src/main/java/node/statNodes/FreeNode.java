package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;

public class FreeNode extends StatNode {

    private final ExprNode exprNode;

    public ExprNode getExprNode() {
        return exprNode;
    }

    public FreeNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    @Override
    public void translate() {
        CodeGenerator.translateFreeNode(this);
    }
}
