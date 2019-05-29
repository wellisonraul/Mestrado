package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private MessageHeader messageHeader;
    private MessageBody messageBody;


    public Message() {

    }

    public Message(MessageHeader messageHeader, MessageBody messageBody) {
        setMessageHeader(messageHeader);
        setMessageBody(messageBody);
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }


}
