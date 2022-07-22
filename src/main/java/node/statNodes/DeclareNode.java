package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;
import utils.Type;

public class DeclareNode extends StatNode {

    private final String ident;
    private final ExprNode assignRhsNode;

    public DeclareNode(String ident, ExprNode assignRhsNode) {
        this.ident = ident;
        this.assignRhsNode = assignRhsNode;
    }

    public String getIdent() {
        return this.ident;
    }

    public ExprNode getAssignRhsNode() {
        return assignRhsNode;
    }

    @Override
    public void translate() {
        CodeGenerator.translateDeclareNode(this);
    }
}
