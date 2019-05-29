package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private MessageHeader message_header;
    private MessageBody message_body;


    public Message() {

    }

    public Message(MessageHeader message_header, MessageBody message_body) {
        setMessage_body(message_body);
        setMessage_header(message_header);
    }

    //ProtoBuf

    public MessageBody getMessage_body() {
        return message_body;
    }

    public void setMessage_body(MessageBody message_body) {
        this.message_body = message_body;
    }

    public MessageHeader getMessage_header() {
        return message_header;
    }

    public void setMessage_header(MessageHeader message_header) {
        this.message_header = message_header;
    }


}
