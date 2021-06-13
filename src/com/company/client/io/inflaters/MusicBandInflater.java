package com.company.client.io.inflaters;

import com.company.client.exceptions.InflateException;
import com.company.server.model.Coordinates;
import com.company.server.model.MusicBand;
import com.company.server.model.MusicGenre;
import com.company.server.model.Studio;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamType;
import com.company.server.verifiers.MusicBandVerifier;

import java.time.LocalDateTime;

public class MusicBandInflater extends Inflater<MusicBand>{

    private MusicBand musicBand;

    public MusicBandInflater(){
        super(new Param[]{
                new Param(ParamType.STRING, "name"),
                new Param(ParamType.COORDINATES, "coordinates"),
                new Param(ParamType.INTEGER, "number of participants"),
                new Param(ParamType.MUSIC_GENRE, "genre"),
                new Param(ParamType.STUDIO, "studio")
        });
        reset();
    }

    public void setField(int i, Object val) throws InflateException {

        String name = paramsToInflate[i].getName();
        switch (name){
            case "name":
                if (MusicBandVerifier.verifyName((String)val)) musicBand.setName((String) val);
                else throw new InflateException();
                break;
            case "coordinates":
                if (MusicBandVerifier.verifyCoordinates((Coordinates)val)) musicBand.setCoordinates((Coordinates)val);
                else throw new InflateException();
                break;
            case "number of participants":
                if (val == null) throw new InflateException();
                if (MusicBandVerifier.verifyNumberOfParticipants((int)val)) musicBand.setNumberOfParticipants((int) val);
                else throw new InflateException();
                break;
            case "genre":
                if (MusicBandVerifier.verifyGenre((MusicGenre) val)) musicBand.setGenre((MusicGenre) val);
                else throw new InflateException();
                break;
            case "studio":
                if (MusicBandVerifier.verifyStudio((Studio) val)) musicBand.setStudio((Studio) val);
                else throw new InflateException();
                break;
        }
    }

    @Override
    public MusicBand inflate() {
        MusicBand mb = musicBand;
        reset();
        return mb;
    }

    @Override
    public void reset(){
        musicBand = new MusicBand();
        musicBand.setCreationDate(LocalDateTime.now());
    }
}
