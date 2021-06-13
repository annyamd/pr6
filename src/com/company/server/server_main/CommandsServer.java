package com.company.server.server_main;

import com.company.server.exceptions.IdAlreadyExistsException;
import com.company.server.exceptions.NonexistentRequestException;
import com.company.server.connection.request_read.CommandERequest;
import com.company.server.controllers.command_control.CommandManager;
import com.company.server.controllers.command_control.CommandType;
import com.company.server.controllers.command_control.ParamBox;
import com.company.server.db.MusicBandHashSet;
import com.company.server.model.MusicBand;
import com.company.server.connection.request_read.ServerRequestReader;
import com.company.server.connection.server_connection_response.Response;
import com.company.server.connection.server_connection_response.ServerResponseWriter;
import com.company.server.connection.server_connection_set.ClientConnection;
import com.company.server.verifiers.Convertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandsServer {
    static final String inputFileExtension = "csv";
    public static int port = 7777;
    public static DataFile<MusicBand> dataFile;
    public static MusicBandHashSet musicBands;
    public static Scanner scanner;
    public static boolean toAsk = true;

    public static void main(String[] args) {

        boolean running = true;

        String fileName = args[0];
        port = Integer.parseInt(args[1]);
        if (fileName == null || !fileName.endsWith(inputFileExtension)) {
            System.out.print("Server: incorrect format of file.");
        } else {
            try {
                dataFile = new DataFile<>(fileName);
                musicBands = new MusicBandHashSet(dataFile.readDataList());
                scanner = new Scanner(System.in);
                CommandManager commandManager = new CommandManager(musicBands);
                ClientConnection connection = new ClientConnection();
                connection.setOnConnection(port);

                while (running) {
                    try {
                        CommandERequest request = ServerRequestReader.readRequest(connection.getDatagramSocket());
                        if (request == null) throw new IOException();
                        CommandType commandType = request.getCommandType();
                        ParamBox params = request.getParams();
                        ParamBox res = commandManager.execute(commandType, params);
                        ServerResponseWriter.sendResponse(connection.getDatagramSocket(), request.getAddress(), request.getPort(),
                                new Response<>(res, true, "Successful execution"));

                        if (toAsk) {
                            connection.stop();
                            checkTerminal();
                            connection.revive();
                        }

                    } catch (NonexistentRequestException e) {
                        ServerResponseWriter.sendResponse(connection.getDatagramSocket(), e.getClientAddress(), e.getClientPort(),
                                new Response<>(null, false, "Server can't read request"));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (IdAlreadyExistsException e){
                System.out.println("Can't load list from file, id repeats.");
            } finally {
                save();
            }
        }
    }

    public static void checkTerminal() {
        System.out.println("Save current state? yes/no or exit");

        if (scanner.hasNext()) {
            String ans = Convertions.convertNullableStrToNull(scanner.next());
            switch (ans.toLowerCase()){
                case "yes":
                    save();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println("Did not save.");
            }
        }
    }

    public static void save() {
        if (musicBands != null && dataFile != null) dataFile.save(new ArrayList<>(musicBands.getData()));
        System.out.println("Saved.");
    }

    public static void exit(){
        save();
        scanner.close();
        System.out.println("End of session.");
        System.exit(0);
    }

}
