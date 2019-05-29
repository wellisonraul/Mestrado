package main

func InvokeRequestor(invocation Invocation) interface{}{

	// Create Message
	messageToSend := CreateMessage(invocation)

	// Marshall
	byteSequence, _ := Marshall(messageToSend)

	// Create Connection
	connection := Connection{invocation.Host, invocation.Port}

	//Send to Socket
	sequenceByteReturn := connection.Send(byteSequence)

	//Unmarshall
	messageToReceive := Message{}
	Unmarshall(sequenceByteReturn, &messageToReceive)

	return messageToReceive.MessageBody.ReplyBody.OperationResult["Respost"]
}

func CreateMessage(invocation Invocation) Message {
	requestHeader := RequestHeader{"",0,true,invocation.ObjectID,
		invocation.MethodName}

	requestBody := RequestBody{invocation.Parameters}

	replyBody := ReplyBody{}
	replyHeader := ReplyHeader{}


	messageBody := MessageBody{requestHeader,requestBody, replyBody,replyHeader}
	messageHeader := MessageHeader{"MIOP",1,false,0,0}

	// Object Message
	message := Message{messageBody, messageHeader}

	return message
}
