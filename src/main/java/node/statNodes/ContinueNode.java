package node.statNodes;

import codeGenerator.CodeGenerator;

public class ContinueNode extends StatNode {

    @Override
    public boolean hasContinueStat() {
        return true;
    }

    @Override
    public void translate() {
        CodeGenerator.translateContinueNode(this);
    }
}
