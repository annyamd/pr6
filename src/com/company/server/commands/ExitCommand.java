package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.ParamBox;
import com.company.client.io.MBTerminal;

public class ExitCommand extends Command {
    private MBTerminal terminal;

    public ExitCommand(){ //через commandManager
        this.terminal = terminal;
    }

    @Override
    public ParamBox execute() {
        terminal.exit();
        return null;
    }
}
