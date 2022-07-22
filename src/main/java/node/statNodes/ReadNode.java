package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;

public class ReadNode extends StatNode {

    private final ExprNode exprNode;

    public ReadNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    public ExprNode getExprNode() {
        return exprNode;
    }

    @Override
    public void translate() {
        CodeGenerator.translateReadNode(this);
    }
}
