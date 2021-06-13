package com.company.server.exceptions;

import com.company.client.exceptions.IncorrectInputException;

public class NoSuchCommandException extends IncorrectInputException {

    @Override
    public String toString() {
        return "No such command.";
    }
}
