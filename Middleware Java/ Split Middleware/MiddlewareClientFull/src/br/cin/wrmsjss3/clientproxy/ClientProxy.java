package br.cin.wrmsjss3.clientproxy;

import java.io.Serializable;

public abstract class ClientProxy implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String host;
    protected int port;
    protected int objectId;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

}
