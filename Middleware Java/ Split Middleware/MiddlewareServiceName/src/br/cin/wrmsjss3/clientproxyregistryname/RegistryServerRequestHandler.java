package br.cin.wrmsjss3.clientproxyregistryname;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistryServerRequestHandler {

    private int portNumber;
    private Socket connectionSocket;
    private ServerSocket welcomeSocket;
    private DataOutputStream outToClient = null;
    private DataInputStream inFromClient = null;
    private int sizeMessageReceived;
    private int sizeMessageSend;
    private byte[] messageReceived;
    private byte[] messageSend;
    private InvokerRegistry invokerRegistry = null;

    public RegistryServerRequestHandler(int port) throws ClassNotFoundException {
        this.portNumber = port;

        invokerRegistry = new InvokerRegistry();

        try {
            welcomeSocket = new ServerSocket(portNumber);


            while (true) {
                connectionSocket = welcomeSocket.accept();

                inFromClient = new DataInputStream(connectionSocket.getInputStream());
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                sizeMessageReceived = inFromClient.readInt();
                messageReceived = new byte[sizeMessageReceived];
                inFromClient.read(messageReceived);

                messageSend = invokerRegistry.invoke(messageReceived);
                sizeMessageSend = messageSend.length;

                outToClient.writeInt(sizeMessageSend);
                outToClient.write(messageSend);

                outToClient.close();
                inFromClient.close();
                connectionSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
