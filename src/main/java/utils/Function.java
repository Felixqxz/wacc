package utils;

import utils.Type;

import java.util.ArrayList;
import java.util.List;

public class Function {

    /* utils.Function type is for storing basic information of a function
     * e.g. name, parameters type and return type                   */

    private String ident;
    private Type returnType;
    private List<Type> params = new ArrayList<>();

    public Function(String ident, Type returnType) {
        this.ident = ident;
        this.returnType = returnType;
    }

    public String getIdent() {
        return ident;
    }

    public void addParam(Type type) {
        params.add(type);
    }

    public int getParamNumber() {
        return params.size();
    }

    public Type getParamType(int i) {
        return params.get(i);
    }

    public Type getReturnType() {
        return returnType;
    }
}
