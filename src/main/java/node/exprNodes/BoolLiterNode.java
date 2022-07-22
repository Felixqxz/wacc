package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class BoolLiterNode extends ExprNode {

    private final boolean bool;

    public BoolLiterNode(boolean bool) {
        this.bool = bool;
        this.type = Type.boolType();
    }

    public boolean getBool() {
        return bool;
    }

    @Override
    public void translate() {
        CodeGenerator.translateBoolLiterNode(this);
    }
}
