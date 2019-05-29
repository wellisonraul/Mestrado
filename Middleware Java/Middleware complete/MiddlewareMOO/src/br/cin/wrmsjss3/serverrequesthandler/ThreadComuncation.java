package br.cin.wrmsjss3.serverrequesthandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import br.cin.wrmsjss3.invoker.Invoker;

public class ThreadComuncation implements Runnable{
	private Socket connectionSocket;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	private int sentMessageSize;
	private int receiveMessageSize;
	private byte [] msgToReceived = null;
	private byte [] msgToSend = null;
	private Invoker invoker = null;
	
	
	public ThreadComuncation(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
		invoker = new Invoker();
	}

	@Override
	public void run() {
		try {
			// Receive the data client
			receive();
			
			// invoke
			msgToSend = invoker.invoke(msgToReceived);
			
			// Send the data client
			send(msgToSend);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receive() throws IOException, Throwable{
		// Instance the object for client input.
		inFromClient = new DataInputStream(connectionSocket.getInputStream()); 
		
		// Capture and create the Message object from Message size.
		receiveMessageSize = inFromClient.readInt();
		msgToReceived = new byte[receiveMessageSize];
		
		// Receive the message to client.
		inFromClient.read(msgToReceived);
	}
	
	public void send(byte [] msgToSend) throws IOException, InterruptedException {
		// Instance the object for client output.
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		
		sentMessageSize = msgToSend.length;
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msgToSend);
		outToClient.flush();
		
		// Close the Streams
		inFromClient.close();
		outToClient.close();
		
		connectionSocket.close();
	}
	
}
