package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.controllers.command_control.ParamType;
import com.company.server.db.MusicBandHashSet;
import com.company.server.model.MusicBand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByNumberOfParticipantsCommand extends Command {

    private int numbOfParts;

    public FilterByNumberOfParticipantsCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        if (params.size() == 1){
            numbOfParts = (int) params.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        List<MusicBand> mbList = receiver.getData().stream()
                .filter(musicBand -> musicBand.getNumberOfParticipants() == numbOfParts)
                .sorted(receiver.getNameComparator())
                .collect(Collectors.toList());

        return new ParamBox(1).add(new Param(ParamType.LIST, mbList)).toPack();
    }
}
