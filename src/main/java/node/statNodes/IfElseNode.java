package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;
import utils.Type;

public class IfElseNode extends StatNode {

    private final ExprNode condition;
    private final StatNode body1;
    private final StatNode body2;

    public IfElseNode(ExprNode condition, StatNode body1, StatNode body2) {
        this.condition = condition;
        this.body1 = body1;
        this.body2 = body2;
    }

    public ExprNode getCondition() {
        return condition;
    }

    public StatNode getBody1() {
        return body1;
    }

    public StatNode getBody2() {
        return body2;
    }

    @Override
    public boolean guaranteeReturn() {
        return body1.guaranteeReturn() && body2.guaranteeReturn();
    }

    @Override
    public Type getReturnType() {
        if (body1.getReturnType() != null) {
            return body1.getReturnType();
        } else {
            return body2.getReturnType();
        }
    }

    @Override
    public boolean hasExitStat() {
        return body1.hasExitStat() || body2.hasExitStat();
    }

    @Override
    public boolean hasBreakStat() {
        return body1.hasBreakStat() || body2.hasBreakStat();
    }

    @Override
    public boolean hasContinueStat() {
        return body1.hasContinueStat() || body2.hasContinueStat();
    }

    @Override
    public void translate() {
        CodeGenerator.translateIfElseNode(this);
    }
}
