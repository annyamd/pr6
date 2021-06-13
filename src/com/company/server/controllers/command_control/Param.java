package com.company.server.controllers.command_control;

import java.io.Serializable;

public class Param implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static String EMPTY_VAL = "val empty";
    public final static String NO_NAME_FIELD = "no name field";

    private final ParamType type;
    private final String name;
    private Object val;

    public Param(ParamType type, String name){
        this(type, name, EMPTY_VAL);
    }

    public Param(ParamType type, String name, Object val){
        this.type = type;
        this.name = name;
        this.val = val;
    }

    public Param(ParamType paramType, Object val){
        this(paramType, NO_NAME_FIELD, val);
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public ParamType getType() {
        return type;
    }

    public Object getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "Param{" +
                "val=" + val +
                '}';
    }
}
