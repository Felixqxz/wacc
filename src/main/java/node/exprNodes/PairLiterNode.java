package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class PairLiterNode extends ExprNode {

    public PairLiterNode() {
        this.type = Type.pairType(Type.allType(), Type.allType());
    }

    @Override
    public void translate() {
        CodeGenerator.translatePairLiterNode(this);
    }
}
