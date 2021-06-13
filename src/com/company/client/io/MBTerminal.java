package com.company.client.io;

import com.company.client.exceptions.IncorrectInputException;
import com.company.client.exceptions.InflateException;
import com.company.server.connection.request_read.CommandERequest;
import com.company.client.client_connection.ServerCommunicator;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.controllers.command_control.CommandType;
import com.company.server.controllers.command_control.Param;
import com.company.server.controllers.command_control.ParamType;
import com.company.server.exceptions.*;
import com.company.client.io.inflaters.*;
import com.company.server.model.MusicGenre;
import com.company.server.connection.server_connection_response.Response;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;

public class MBTerminal {
    public int port;

    private IOHandler handler;
    private ServerCommunicator serverCommunicator;
    private boolean toContinue = true;
    private ExceptionListener exceptionListener = e -> toContinue = false;
    private boolean isCommandCompleted;
    private Writer resultWriter;

    public MBTerminal(int port) {
        this.port = port;
        init();
    }

    public void start() {

        while (toContinue) {
            handler.getWriter().writeln("Write next command: ");

            try {
                CommandType cmd = handler.getReader().readCommand();

                isCommandCompleted = false;
                ParamType[] params = cmd.getParamTypes();
                ParamBox paramBox = new ParamBox(params.length);
                Object val;

                int primitivesCount = cmd.getPrimitivesCount();
                String[] primitives = handler.getReader().readPrimitiveParams();
                if (primitives.length != primitivesCount) throw new IncorrectInputException();

                //reading primitive params
                for (int i = 0; i < primitives.length; i++) {
                    val = handler.getReader().objToPrimitive(params[i], primitives[i]);
                    if (val == null) throw new IncorrectInputException();
                    paramBox.add(new Param(params[i], val));
                }

                //reading object params
                for (int i = primitivesCount; i < paramBox.size(); i++) {
                    ParamType type = params[i];
                    val = handler.readObject(type);
                    paramBox.add(new Param(type, val));
                }

                if (cmd.equals(CommandType.EXIT)){
                    exit();
                    continue;
                } else if (cmd.equals(CommandType.EXECUTE_SCRIPT)) {
                    String name = (String) paramBox.get().getVal();
                    executeScript(name);
                    continue;
                }

                //call to server - serialize command, its params, send and wait to get result
                Response<ParamBox> serverResponse = serverCommunicator.sendRequest(new CommandERequest(cmd, paramBox));

                ParamBox res = serverResponse.geResponseObj();

                isCommandCompleted = true;
                resultWriter.writeParamBox("Result", res);
                handler.getWriter().writeln("Command completed execution.");
            } catch (Exception e) {
                getExceptionListener().onExceptionGet(e);
            }
        }
    }

    private void init() {

        try {
            serverCommunicator = new ServerCommunicator();
            serverCommunicator.setTarget(new InetSocketAddress("localhost", port));
        } catch (IOException e) {
            exit();
        }

        this.resultWriter = new Writer();

        this.exceptionListener = e -> {
            if (e instanceof NoSuchCommandException) {
                handler.getWriter().writeln(e.toString());
            } else if (e instanceof IncorrectInputException | e instanceof InflateException) {
                handler.getWriter().writeln(e.toString() + " Try again.");
            } else if (e instanceof PortUnreachableException){
                handler.getWriter().writeln("Can't connect with server. Try again.");
            } else if (e instanceof ServerClientException || e instanceof IOException){
                handler.getWriter().writeln("Problem client|server");
                e.printStackTrace();
                exit();
            }
        };

        handler = new IOHandler(getExceptionListener()) {
            @Override
            public Inflater getInflater(ParamType type) {
                switch (type) {
                    case MUSIC_BOX:
                        return new MusicBandInflater();
                    case COORDINATES:
                        return new CoordinatesInflater();
                    case STUDIO:
                        return new StudioInflater();
                    case MUSIC_GENRE:
                        return new EnumInflater<>("music genre", MusicGenre.class);
                }
                return null;
            }
        };
    }

    public void exit() {
        toContinue = false;
        handler.getReader().close();
        try {
            serverCommunicator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeScript(String fileName) {

        try {
            CommandReader reader = new CommandReader(new File(fileName));

            Writer writer = new Writer() {
                @Override
                public void write(String str) {
                }

                @Override
                public void writeln(String str) {
                }
            };

            handler.setReader(reader);
            handler.setWriter(writer);

            ExceptionListener mainEL = getExceptionListener();
            exceptionListener = e -> toContinue = false;

            handler.setExceptionListener(getExceptionListener());

            start();

            exceptionListener = mainEL;
            handler.setExceptionListener(mainEL);
            handler.setReader(new CommandReader());
            handler.setWriter(new Writer());

            if (toContinue || isCommandCompleted) {
                handler.getWriter().writeln("Script completed execution successfully.");
            } else handler.getWriter().writeln("Script execution error.");

            toContinue = true;

        } catch (IOException e) {
            handler.getWriter().writeln("Reading file error.");
        }
    }

    public ExceptionListener getExceptionListener() {
        return exceptionListener;
    }

    interface ExceptionListener {
        void onExceptionGet(Exception e);
    }
}
