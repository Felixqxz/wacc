package node.statNodes;

import codeGenerator.CodeGenerator;
import node.exprNodes.ExprNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class SwitchNode extends StatNode {

    private final ExprNode entity;
    private final Map<ExprNode, StatNode> cases = new LinkedHashMap<>();
    private StatNode defaultStat = null;

    public SwitchNode(ExprNode entity) {
        this.entity = entity;
    }

    public ExprNode getEntity() {
        return entity;
    }

    public Map<ExprNode, StatNode> getCases() {
        return cases;
    }

    public StatNode getDefaultStat() {
        return defaultStat;
    }

    public void addCase(ExprNode exprNode, StatNode statNode) {
        cases.put(exprNode, statNode);
    }

    public void setDefaultStat(StatNode defaultStat) {
        this.defaultStat = defaultStat;
    }

    @Override
    public boolean hasBreakStat() {
        for (Map.Entry<ExprNode, StatNode> entry : cases.entrySet()) {
            if (entry.getValue() != null && entry.getValue().hasBreakStat()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void translate() {
        CodeGenerator.translateSwitchNode(this);
    }
}
