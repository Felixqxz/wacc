package node;

import codeGenerator.CodeGenerator;
import node.exprNodes.ExprNode;

public class MacroNode extends Node {

    private final String ident;
    private final ExprNode assignRhsNode;

    public MacroNode(String ident, ExprNode assignRhsNode) {
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
        CodeGenerator.translateMacroNode(this);
    }

}
