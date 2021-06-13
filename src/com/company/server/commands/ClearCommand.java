package com.company.server.commands;

import com.company.server.controllers.command_control.ParamBox;
import com.company.server.db.MusicBandHashSet;
import com.company.server.commands.templer.Command;

public class ClearCommand extends Command {

    public ClearCommand(MusicBandHashSet receiver){
        super(receiver);
    }

    @Override
    public ParamBox execute() {
        receiver.getData().clear();
        return null;
    }
}
