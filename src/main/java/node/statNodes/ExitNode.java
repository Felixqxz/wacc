package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;

public class ExitNode extends StatNode {

    private final ExprNode exprNode;

    public ExitNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    public ExprNode getExprNode() {
        return exprNode;
    }

    @Override
    public boolean hasExitStat() {
        return true;
    }

    @Override
    public void translate() {
        CodeGenerator.translateExitNode(this);
    }

}
