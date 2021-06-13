package com.company.server.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private static final long serialVersionUID = 1L;

    @CsvBindByName(column = "coordinates_x")
    private float x; /**Максимальное значение поля: 645*/
    @CsvBindByName(column = "coordinates_y")
    private Integer y; /**Значение поля должно быть больше -328, Поле не может быть null*/

    public float getX() {
        return x;
    }

    public void setX(float x){
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y){
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
