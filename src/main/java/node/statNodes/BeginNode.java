package node.statNodes;

import codeGenerator.CodeGenerator;
import utils.Type;

public class BeginNode extends StatNode {

    private final StatNode body;

    public BeginNode(StatNode body) {
        this.body = body;
    }

    public StatNode getBody() {
        return body;
    }

    @Override
    public boolean guaranteeReturn() {
        return body.guaranteeReturn();
    }

    @Override
    public Type getReturnType() {
        return body.getReturnType();
    }

    @Override
    public boolean hasExitStat() {
        return body.hasExitStat();
    }

    @Override
    public boolean hasBreakStat() {
        return body.hasBreakStat();
    }

    @Override
    public void translate() {
        CodeGenerator.translateBeginNode(this);
    }
}
