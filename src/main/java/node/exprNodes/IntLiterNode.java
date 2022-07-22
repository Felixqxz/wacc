package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class IntLiterNode extends ExprNode {

    private final int value;

    public IntLiterNode(int value) {
        this.value = value;
        this.type = Type.intType();
    }

    public int getValue() {
        return value;
    }

    @Override
    public void translate() {
        CodeGenerator.translateIntLiterNode(this);
    }
}
