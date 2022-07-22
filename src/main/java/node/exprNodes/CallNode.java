package node.exprNodes;

import codeGenerator.CodeGenerator;

import java.util.List;

public class CallNode extends ExprNode {

    private final String ident;
    private final List<ExprNode> arguments;

    public CallNode(String ident, List<ExprNode> arguments) {
        this.ident = ident;
        this.arguments = arguments;
    }

    public String getIdent() {
        return ident;
    }

    public List<ExprNode> getArguments() {
        return arguments;
    }

    @Override
    public void translate() {
        CodeGenerator.translateCallNode(this);
    }
}