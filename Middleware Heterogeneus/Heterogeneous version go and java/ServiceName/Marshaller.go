package main

import "encoding/json"


type Marshaller struct {}

func (Marshaller) Marshall(messageToData Message) ([]byte, error){
	return json.Marshal(messageToData)
}

func (Marshaller) Unmarshall(byteSequence []byte, dataToMessage *Message) (error) {
	return json.Unmarshal(byteSequence, dataToMessage )
}

