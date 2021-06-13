package com.company.server.verifiers;

import com.company.server.model.Coordinates;
import com.company.server.model.MusicGenre;
import com.company.server.model.Studio;

public class MusicBandVerifier implements IVerifier{

    public static boolean verifyId(long id){
        return id > 0;
    }

    public static boolean verifyName(String name){
        return name != null && !name.trim().isEmpty();
    }

    public static boolean verifyCoordinates(Coordinates coordinates){
        return coordinates != null;
    }

    public static boolean verifyCreationDate(java.time.LocalDateTime localDateTime){
        return localDateTime != null;
    }

    public static boolean verifyNumberOfParticipants(int numberOfParticipants){
        return numberOfParticipants > 0;
    }

    public static boolean verifyGenre(MusicGenre musicGenre){
        return true;
    }

    public static boolean verifyStudio(Studio studio){
        return true;
    }

    @Override
    public boolean verify(Object obj) {
        return false;
    }
}
