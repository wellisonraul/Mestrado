package br.cin.wrmsjss3.marshall;

import br.cin.wrmsjss3.message.Message;

import java.io.*;

public class Marshall {
    public byte[] marshallMessage(Message msgToBeMarshalled) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(msgToBeMarshalled);

        return byteStream.toByteArray();

    }

    public Message unmarshallMessage(byte[] msgToBeUnmarshalled) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(msgToBeUnmarshalled);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);

        return (Message) objectStream.readObject();

    }
}
