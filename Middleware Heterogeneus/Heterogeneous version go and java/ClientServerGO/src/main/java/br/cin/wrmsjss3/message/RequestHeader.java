package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class RequestHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    private String context;
    private int request_id;
    private boolean response_expected;
    private int object_key;
    private String operation;

    public RequestHeader(String context, int requestId
            , boolean responseExpected, int objectKey, String operation) {
        setContext(context);
        setRequest_id(requestId);
        setResponse_expected(responseExpected);
        setObject_key(objectKey);
        setOperation(operation);
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getObject_key() {
        return object_key;
    }

    public void setObject_key(int object_key) {
        this.object_key = object_key;
    }

    public boolean isResponse_expected() {
        return response_expected;
    }

    public void setResponse_expected(boolean response_expected) {
        this.response_expected = response_expected;
    }
}
