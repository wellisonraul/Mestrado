package br.cin.wrmsjss3.aplicationclient;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import br.cin.wrmsjss3.clientproxy.ClientProxyFibonacci;
import br.cin.wrmsjss3.clientproxy.ClientProxyRegistry;

public class FibonacciClient {
	public static void main(String[] args) throws InterruptedException, IOException {
		ClientProxyRegistry serviceRegistry = new ClientProxyRegistry("localhost", 1234);

		ClientProxyFibonacci clientProxyFibonacci = 
				(ClientProxyFibonacci) serviceRegistry.lookup("FibonacciRecursive");
		String repeticoes = System.getenv("REPETICOES");
		String fibonacci = System.getenv("FIBONACCI");
		int sampleSize = Integer.parseInt(repeticoes);
		int sampleFibonacci = Integer.parseInt(fibonacci);
		long startTime;
		startTime = System.nanoTime();
		for (int i = 0; i < sampleSize; i++) {
			clientProxyFibonacci.FibonacciRecursive(sampleFibonacci);
			Thread.sleep(1);
		}
		long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

		FileWriter writer = new FileWriter("teste_java" + repeticoes + ".txt", true);
		PrintWriter saida = new PrintWriter(writer);
		saida.println(duration);
		saida.close();
		writer.close();
	}
}
