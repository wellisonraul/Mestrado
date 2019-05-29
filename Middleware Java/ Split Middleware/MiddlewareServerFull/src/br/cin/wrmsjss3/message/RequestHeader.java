package br.cin.wrmsjss3.message;

import java.io.Serializable;

public class RequestHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    private String context;
    private int requestId;
    private boolean responseExpected;
    private int objectKey;
    private String operation;

    public RequestHeader(String context, int requestId
            , boolean responseExpected, int objectKey, String operation) {
        setContext(context);
        setRequestId(requestId);
        setResponseExpected(responseExpected);
        setObjectKey(objectKey);
        setOperation(operation);
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public boolean isResponseExpected() {
        return responseExpected;
    }

    public void setResponseExpected(boolean responseExpected) {
        this.responseExpected = responseExpected;
    }

    public int getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(int objectKey) {
        this.objectKey = objectKey;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
