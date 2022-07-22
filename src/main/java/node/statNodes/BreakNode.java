package node.statNodes;

import codeGenerator.CodeGenerator;

public class BreakNode extends StatNode{

    @Override
    public boolean hasBreakStat() {
        return true;
    }

    @Override
    public void translate() {
        CodeGenerator.translateBreakNode(this);
    }
}
