package com.company.server.verifiers;

public class Convertions {

    public static String convertNullableStrToNull(String s){
        if (s == null  || s.trim().isEmpty()){
            return null;
        }
        return s;
    }

}
