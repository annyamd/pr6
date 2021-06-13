package com.company.server.controllers.command_control;

import java.io.Serializable;

public enum ParamType implements Serializable {
    INTEGER(true),
    STRING(true),
    MUSIC_BOX(false),
    COORDINATES(false),
    LOCAL_DATE_TIME(true),
    MUSIC_GENRE(false),
    STUDIO(false),
    FLOAT(true),
    ENUM(false),
    LONG(true),
    LIST(false);

    private static final long serialVersionUID = 1L;

    private final boolean isPrimitive;

    ParamType(boolean isPrimitive){
        this.isPrimitive = isPrimitive;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }
}
