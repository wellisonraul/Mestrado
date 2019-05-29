package main

import (
	"encoding/json"
	"reflect"
)

type Invoker struct{}

type ListOfServices struct {
	objectInterface interface{}
	MethodName      string
	InvokerID       int
	ObjectID        int
	Connection      Connection
}

var ListOfInvokerServices []ListOfServices

type ReplyUser struct {
	Respost interface{}
}

func StartInvoker(listOfServicesInput []ListOfServices) {
	// Publishing services on Invoker
	for i := range listOfServicesInput {
		ListOfInvokerServices = append(ListOfInvokerServices, listOfServicesInput[i])
	}

	// Starting the server
	a := len(ListOfInvokerServices)
	ListOfInvokerServices[a-1].Connection.ServerResquestHandler()
}

func InvokerRequest(dataInput []byte) []byte {
	var messageUnmarshall Message

	Unmarshall(dataInput, &messageUnmarshall)

	parameters := messageUnmarshall.MessageBody.RequestBody.Parameters
	operationName := messageUnmarshall.MessageBody.RequestHeader.Operation
	for i := range ListOfInvokerServices {
		if ListOfInvokerServices[i].MethodName == operationName {
			parametersReflect := make([]reflect.Value, len(parameters))
			iterator := 0
			for j := range parameters {
				parametersReflect[iterator] = reflect.ValueOf(parameters[j])
				iterator++;
			}

			reply := reflect.ValueOf(ListOfInvokerServices[i].objectInterface).MethodByName(operationName).Call(parametersReflect)
			var replyUser ReplyUser
			for i := range reply {
				replyUser = ReplyUser{reply[i].Interface()}
			}

			parametersReturn := CreateParameters(&replyUser);
			messageUnmarshall.MessageBody.ReplyBody.OperationResult = parametersReturn
		}
	}

	dataOutput, _ := json.Marshal(messageUnmarshall)
	return dataOutput
}
