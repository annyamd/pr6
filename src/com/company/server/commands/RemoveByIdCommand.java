package com.company.server.commands;

import com.company.server.controllers.command_control.ParamBox;
import com.company.server.db.MusicBandHashSet;
import com.company.server.commands.templer.Command;
import com.company.server.model.MusicBand;

public class RemoveByIdCommand extends Command {
    private long id;

    public RemoveByIdCommand(MusicBandHashSet receiverHashSet, ParamBox paramBox){
        super(receiverHashSet, paramBox);

        if (paramBox.size() == 1){
            id = (long) paramBox.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        receiver.getData().stream()
                .filter(m -> m.getId() == id)
                .findAny().ifPresent(musicBand -> receiver.getData().remove(musicBand));
        return null;
    }
}