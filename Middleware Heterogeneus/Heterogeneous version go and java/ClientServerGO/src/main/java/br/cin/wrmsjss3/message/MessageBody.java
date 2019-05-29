package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class MessageBody implements Serializable {
    private static final long serialVersionUID = 1L;

    private RequestHeader request_header;
    private RequestBody request_body;
    private ReplyHeader reply_header;
    private ReplyBody reply_body;

    public MessageBody(RequestHeader requestHeader, RequestBody requestBody, ReplyHeader replyHeader
            , ReplyBody replyBody) {
        setRequest_header(requestHeader);
        setRequest_body(requestBody);
        setReply_body(replyBody);
        setReply_header(replyHeader);
    }

    public RequestHeader getRequest_header() {
        return request_header;
    }

    public void setRequest_header(RequestHeader request_header) {
        this.request_header = request_header;
    }

    public RequestBody getRequest_body() {
        return request_body;
    }

    public void setRequest_body(RequestBody request_body) {
        this.request_body = request_body;
    }

    public ReplyHeader getReply_header() {
        return reply_header;
    }

    public void setReply_header(ReplyHeader reply_header) {
        this.reply_header = reply_header;
    }

    public ReplyBody getReply_body() {
        return reply_body;
    }

    public void setReply_body(ReplyBody reply_body) {
        this.reply_body = reply_body;
    }

}
