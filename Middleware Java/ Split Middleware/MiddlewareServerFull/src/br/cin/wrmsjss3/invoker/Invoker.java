package br.cin.wrmsjss3.invoker;

import br.cin.wrmsjss3.marshall.Marshall;
import br.cin.wrmsjss3.message.Message;

import java.io.IOException;

public class Invoker {

    private Message msgReceived;
    private Message msgSend;
    private byte[] msgToBeMarshall;
    private Marshall marshall;
    private int objectId;

    public byte[] invoke(byte[] msgToBeUnmarshalled)
            throws ClassNotFoundException, IOException {

        marshall = new Marshall();

        msgReceived = marshall.unmarshallMessage(msgToBeUnmarshalled);
        objectId = msgReceived.getMessageBody().getRequestHeader().getObjectKey();

        switch (objectId) {
            case 1:
                InvokerFibonacci invokerFibonacci = new InvokerFibonacci();
                msgSend = invokerFibonacci.demultiplexerInvocator(msgReceived);
            default:
                break;
        }

        msgToBeMarshall = marshall.marshallMessage(msgSend);

        return msgToBeMarshall;
    }
}
