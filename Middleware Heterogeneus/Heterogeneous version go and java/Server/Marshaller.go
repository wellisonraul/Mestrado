package main

import (
	"encoding/json"
)

func Marshall(messageToData Message) ([]byte, error) {
	return json.Marshal(messageToData)
}

func Unmarshall(byteSequence []byte, dataToMessage *Message) (error) {
	return json.Unmarshal(byteSequence, dataToMessage)
}
