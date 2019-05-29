package br.cin.wrmsjss3.clientproxyregistryname;

import br.cin.wrmsjss3.clientproxy.ClientProxy;

public class NamingRecord {
    private String serviceName;
    private ClientProxy clientProxy;

    public NamingRecord(String serviceName, ClientProxy clientProxy) {
        setServiceName(serviceName);
        setClienteProxy(clientProxy);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ClientProxy getClienteProxy() {
        return clientProxy;
    }

    public void setClienteProxy(ClientProxy clienteProxy) {
        this.clientProxy = clienteProxy;
    }


}
