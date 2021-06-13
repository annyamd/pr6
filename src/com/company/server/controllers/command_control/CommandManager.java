package com.company.server.controllers.command_control;

import com.company.server.db.MusicBandHashSet;
import com.company.server.commands.templer.Command;

import java.util.ArrayList;

public class CommandManager{ //envoker
    private CommandFactory commandFactory;
    private ArrayList<String> commands;

    public CommandManager(MusicBandHashSet musicBandHashSet){
        commandFactory = new CommandFactory();
        commandFactory.setStandardParams(musicBandHashSet,this);
        commands = new ArrayList<>();
    }

    public ParamBox execute(CommandType commandType, ParamBox paramBox){
        Command command = commandFactory.getCommand(commandType, paramBox);
        ParamBox res = command.execute();

        addCommand(commandType.toString());

        return res;
    }

    private void addCommand(String newElem){
        commands.add(newElem);
        if (commands.size() > 10){
            commands.remove(0);
        }
    }

    public ArrayList<String> getCommandList() {
        return commands;
    }
}
