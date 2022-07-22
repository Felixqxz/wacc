package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;

public class PrintNode extends StatNode {

    public enum LINE {
        WITH_LINE, WITHOUT_LINE
    }

    private final ExprNode exprNode;
    private final LINE lineMode;

    public PrintNode(ExprNode exprNode) {
        this.exprNode = exprNode;
        this.lineMode = LINE.WITHOUT_LINE;
    }

    public PrintNode(ExprNode exprNode, LINE lineMode) {
        this.exprNode = exprNode;
        this.lineMode = lineMode;
    }

    public ExprNode getExprNode() {
        return exprNode;
    }

    public LINE getLineMode() {
        return lineMode;
    }

    @Override
    public void translate() {
        CodeGenerator.translatePrintNode(this);
    }
}
