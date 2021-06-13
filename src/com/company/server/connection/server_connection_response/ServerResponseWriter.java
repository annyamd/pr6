package com.company.server.connection.server_connection_response;

import com.company.server.connection.serialize.ObjByteConverter;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class ServerResponseWriter {

    public static <T extends Serializable> void sendResponse(DatagramSocket socket, InetAddress address, int port, Response<T> response){
        try {
            byte[] bytes = ObjByteConverter.serializeObj(response);
            socket.send(new DatagramPacket(bytes, bytes.length, address, port));
        } catch (IOException e){
            System.out.println("Server: can't send a response.");
        }
    }

}
