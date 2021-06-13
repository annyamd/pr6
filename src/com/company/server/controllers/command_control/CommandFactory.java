package com.company.server.controllers.command_control;

import com.company.server.commands.*;
import com.company.server.commands.templer.Command;
import com.company.server.db.MusicBandHashSet;

/**
 * the factory of Commands
 *
 * @see AddCommand
 * @see ClearCommand
 * @see ExitCommand
 * @see HelpCommand
 * @see InfoCommand
 * @see ShowCommand
 * @see UpdateCommand
 * @see HistoryCommand
 * @see RemoveByIdCommand
 * @see RemoveLowerCommand
 * @see RemoveGreaterCommand
 * @see MaxByStudioCommand
 * @see ExecuteScriptCommand
 * @see PrintDescendingCommand
 * @see FilterByNumberOfParticipantsCommand
 */

public class CommandFactory {

    private MusicBandHashSet musicBandHashSet;
    private CommandManager commandManager;

    /**
     * Creates and returns an object of command defined by {@code CommandType}. It gives a command {@code ParamBox} with
     * parameters it needs and required standard parameters (receivers).
     *
     * @param commandType type of command which is needed to create
     * @param paramBox    required parameters
     * @return a {@code Command}
     */

    public Command getCommand(CommandType commandType, ParamBox paramBox) {
        switch (commandType) {
            case ADD:
                return new AddCommand(musicBandHashSet, paramBox);
            case CLEAR:
                return new ClearCommand(musicBandHashSet);
            case HELP:
                return new HelpCommand();
            case INFO:
                return new InfoCommand(musicBandHashSet);
            case SHOW:
                return new ShowCommand(musicBandHashSet);
            case UPDATE:
                return new UpdateCommand(musicBandHashSet, paramBox);
            case HISTORY:
                return new HistoryCommand(commandManager);
            case REMOVE_BY_ID:
                return new RemoveByIdCommand(musicBandHashSet, paramBox);
            case REMOVE_LOWER:
                return new RemoveLowerCommand(musicBandHashSet, paramBox);
            case MAX_BY_STUDIO:
                return new MaxByStudioCommand(musicBandHashSet);
            case EXECUTE_SCRIPT:
                return new ExecuteScriptCommand(paramBox);
            case REMOVE_GREATER:
                return new RemoveGreaterCommand(musicBandHashSet, paramBox);
            case PRINT_DESCENDING:
                return new PrintDescendingCommand(musicBandHashSet);
            case FILTER_BY_NUMBER_OF_PARTICIPANTS:
                return new FilterByNumberOfParticipantsCommand(
                        musicBandHashSet, paramBox);
        }
        return null;
    }

    /**
     * Sets receivers, which may be needed when creating a {@code Command}
     *
     * @param musicBandHashSet
     * @param commandManager
     */

    public void setStandardParams(MusicBandHashSet musicBandHashSet, CommandManager commandManager) { //либо сохранение параметров с помощью переопределения get методов, а сам класс фабрики абстрактный
        this.musicBandHashSet = musicBandHashSet;
        this.commandManager = commandManager;
    }
}
