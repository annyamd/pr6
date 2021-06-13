package com.company.server.commands;

import com.company.server.controllers.command_control.ParamBox;
import com.company.server.controllers.command_control.Param;
import com.company.server.db.MusicBandHashSet;
import com.company.server.commands.templer.Command;
import com.company.server.model.MusicBand;

public class AddCommand extends Command {

    private MusicBand elem;

    public AddCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        if (params.size() == 1){
            this.elem = (MusicBand) (params.toUnpack().get().getVal());
        }
    }

    @Override
    public ParamBox execute() {
        receiver.addWithNewId(elem);
        return null;
    }

}