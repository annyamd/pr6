package com.company.client.io.inflaters;

import com.company.client.exceptions.InflateException;
import com.company.server.model.Coordinates;
import com.company.server.verifiers.CoordinatesVerifier;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamType;

public class CoordinatesInflater extends Inflater<Coordinates>{

    private Coordinates coordinates;

    public CoordinatesInflater() {
        super(new Param[]{
                new Param(ParamType.FLOAT, "coordinate_x"),
                new Param(ParamType.INTEGER, "coordinate_y")
        });
        reset();
    }

    @Override
    public Coordinates inflate() {
        Coordinates c = coordinates;
        reset();
        return c;
    }

    @Override
    public void setField(int i, Object val) throws InflateException {

        String name = paramsToInflate[i].getName();
        switch (name){
            case "coordinate_x":
                if (val == null) throw new InflateException();
                if (CoordinatesVerifier.verifyX((float)val)) coordinates.setX((float) val);
                else throw new InflateException();
                break;
            case "coordinate_y":
                if (CoordinatesVerifier.verifyY((Integer) val)) coordinates.setY((Integer) val);
                else throw new InflateException();
                break;
        }
    }

    @Override
    public void reset() {
        coordinates = new Coordinates();
    }
}
