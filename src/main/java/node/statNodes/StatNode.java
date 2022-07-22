package node.statNodes;

import node.Node;
import utils.Type;

/**
 * The StatNode class extends Node class and is the highest level in all stat nodes.
 */

public abstract class StatNode extends Node {

    public boolean guaranteeReturn() { return false; }

    public Type getReturnType() {
        return null;
    }

    public boolean hasExitStat() {
        return false;
    }

    public boolean hasBreakStat() { return false; }

    public boolean hasContinueStat() {return  false; }
}
