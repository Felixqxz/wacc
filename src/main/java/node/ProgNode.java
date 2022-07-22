package node;

import node.statNodes.StatNode;
import codeGenerator.CodeGenerator;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.List;

public class ProgNode extends Node {

    private final List<MacroNode> macroNodes = new ArrayList<>();
    private final List<FuncNode> funcNodes = new ArrayList<>();
    private StatNode statNode;

    public List<MacroNode> getMacroNodes() {
        return macroNodes;
    }

    public List<FuncNode> getFuncNodes() {
        return funcNodes;
    }

    public StatNode getStatNode() {
        return statNode;
    }

    public void addMacroNode(MacroNode marco) {
        macroNodes.add(marco);
    }

    public void addFuncNode(FuncNode func) {
        funcNodes.add(func);
    }

    public void setStatNode(StatNode statNode) {
        this.statNode = statNode;
    }

    @Override
    public void translate() {
        CodeGenerator.translateProgNode(this);
    }
}
