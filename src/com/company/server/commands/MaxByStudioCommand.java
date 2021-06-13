package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.controllers.command_control.ParamType;
import com.company.server.db.MusicBandHashSet;
import com.company.server.model.MusicBand;
import com.company.server.model.Studio;

public class MaxByStudioCommand extends Command {

    public MaxByStudioCommand(MusicBandHashSet receiver){
        super(receiver);
    }

    @Override
    public ParamBox execute() {
        MusicBand max = receiver.getData().stream()
                .max((o1, o2) -> {
                    if (o1 != null) return o1.compareTo(o2);
                    return -1;
                })
                .orElse(null);
        return new ParamBox(1).add(new Param(ParamType.MUSIC_BOX, Param.NO_NAME_FIELD, max)).toPack();
    }
}
