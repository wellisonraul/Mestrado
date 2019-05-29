package br.cin.wrmsjss3.clientproxyregistryname;

import br.cin.wrmsjss3.clientproxy.ClientProxy;
import br.cin.wrmsjss3.message.Message;
import br.cin.wrmsjss3.message.ReplyBody;
import br.cin.wrmsjss3.message.ReplyHeader;

import java.io.IOException;
import java.util.ArrayList;

public class InvokerRegistryName {

    private String methodName;
    private ArrayList<Object> parameters;
    private RegistryImpl registryImpl;

    public InvokerRegistryName() {
        registryImpl = new RegistryImpl();
    }

    public Message demultiplexerInvocator(Message messageReiceved) throws ClassNotFoundException, IOException {

        methodName = messageReiceved.getMessageBody().getRequestHeader().getOperation();
        parameters = messageReiceved.getMessageBody().getRequestBody().getParameters();

        String serviceName;
        ClientProxy clientProxy;
        ReplyBody replyBody;
        ReplyHeader replyHeader;

        switch (methodName) {

            case "bind":
                serviceName = (String) parameters.get(0);
                clientProxy = (ClientProxy) parameters.get(1);
                registryImpl.bind(serviceName, clientProxy);
                replyBody = new ReplyBody("Publish!");
                replyHeader = new ReplyHeader("no", 0, 0);
                messageReiceved.getMessageBody().setReplyBody(replyBody);
                messageReiceved.getMessageBody().setReplyHeader(replyHeader);

                break;
            case "lookup":
                serviceName = (String) parameters.get(0);
                clientProxy = registryImpl.lookup(serviceName);
                replyBody = new ReplyBody(clientProxy);
                replyHeader = new ReplyHeader("no", 0, 0);
                messageReiceved.getMessageBody().setReplyBody(replyBody);
                messageReiceved.getMessageBody().setReplyHeader(replyHeader);
            default:
                break;
        }

        return messageReiceved;
    }
}
