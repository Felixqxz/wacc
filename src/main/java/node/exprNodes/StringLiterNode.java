package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class StringLiterNode extends ExprNode {

    private final String string;

    public StringLiterNode(String string) {
        this.string = string.substring(1, string.length() - 1);
        this.type = Type.stringType();
    }

    public String getValue() {
        return string;
    }

    @Override
    public void translate() {
        CodeGenerator.translateStringLiterNode(this);
    }
}
