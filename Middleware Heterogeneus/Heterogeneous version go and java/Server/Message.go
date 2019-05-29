package main

type RequestBody struct {
	Parameters map[string]interface{} `json:"parameters"`
}

type RequestHeader struct {
	Context          string `json:"context"`
	RequestID        int    `json:"request_id"`
	ResponseExpected bool   `json:"response_expected"`
	ObjectKey        int    `json:"object_key"`
	Operation        string `json:"operation"`
}

type ReplyBody struct {
	OperationResult map[string]interface{} `json:"operation_result"`
}

type ReplyHeader struct {
	ServiceContext string `json:"service_context"`
	RequestID      int    `json:"request_id"`
	ReplyStatus    int    `json:"reply_status"`
}

type MessageHeader struct {
	Magic       string `json:"magic"`
	Version     int    `json:"version"`
	ByteOrder   bool   `json:"byte_order"`
	MessageType int    `json:"message_type"`
	MessageSize int    `json:"message_size"`
}

type MessageBody struct {
	RequestHeader RequestHeader `json:"request_header"`
	RequestBody   RequestBody   `json:"request_body"`
	ReplyBody     ReplyBody     `json:"reply_body"`
	ReplyHeader   ReplyHeader   `json:"reply_header"`
}

type Message struct {
	MessageBody   MessageBody   `json:"message_body"`
	MessageHeader MessageHeader `json:"message_header"`
}
