package br.cin.wrmsjss3.clientproxyregistryname;

import br.cin.wrmsjss3.clientproxy.ClientProxy;

import java.util.ArrayList;

public interface INaming {
    public ArrayList<String> list();

    void bind(String serviceName, ClientProxy clientProxy);

    void rebind(String serviceName, ClientProxy clientProxy);

    void unbind(String serviceName);

    ClientProxy lookup(String serviceName);
}
