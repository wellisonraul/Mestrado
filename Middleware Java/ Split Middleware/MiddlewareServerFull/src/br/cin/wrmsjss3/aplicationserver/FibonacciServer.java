package br.cin.wrmsjss3.aplicationserver;

import java.io.IOException;

import br.cin.wrmsjss3.clientproxy.ClientProxyFibonacci;
import br.cin.wrmsjss3.clientproxy.ClientProxyRegistry;

public class FibonacciServer {

    public static void main(String args[]) throws IOException, Throwable {
        ClientProxyFibonacci clientProxy = new ClientProxyFibonacci(); // ProxyFibonacci

        ClientProxyRegistry serviceRegistry = new ClientProxyRegistry("localhost", 12345);

        serviceRegistry.bind("Fibonacci", clientProxy);
    }
}
