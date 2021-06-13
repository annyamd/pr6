package com.company.client.client_connection;

import com.company.server.exceptions.ServerClientException;
import com.company.server.connection.request_read.CommandERequest;
import com.company.server.connection.serialize.ObjByteConverter;
import com.company.server.connection.server_connection_response.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class ServerCommunicator {
    private DatagramChannel channel;
    private ByteBuffer buffer;
    private Selector selector;

    public <T extends Serializable> Response<T> sendRequest(CommandERequest request) throws ServerClientException, IOException {
        if (channel == null) throw new ServerClientException();
        try {
            channel.register(selector, SelectionKey.OP_WRITE);
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            if (keys.isEmpty()) throw new PortUnreachableException();
            Iterator<SelectionKey> iter = keys.iterator();
            SelectionKey writeKey = iter.next();
            if (!writeKey.isValid() || !writeKey.isWritable()) throw new ServerClientException();

            buffer =  ByteBuffer.wrap(ObjByteConverter.serializeObj(request));
            channel.write(buffer);

            iter.remove();
            channel.register(selector, SelectionKey.OP_READ);

            selector.select(500);

            keys = selector.selectedKeys();
            if (keys.isEmpty()) throw new PortUnreachableException();
            iter = keys.iterator();
            SelectionKey readKey = iter.next();
            if (!readKey.isValid() || !readKey.isReadable()) throw new ServerClientException();
            Response<T> response = receiveServerResult();

            iter.remove();

            return response;

        } finally {

        }
    }

    public void setTarget(InetSocketAddress address) throws IOException {
        selector = Selector.open();
        channel = DatagramChannel.open();
        channel.connect(address);
        channel.configureBlocking(false);
    }

    private <T extends Serializable> Response<T> receiveServerResult() throws IOException, ServerClientException {
        try {
            buffer = ByteBuffer.allocate(20000);
            channel.receive(buffer);
            byte[] bytes = buffer.array();

            return ObjByteConverter.deserializeObj(bytes);
        } catch (ClassNotFoundException e) {
            throw new ServerClientException();
        } finally {
            buffer.clear();
        }
    }

    public void close() throws IOException {
        channel.close();
    }
}
