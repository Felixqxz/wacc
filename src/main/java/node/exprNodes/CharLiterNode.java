package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class CharLiterNode extends ExprNode {

    private final char character;

    public CharLiterNode(char character) {
        this.character = character;
        this.type = Type.charType();
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public void translate() {
        CodeGenerator.translateCharLiterNode(this);
    }
}
