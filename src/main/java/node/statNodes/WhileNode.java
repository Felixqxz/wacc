package node.statNodes;

import node.exprNodes.ExprNode;
import codeGenerator.CodeGenerator;
import utils.Type;

public class WhileNode extends StatNode {

    private final ExprNode condition;
    private final StatNode body;

    public WhileNode(ExprNode condition, StatNode body) {
        this.condition = condition;
        this.body = body;
    }

    public ExprNode getCondition() {
        return condition;
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
    public boolean hasContinueStat() {
        return body.hasContinueStat();
    }

    @Override
    public void translate() {
        CodeGenerator.translateWhileNode(this);
    }
}
