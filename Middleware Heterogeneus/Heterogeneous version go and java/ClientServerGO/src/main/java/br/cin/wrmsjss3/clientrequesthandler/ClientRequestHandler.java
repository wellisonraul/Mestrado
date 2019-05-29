package br.cin.wrmsjss3.clientrequesthandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRequestHandler {
    private String host;
    private int port;

    private Socket clientSocket;
    private DataOutputStream outToServer;
    private DataInputStream inFromServer;

    public ClientRequestHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(byte[] msgToSend) throws IOException, InterruptedException {
        clientSocket = new Socket(host, port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.write(msgToSend);
        outToServer.flush();
    }

    public byte[] receive() throws IOException, InterruptedException {
        inFromServer = new DataInputStream(clientSocket.getInputStream());

        byte[] msgToReceived = new byte[100000];
        inFromServer.read(msgToReceived);
        outToServer.close();
        inFromServer.close();
        clientSocket.close();

        return msgToReceived;
    }
}
