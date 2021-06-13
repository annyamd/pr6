package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.CommandManager;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.controllers.command_control.ParamType;

public class HistoryCommand extends Command {

    private CommandManager commandManager;

    public HistoryCommand(CommandManager receiver){
        this.commandManager = receiver;
    }

    @Override
    public ParamBox execute() {
        return new ParamBox(1).add(new Param(ParamType.LIST, commandManager.getCommandList())).toPack();
    }
}
