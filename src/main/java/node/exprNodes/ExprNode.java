package node.exprNodes;

import node.Node;
import utils.Type;

/**
 * The ExprNode class extends Node class and is the highest level in all expr nodes.
 * It also includes nodes in assign_lhs and assign_rhs in our design.
 */

public abstract class ExprNode extends Node {

    protected Type type;
    protected int weight = 1;

    public Type getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
