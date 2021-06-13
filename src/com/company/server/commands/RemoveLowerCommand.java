package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.db.MusicBandHashSet;
import com.company.server.model.MusicBand;

public class RemoveLowerCommand extends Command {

    private MusicBand elem;

    public RemoveLowerCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        if (params.size() == 1){
            elem = (MusicBand) params.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        receiver.getData().removeIf(mb -> mb.compareTo(elem) < 0);
        return null;
    }
}
