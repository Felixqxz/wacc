package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class PairElemNode extends ExprNode {

    private final String ident;
    private final boolean first;

    public PairElemNode(boolean first, String ident) {
        this.ident = ident;
        this.first = first;
    }

    public String getIdent() {
        return ident;
    }

    public boolean isFirst() {
        return first;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void translate() {
        CodeGenerator.translatePairElemNode(this);
    }
}
