package com.company.server.exceptions;

import java.net.InetAddress;

public class NonexistentRequestException extends Exception{
    private final InetAddress clientAddress;
    private final int clientPort;

    public NonexistentRequestException(InetAddress clientAddress, int clientPort){
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }
}
