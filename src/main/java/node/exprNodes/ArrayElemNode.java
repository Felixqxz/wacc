package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

import java.util.List;

public class ArrayElemNode extends ExprNode {

    private final String ident;
    private final List<ExprNode> index;

    public ArrayElemNode(String ident, List<ExprNode> index) {
        this.ident = ident;
        this.index = index;
    }

    public String getIdent() {
        return ident;
    }

    public List<ExprNode> getIndex() {
        return index;
    }

    @Override
    public void setType(Type type) {
        super.setType(type);
    }

    @Override
    public void translate() {
        CodeGenerator.translateArrayElemNode(this);
    }
}
