package br.cin.wrmsjss3.marshall;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.cin.wrmsjss3.message.Message;

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
