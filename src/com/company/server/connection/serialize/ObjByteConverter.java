package com.company.server.connection.serialize;

import java.io.*;

public class ObjByteConverter {
    public static byte[] serializeObj(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream serializeStream = new ObjectOutputStream(byteArrayOutputStream);
        serializeStream.writeObject(obj);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        serializeStream.close();
        return bytes;
    }

    public static <T extends Serializable> T deserializeObj(byte[] buff) throws IOException, ClassNotFoundException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buff);
        ObjectInputStream deserializeStream = new ObjectInputStream(byteArrayInputStream);
        T obj = (T) deserializeStream.readObject();
        deserializeStream.close();
        return obj;
    }
}
