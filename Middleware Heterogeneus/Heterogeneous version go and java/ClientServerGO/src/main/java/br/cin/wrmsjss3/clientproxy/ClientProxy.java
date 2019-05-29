package br.cin.wrmsjss3.clientproxy;

public abstract class ClientProxy {

    protected String host;
    protected int port;
    protected int object_id;
    protected int invoker_id;

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

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getInvoker_id() {
        return invoker_id;
    }

    public void setInvoker_id(int invoker_id) {
        this.invoker_id = invoker_id;
    }


}
