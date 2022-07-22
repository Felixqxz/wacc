package node.exprNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class IdentNode extends ExprNode {

    private final String name;

    public IdentNode(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public void translate() {
        CodeGenerator.translateIdentNode(this);
    }
}
