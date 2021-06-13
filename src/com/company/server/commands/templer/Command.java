package com.company.server.commands.templer;

import com.company.server.controllers.command_control.ParamBox;
import com.company.server.db.MusicBandHashSet;

public abstract class Command {

    protected MusicBandHashSet receiver; //не должно быть равно null
    protected ParamBox params;

    public Command(){}

    public Command(MusicBandHashSet receiver, ParamBox params){
        this.receiver = receiver;
        this.params = params;
    }

    public Command(MusicBandHashSet musicBandHashSet){
        this(musicBandHashSet, null);
    }

    public abstract ParamBox execute();

}