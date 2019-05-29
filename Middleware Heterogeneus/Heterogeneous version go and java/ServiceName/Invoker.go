package main

import (
	"encoding/json"
	"reflect"
	"github.com/mitchellh/mapstructure"
)

func invokeServiceName(dataInput []byte) []byte {
	var marshaller Marshaller
	var messageUnmarshall Message

	marshaller.Unmarshall(dataInput, &messageUnmarshall)

	parameters := messageUnmarshall.MessageBody.RequestBody.Parameters
	if messageUnmarshall.MessageBody.RequestHeader.Operation == "bind" {
		var clientProxyInsert ClientProxy
		serviceName := parameters["ServiceName"].(string)
		mapstructure.Decode(parameters["ClientProxy"], &clientProxyInsert)

		reply := Bind(serviceName, clientProxyInsert)
		bindReply := ReplyUser{reply}

		parameters := CreateParameters(&bindReply)
		messageUnmarshall.MessageBody.ReplyBody.OperationResult = parameters

	} else if messageUnmarshall.MessageBody.RequestHeader.Operation == "lookup" {
		serviceName := parameters["ServiceName"].(string)
		reply := Lookup(serviceName)
		lookupReply := ReplyUser{reply}

		parameters := CreateParameters(&lookupReply)
		messageUnmarshall.MessageBody.ReplyBody.OperationResult = parameters
	}

	dataOutput, _ := json.Marshal(messageUnmarshall)
	return dataOutput
}

func CreateParameters(args interface{}) map[string]interface{} {

	parameters := make(map[string]interface{})

	s := reflect.ValueOf(args).Elem()
	typeOfT := s.Type()

	for i := 0; i < s.NumField(); i++ {
		f := s.Field(i)
		parameters[typeOfT.Field(i).Name] = f.Interface()
	}

	return parameters
}
