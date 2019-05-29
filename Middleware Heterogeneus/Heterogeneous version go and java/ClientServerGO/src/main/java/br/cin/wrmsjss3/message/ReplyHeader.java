package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class ReplyHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    private String service_context;
    private int request_id;
    private int reply_status;

    public ReplyHeader() {
    }

    public ReplyHeader(String serviceContext, int requestId, int replyStatus) {
        setService_context(serviceContext);
        setRequest_id(requestId);
        setReply_status(replyStatus);
    }

    public String getService_context() {
        return service_context;
    }

    public void setService_context(String service_context) {
        this.service_context = service_context;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getReply_status() {
        return reply_status;
    }

    public void setReply_status(int reply_status) {
        this.reply_status = reply_status;
    }


}
