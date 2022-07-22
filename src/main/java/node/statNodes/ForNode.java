package node.statNodes;

import codeGenerator.CodeGenerator;
import node.exprNodes.ExprNode;
import utils.Type;

public class ForNode extends StatNode {

    private final StatNode variableIdentify;
    private final ExprNode condition;
    private final StatNode variableModify;
    private final StatNode body;

    public ForNode(StatNode variableIdentify, ExprNode condition, StatNode variableModify, StatNode body) {
        this.variableIdentify = variableIdentify;
        this.condition = condition;
        this.variableModify = variableModify;
        this.body = body;
    }

    public StatNode getBody() {
        return body;
    }

    public ExprNode getCondition() {
        return condition;
    }

    public StatNode getVariableIdentify() {
        return variableIdentify;
    }

    public StatNode getVariableModify() {
        return variableModify;
    }

    @Override
    public Type getReturnType() {
        return super.getReturnType();
    }

    @Override
    public boolean guaranteeReturn() {
        return body.guaranteeReturn();
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
        CodeGenerator.translateForNode(this);
    }
}
