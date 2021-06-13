package com.company.server.verifiers;

public class CoordinatesVerifier implements IVerifier{
    public static final int INT = 645;
    public static final int INT1 = -328;

    public static boolean
    verifyX(float x){
        return !(x > INT);
    }

    public static boolean verifyY(Integer y){
        return y != null && y > INT1;
    }

    @Override
    public boolean verify(Object obj) {
        return false;
    }
}
