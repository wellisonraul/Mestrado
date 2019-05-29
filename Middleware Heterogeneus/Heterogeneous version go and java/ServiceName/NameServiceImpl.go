package main

type ClientProxy struct {
	ObjectID  int    `json:"object_id"`
	InvokerID int    `json:"invoker_id"`
	Host      string `json:"host"`
	Port      string `json:"port"`
}

type Service struct {
	serviceName string
	clientProxy ClientProxy
}

type ReplyUser struct {
	Respost interface{}
}

var arrayNames []Service

func Bind(serviceName string, clientProxy ClientProxy) string {
	serviceItem := Service{serviceName, clientProxy}
	var messageReturn string

	idIsBusy := false
	for key := range arrayNames {
		serviceName := arrayNames[key].serviceName
		if serviceName == serviceItem.serviceName {
			messageReturn = "This " + serviceName + " is currently in use, please submit a new ID!"
			idIsBusy = true
		}
	}

	if !idIsBusy {
		arrayNames = append(arrayNames, serviceItem)
		messageReturn = "The service " + serviceItem.serviceName + " has been published!"
	}

	return messageReturn
}

func Lookup(serviceName string) ClientProxy {
	var returnProxy ClientProxy
	for key := range arrayNames {
		listServiceName := arrayNames[key].serviceName
		if serviceName == listServiceName {
			returnProxy = arrayNames[key].clientProxy
			return returnProxy
		}
	}

	return returnProxy
}
