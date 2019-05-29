package br.cin.wrmsjss3.invoker;

import br.cin.wrmsjss3.aplicationserver.FibonacciImpl;
import br.cin.wrmsjss3.message.Message;
import br.cin.wrmsjss3.message.ReplyBody;
import br.cin.wrmsjss3.message.ReplyHeader;

import java.io.IOException;
import java.util.ArrayList;

public class InvokerFibonacci {

    private String methodName;
    private ArrayList<Object> parameters;
    private FibonacciImpl fibonacciImpl;


    public Message demultiplexerInvocator(Message messageReiceved) throws ClassNotFoundException, IOException {
        fibonacciImpl = new FibonacciImpl();

        methodName = messageReiceved.getMessageBody().getRequestHeader().getOperation();
        parameters = messageReiceved.getMessageBody().getRequestBody().getParameters();

        ReplyBody replyBody;
        ReplyHeader replyHeader;

        switch (methodName) {
            case "recursiveFibonacci":
                long number = (long) parameters.get(0);
                long numberFibonacci = fibonacciImpl.recursiveFibonacci(number);
                replyBody = new ReplyBody(numberFibonacci);
                replyHeader = new ReplyHeader("no", 0, 0);
                messageReiceved.getMessageBody().setReplyBody(replyBody);
                messageReiceved.getMessageBody().setReplyHeader(replyHeader);

                break;

            default:
                break;
        }

        return messageReiceved;
    }

}
