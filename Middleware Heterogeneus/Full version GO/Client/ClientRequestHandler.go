package main

import (
	"net"
	"log"
)

type Connection struct {
	Host string
	Port string
}

func clientFailOnError(err error, msg string) {
	if err != nil {
		log.Fatalln("%s: %s", msg, err)
		return
	}
}

func (crh *Connection) Send(message []byte) []byte {
	addres := crh.Host + ":" + crh.Port
	conn, err1 := net.Dial("tcp", addres)
	clientFailOnError(err1, "error while creating socket")
	defer conn.Close()
	conn.Write(message)
	buffer := make([]byte, 1024)
	data, _ := conn.Read(buffer)
	return buffer[:data]

}
