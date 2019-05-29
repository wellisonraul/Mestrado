package br.cin.wrmsjss3.aplicationclient;

import java.io.IOException;
import br.cin.wrmsjss3.clientproxy.ClientProxyFibonacci;
import br.cin.wrmsjss3.clientproxy.ClientProxyRegistry;

public class FibonacciClient {
	public static void main(String args[]) throws IOException, InterruptedException {
		
		ClientProxyRegistry serviceRegistry = new ClientProxyRegistry("localhost",12345);
		
		ClientProxyFibonacci clientProxyFibonacci = 
				(ClientProxyFibonacci) serviceRegistry.lookup("Fibonacci");
		
		for(int i=1; i<1000; i++) {
			clientProxyFibonacci.recursiveFibonacci(20);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
