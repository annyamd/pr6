package com.company.client.io.inflaters;

import com.company.client.exceptions.InflateException;
import com.company.server.controllers.command_control.Param;

public abstract class Inflater<T> {
    Param[] paramsToInflate;

    public Inflater(Param[] paramsToInflate){
        this.paramsToInflate = paramsToInflate;
    }

    public Param[] getParamsToInflate() {
        return paramsToInflate;
    }

    public abstract T inflate();

    public abstract void setField(int i, Object val) throws InflateException;

    public Param getParam(int index){
        return paramsToInflate[index];
    }

    public int getParamCount(){
        return paramsToInflate.length;
    }

    public abstract void reset();

}