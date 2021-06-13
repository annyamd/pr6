package com.company.server.commands;

import com.company.server.commands.templer.Command;
import com.company.server.controllers.command_control.ParamBox;
import com.company.client.io.MBTerminal;

public class ExecuteScriptCommand extends Command {

    private String fileName;
    private MBTerminal mbTerminal;

    public ExecuteScriptCommand(ParamBox paramBox){
        this.mbTerminal = mbTerminal;
        if (paramBox.size() == 1){
            fileName = (String) paramBox.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        mbTerminal.executeScript(fileName);
        return null;
    }
}
