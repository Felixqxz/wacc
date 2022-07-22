package node;

/**
 * The Node class is the highest level in the hierarchy of our AST internal representation.
 */

public abstract class Node {

    /* Translate node to assembly instructions. */
    public abstract void translate();

}
