package com.company.server.connection.server_connection_set;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ClientConnection {
    private DatagramSocket datagramSocket;
    private int port;

    public DatagramSocket setOnConnection(int port) throws SocketException {
        this.port = port;
        datagramSocket = new DatagramSocket(port);
        return datagramSocket;
    }

    public void stop() {
        datagramSocket.close();
    }

    public void revive() throws SocketException{
        datagramSocket = new DatagramSocket(port);
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
