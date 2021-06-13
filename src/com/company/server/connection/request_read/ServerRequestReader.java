package com.company.server.connection.request_read;

import com.company.server.exceptions.NonexistentRequestException;
import com.company.server.connection.serialize.ObjByteConverter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ServerRequestReader {

    public static CommandERequest readRequest(DatagramSocket datagramSocket) throws NonexistentRequestException{
        byte[] buff = new byte[2000];
        DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
        try {

            datagramSocket.receive(datagramPacket);
//            System.out.println("RECEIVE " + datagramPacket.getData().length + " " + buff[0]);

//            buff = new byte[datagramPacket.getLength() - 1];
//            DatagramPacket datagramPacket0 = new DatagramPacket(buff, buff.length);
//            datagramSocket.receive(datagramPacket0);
//            System.out.println("RECEIVE " + datagramPacket0.getLength() + " " + buff[0]);
//            System.exit(0);

            CommandERequest commandERequest = ObjByteConverter.deserializeObj(buff);
            commandERequest.setAddressAndPort(datagramPacket.getAddress(), datagramPacket.getPort());
            return commandERequest;
        } catch (IOException e){
            System.out.println("Server: can't read request");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new NonexistentRequestException(datagramPacket.getAddress(), datagramPacket.getPort());
        } finally {
            Arrays.fill(buff,(byte) 0);
        }
        return null;
    }

}