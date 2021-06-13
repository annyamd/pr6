package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.controllers.command_control.ParamType;
import com.company.server.db.MusicBandHashSet;
import com.company.server.model.MusicBand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrintDescendingCommand extends Command {

    public PrintDescendingCommand(MusicBandHashSet musicBandHashSet){
        super(musicBandHashSet);
    }

    @Override
    public ParamBox execute() {
        List<MusicBand> musicBands =
                receiver.getData().stream()
                .sorted((o1, o2) -> {
                    if (o2 != null) return o2.compareTo(o1);
                    return 1;
                })
                .collect(Collectors.toList());

        return new ParamBox(1).add(new Param(ParamType.LIST, Param.NO_NAME_FIELD, musicBands)).toPack();
    }
}
