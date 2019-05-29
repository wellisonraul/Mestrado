package main

import (
	"reflect"
	"github.com/mitchellh/mapstructure"
)

// ClientProxy
type ClientProxy struct {
	ObjectID  int    `json:"object_id"`
	InvokerID int    `json:"invoker_id"`
	Host      string `json:"host"`
	Port      string `json:"port"`
}

type Bind struct {
	ServiceName string
	ClientProxy ClientProxy
}

type Lookup struct {
	ServiceName string
}

type ServiceName struct {
	Host string
	Port string
}

type Invocation struct {
	MethodName string
	Host       string
	Port       string
	ObjectID   int
	InvokerID  int
	Parameters map[string]interface{}
}

func (s ServiceName) Bind(methodName string, a interface{}) {
	parameters := CreateParameters(a)

	invocation := Invocation{methodName, s.Host, s.Port,
		0, 0, parameters}

	InvokeRequestor(invocation)

}

func (s ServiceName) Lookup(methodName string, a interface{}) ClientProxy {
	parameters := CreateParameters(a)

	invocation := Invocation{methodName, s.Host, s.Port,
		0, 0, parameters}

	repost := InvokeRequestor(invocation)
	var clientProxyInsert ClientProxy
	mapstructure.Decode(repost, &clientProxyInsert)

	return clientProxyInsert
}

func ClientProxyCall(methodName string, clientProxy ClientProxy, inputParameters interface{}) interface{} {
	parameters := CreateParameters(inputParameters)

	invocation := Invocation{methodName, clientProxy.Host, clientProxy.Port,
		0, 0, parameters}

	repost := InvokeRequestor(invocation)
	return repost
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
