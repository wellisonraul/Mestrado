package main

import (
	"github.com/mitchellh/mapstructure"
	"reflect"
	"fmt"
	"net"
)

var id = 0

// ClientProxy
type ClientProxy struct {
	ObjectID  int         `json:"object_id"`
	InvokerID int         `json:"invoker_id"`
	Host      string      `json:"host"`
	Port      string      `json:"port"`
	Inter     interface{} `json:"inter"`
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

func (s ServiceName) Bind(methodName string, clientProxy ... interface{}) {
	isFail := false
	var listOfServices []ListOfServices

	for i := range clientProxy {

		// Take the function interface
		a := reflect.ValueOf(clientProxy[i]).Elem().Interface()
		b := reflect.ValueOf(a).Field(1)
		c := b.Interface().(ClientProxy)

		// Create the parameter list
		parameters := CreateParameters(clientProxy[i])

		// Assemble the information for invocation
		clientP := reflect.ValueOf(parameters["ClientProxy"])
		objectID := int(clientP.Field(0).Int())
		invokerID := int(clientP.Field(1).Int())

		// Create an invocation object
		invocation := Invocation{methodName, s.Host, s.Port,
			objectID, invokerID, parameters}

		returnInvocation := InvokeRequestor(invocation)

		if returnInvocation == "The service "+parameters["ServiceName"].(string)+" has been published!" {
			ClientProxyPublish := reflect.ValueOf(parameters["ClientProxy"])
			publishInvoker := ListOfServices{
				c.Inter,
				parameters["ServiceName"].(string),
				0, 0, Connection{ClientProxyPublish.Field(2).String(),
					ClientProxyPublish.Field(3).String()}}
			listOfServices = append(listOfServices, publishInvoker)
		} else {
			fmt.Println(returnInvocation)
			isFail = true
		}
	}

	if !isFail {
		StartInvoker(listOfServices)
	} else {
		fmt.Println("There was a problem inserting the services!")
		fmt.Println("Please check your information or the error message")
	}

}

func (s ServiceName) Lookup(methodName string, a ... interface{}) interface{} {
	parameters := CreateParameters(a)

	invocation := Invocation{methodName, s.Host, s.Port,
		0, 0, parameters}

	repost := InvokeRequestor(invocation)
	var clientProxyInsert ClientProxy
	mapstructure.Decode(repost, &clientProxyInsert)

	return clientProxyInsert
}

func CreateParameters(args interface{}) map[string]interface{} {

	parameters := make(map[string]interface{})

	s := reflect.ValueOf(args).Elem()
	typeOfT := s.Type()

	for i := 0; i < s.NumField(); i++ {
		f := s.Field(i)
		//fmt.Printf("%s = %v\n", typeOfT.Field(i).Name, f.Interface())
		parameters[typeOfT.Field(i).Name] = f.Interface()
	}

	return parameters
}

func createProxy(objectInterface interface{}) ClientProxy {
	var clientProxy ClientProxy
	clientProxy.Host = "localhost"
	clientProxy.Port = "2345"
	clientProxy.ObjectID = id
	clientProxy.InvokerID = id
	clientProxy.Inter = objectInterface

	id++

	return clientProxy
}

func findMyIp() string {
	var ip string

	ifaces, _ := net.Interfaces()
	// handle err
	for _, i := range ifaces {
		addrs, _ := i.Addrs()
		// handle err

		for _, addr := range addrs {
			switch v := addr.(type) {
			case *net.IPNet:
				if v.IP.IsGlobalUnicast() {
					ip = v.IP.String()
				}

			}

			break
		}
	}

	return ip
}
