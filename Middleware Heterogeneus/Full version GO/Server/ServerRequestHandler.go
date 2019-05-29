package main

import (
	"log"
	"net"
)

func serverFailOnError(err error, msg string) {
	if err != nil {
		log.Fatalln("%s: %s", msg, err)
		return
	}
}

func (c Connection) ServerResquestHandler() {
	addres := c.Host+":"+c.Port
	listener, errListen := net.Listen("tcp", addres)
	serverFailOnError(errListen, "error while listening socket")
	log.Println("server socket initilized")

	for {
		conn, err1 := listener.Accept()
		serverFailOnError(err1, "error while accepting socket")
		defer listener.Close()
		buffer := make([]byte, 1024)
		dataSize, err2 := conn.Read(buffer)
		serverFailOnError(err2, "error while reading buffer")
		data := buffer[:dataSize]
		result := InvokerRequest(data)
		conn.Write(result)
		conn.Close()
	}
}