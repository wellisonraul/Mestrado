package br.cin.wrmsjss3.marshall;

import java.io.IOException;
import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import br.cin.wrmsjss3.message.Message;

public class Marshall {
    Gson gson = new Gson();

    public byte[] marshallJson(Message msgToBeMarshalled) {
        String json = gson.toJson(msgToBeMarshalled);
        return json.getBytes();
    }

    public Message unmarshallJson(byte[] msgToBeUnmarshalled) throws IOException, ClassNotFoundException {

        String messageString = new String(msgToBeUnmarshalled);
        JsonReader reader = new JsonReader(new StringReader(messageString));
        reader.setLenient(true);
        Message message = gson.fromJson(reader, Message.class);
        return message;
    }
}
