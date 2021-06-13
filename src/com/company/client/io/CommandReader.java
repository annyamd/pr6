package com.company.client.io;

import com.company.server.controllers.command_control.CommandType;
import com.company.server.controllers.command_control.ParamType;
import com.company.client.exceptions.IncorrectInputException;
import com.company.client.exceptions.NoSymbolToReadException;
import com.company.server.exceptions.NoSuchCommandException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class CommandReader {
    private String[] primitiveParams;

    private final Scanner scanner;

    public CommandReader(){
        scanner = new Scanner(System.in);
    }

    public CommandReader(File file) throws IOException {
        scanner = new Scanner(file);
    }

    public CommandType readCommand() throws NoSuchCommandException, NoSymbolToReadException {
        String s = readln();
        while (s == null || s.equals("")) s = readln();
        String command;
        s = s.trim();

        if (s.contains(" ")) {
            command = s.substring(0, s.indexOf(" "));
            primitiveParams = s.substring(s.indexOf(" ") + 1).split(" ");
        } else {
            command = s;
            primitiveParams = new String[0];
        }
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new NoSuchCommandException();
        }
    }

    public String[] readPrimitiveParams() {
        return primitiveParams;
    }

    public Object objToPrimitive(ParamType paramType, String primitive) throws IncorrectInputException {
        if (primitive == null) return null;
        if (paramType.isPrimitive()) {
            try {
                switch (paramType) {
                    case INTEGER:
                        return Integer.parseInt(primitive);
                    case FLOAT:
                        return Float.parseFloat(primitive);
                    case STRING:
                        return primitive;
                    case LONG:
                        return Long.parseLong(primitive);
                }
            } catch (IllegalArgumentException ex) {
                throw new IncorrectInputException();
            }
        }
        return null;
    }

    public Object readlnPrimitiveParam(ParamType paramType) throws IncorrectInputException, NoSymbolToReadException{
        return objToPrimitive(paramType, readln());
    }

    public <T extends Enum> String readEnum(Class<T> enumClass) throws IncorrectInputException, NoSymbolToReadException{ //дженерики для проверки класса на енам
        String str = (String) objToPrimitive(ParamType.STRING, readln());
        if (str == null) return null;
        try {
            Enum.valueOf(enumClass, str);
            return str;
        } catch (IllegalArgumentException e) {
            throw new IncorrectInputException();
        }
    }

    public void close() {
        scanner.close();
    }

    public void nextLine() {
        scanner.nextLine();
    }

    public String readln() throws NoSymbolToReadException{
        if (!hasNext()) throw new NoSymbolToReadException();
        String s = scanner.nextLine();
        if (s == null || s.equals("") || s.trim().isEmpty()) return null;
        return s;
    }

    public String read(){
        String s = scanner.next();
        if (s == null || s.equals("") || s.trim().isEmpty()) return null;
        return s;
    }

    public boolean hasNext(){
        return scanner.hasNextLine();
    }
}
