package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;

public class AssignNode extends StatNode {

    private final ExprNode assignLhsNode;
    private final ExprNode assignRhsNode;

    public AssignNode(ExprNode assignLhsNode, ExprNode assignRhsNode) {
        this.assignLhsNode = assignLhsNode;
        this.assignRhsNode = assignRhsNode;
        assignRhsNode.setType(assignLhsNode.getType());
    }

    public ExprNode getAssignLhsNode() {
        return assignLhsNode;
    }

    public ExprNode getAssignRhsNode() {
        return assignRhsNode;
    }

    @Override
    public void translate() {
        CodeGenerator.translateAssignNode(this);
    }
}
