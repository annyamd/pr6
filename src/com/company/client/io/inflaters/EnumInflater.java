package com.company.client.io.inflaters;

import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamType;
import com.company.client.exceptions.InflateException;

public class EnumInflater<T extends Enum> extends Inflater<T> {

    private Class<T> enumClass;
    private String string;

    public EnumInflater(String enumName, Class<T> enumClass) {
        super(new Param[]{
            new Param(ParamType.ENUM, enumName)
        });

        this.enumClass = enumClass;
    }

    public Class<T> getEnumClass() {
        return enumClass;
    }

    @Override
    public T inflate() {
        return (T) Enum.valueOf(enumClass, string);
    }

    @Override
    public void setField(int i, Object val) throws InflateException {
        if (val == null) throw new InflateException();
        if (i == 0) string = (String) val;
    }

    @Override
    public void reset() {
        string = null;
    }
}
