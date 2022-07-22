package node.statNodes;

import utils.Type;

public class SeqNode extends StatNode {

    private final StatNode fstStatNode;
    private final StatNode sndStatNode;

    public SeqNode(StatNode fstStatNode, StatNode sndStatNode) {
        this.fstStatNode = fstStatNode;
        this.sndStatNode = sndStatNode;
    }

    @Override
    public boolean guaranteeReturn() {
        return fstStatNode.guaranteeReturn() || sndStatNode.guaranteeReturn();
    }

    @Override
    public Type getReturnType() {
        if (fstStatNode.getReturnType() != null) {
            return fstStatNode.getReturnType();
        } else {
            return sndStatNode.getReturnType();
        }
    }

    @Override
    public boolean hasExitStat() {
        return fstStatNode.hasExitStat() || sndStatNode.hasExitStat();
    }

    @Override
    public boolean hasBreakStat() {
        return fstStatNode.hasBreakStat() || sndStatNode.hasBreakStat();
    }

    @Override
    public boolean hasContinueStat() {
        return fstStatNode.hasContinueStat() || sndStatNode.hasContinueStat();
    }

    @Override
    public void translate() {
        fstStatNode.translate();
        sndStatNode.translate();
    }
}
