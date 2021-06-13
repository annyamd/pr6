package com.company.server.commands;

import com.company.server.controllers.command_control.ParamBox;
import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamType;
import com.company.server.controllers.Messenger;

public class HelpCommand extends Command {
    @Override
    public ParamBox execute() {
        return new ParamBox(1).add(new Param(ParamType.STRING, Param.NO_NAME_FIELD,
                Messenger.getCommandsDescription()));
    }
}
