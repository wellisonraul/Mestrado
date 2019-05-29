package br.cin.wrmsjss3.clientproxyregistryname;

import java.io.IOException;
import br.cin.wrmsjss3.marshall.Marshall;
import br.cin.wrmsjss3.message.Message;

public class InvokerRegistry {
	private Message msgReceived;
	private Message msgSend;
	private byte [] msgToBeMarshall;
	private Marshall marshall;
	private int objectId;
	private InvokerRegistryName invokerRegistryName;
	
	public InvokerRegistry() {
		invokerRegistryName = new InvokerRegistryName();
	}
	
	
	public byte [] invoke(byte [] msgToBeUnmarshalled)
			throws ClassNotFoundException, IOException {
		
		marshall = new Marshall();

		msgReceived = marshall.unmarshallMessage(msgToBeUnmarshalled);
		objectId = msgReceived.getMessageBody().getRequestHeader().getObjectKey();
		
		switch (objectId) {
		case 0:
			msgSend = invokerRegistryName.demultiplexerInvocator(msgReceived);
			break;
		default:
			break;
		}
		
		msgToBeMarshall = marshall.marshallMessage(msgSend);
		
		return msgToBeMarshall;
	}
}
