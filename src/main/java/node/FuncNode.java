package node;

import node.statNodes.StatNode;
import codeGenerator.CodeGenerator;
import utils.Type;

import java.util.List;

public class FuncNode extends Node {

    private final Type type;
    private final String ident;
    private final List<String> paramList;
    private final StatNode statNode;

    public FuncNode(Type type, String ident, StatNode statNode, List<String> paramList) {
        this.type = type;
        this.ident = ident;
        this.statNode = statNode;
        this.paramList = paramList;
    }

    public Type getType() {
        return type;
    }

    public String getIdent() {
        return ident;
    }

    public StatNode getStatNode() {
        return statNode;
    }

    public List<String> getParamList() {
        return paramList;
    }

    @Override
    public void translate() {
        CodeGenerator.translateFuncNode(this);
    }
}