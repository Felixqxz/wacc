package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;
import utils.Type;

public class ReturnNode extends StatNode {

    private final ExprNode exprNode;

    public ReturnNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    @Override
    public boolean guaranteeReturn() {
        return true;
    }

    public ExprNode getExprNode() {
        return exprNode;
    }

    @Override
    public Type getReturnType() {
        return exprNode.getType();
    }

    @Override
    public void translate() {
        CodeGenerator.translateReturnNode(this);
    }
}
