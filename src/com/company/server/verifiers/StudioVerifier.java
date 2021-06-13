package com.company.server.verifiers;

public class StudioVerifier implements IVerifier{
    public static boolean verifyName(String name){
        return true;
    }

    public static boolean verifyAddress(String address){
        return address != null && !address.trim().isEmpty();
    }

    @Override
    public boolean verify(Object obj) {
        return false;
    }
}
