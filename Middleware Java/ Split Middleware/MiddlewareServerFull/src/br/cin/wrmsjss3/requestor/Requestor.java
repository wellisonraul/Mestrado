package br.cin.wrmsjss3.requestor;

import br.cin.wrmsjss3.clientrequesthandler.ClientRequestHandler;
import br.cin.wrmsjss3.marshall.Marshall;
import br.cin.wrmsjss3.message.*;

import java.io.IOException;

public class Requestor {

    public Termination invoke(Invocation invocation) throws IOException, InterruptedException,
            ClassNotFoundException {

        ClientRequestHandler chr = new ClientRequestHandler(invocation.getIpAddres(), invocation.getPortNumber());
        Marshall marshaller = new Marshall();
        Termination termination = new Termination();

        byte[] msgMarshalled;
        byte[] msgToBeUnmarshalled;

        Message msgUnmarshalled = new Message();

        RequestHeader requestHeader = new RequestHeader("", 0, true, invocation.getObjectId(),
                invocation.getOperationName());
        RequestBody requestBody = new RequestBody(invocation.getParameters());
        MessageHeader messageHeader = new MessageHeader("MIOP", 0, false, 0, 0);
        MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);

        Message msgToBeMarshalled = new Message(messageHeader, messageBody);
        msgMarshalled = marshaller.marshallMessage(msgToBeMarshalled);

        chr.send(msgMarshalled);

        msgToBeUnmarshalled = chr.receive();

        msgUnmarshalled = marshaller.unmarshallMessage(msgToBeUnmarshalled);

        termination.setResult(msgUnmarshalled.getMessageBody().getReplyBody().getOperationResult());

        return termination;
    }
}
