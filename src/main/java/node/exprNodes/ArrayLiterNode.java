package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

import java.util.List;

public class ArrayLiterNode extends ExprNode {

    private final List<ExprNode> expressions;

    public ArrayLiterNode(List<ExprNode> expressions, Type type) {
        this.expressions = expressions;
        this.type = Type.arrayType(type);
    }

    public int getSize() {
        return expressions.size();
    }

    public List<ExprNode> getExpressions() {
        return expressions;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[");
        for (ExprNode exprNode : expressions) {
            buffer.append(exprNode.toString()).append(",");
        }
        buffer.deleteCharAt(buffer.length());
        buffer.append("]");
        return buffer.toString();
    }

    @Override
    public void translate() {
        CodeGenerator.translateArrayLiterNode(this);
    }
}
