package com.company.server.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class Studio implements Comparable<Studio>, Serializable {//Comparable

    private static final long serialVersionUID = 1L;

    @CsvBindByName(column = "studio_name")
    private String name; /**Поле может быть null*/
    @CsvBindByName(column = "studio_address")
    private String address; /**Поле не может быть null*/

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    @Override
    public String toString() {
        return "Studio " +
                "название: " + name +
                ", адрес: " + address;
    }

    @Override
    public int compareTo(Studio o) {
        if (o == null) return 1;
        return name.compareTo((o).name);
    }
}
