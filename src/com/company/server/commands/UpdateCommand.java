package com.company.server.commands;

import com.company.server.controllers.command_control.ParamBox;
import com.company.server.db.MusicBandHashSet;
import com.company.server.commands.templer.Command;
import com.company.server.model.MusicBand;

public class UpdateCommand extends Command {

    private long id;
    private MusicBand elem;

    public UpdateCommand(MusicBandHashSet receiver, ParamBox paramBox){
        super(receiver, paramBox);

        if (paramBox.size() == 2) {
            this.id = (long) paramBox.toUnpack().get().getVal();
            this.elem = (MusicBand) paramBox.get().getVal();
        }
    }

    @Override
    public ParamBox execute() {

        receiver.getData().stream()
                .filter(musicBand -> musicBand.getId() == id)
                .findFirst()
                .ifPresent(musicBand -> receiver.getData().remove(musicBand));

        receiver.addWithNewId(elem);

        return null;
    }
}
