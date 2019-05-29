package br.cin.wrmsjss3.serverrequesthandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerRequestHandler {
    private int portNumber;
    private ServerSocket welcomeSocket;

    public ServerRequestHandler(int port) {
        this.portNumber = port;

        try {
            welcomeSocket = new ServerSocket(portNumber);

            while (true) {
                ThreadComuncation supportToServer = new ThreadComuncation(welcomeSocket.accept());
                Thread t = new Thread(supportToServer);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
