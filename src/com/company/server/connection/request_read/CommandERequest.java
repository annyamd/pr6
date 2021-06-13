package com.company.server.connection.request_read;

import com.company.server.controllers.command_control.CommandType;
import com.company.server.controllers.command_control.ParamBox;

import java.io.Serializable;
import java.net.InetAddress;

public class CommandERequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private final CommandType commandType;
    private final ParamBox params;
    private int port;
    private InetAddress address;

    public CommandERequest(CommandType commandType, ParamBox paramBox){
        this.commandType = commandType;
        this.params = paramBox;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public ParamBox getParams() {
        return params;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void setAddressAndPort(InetAddress address, int port){
        this.address = address;
        this.port = port;
    }

    @Override
    public String toString() {
        return "CommandERequest{" +
                "commandType=" + commandType +
                ", params=" + params +
                ", port=" + port +
                ", address=" + address +
                '}';
    }
}
