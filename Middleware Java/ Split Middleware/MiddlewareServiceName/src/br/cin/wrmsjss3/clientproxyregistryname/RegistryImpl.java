package br.cin.wrmsjss3.clientproxyregistryname;

import br.cin.wrmsjss3.clientproxy.ClientProxy;

import java.util.ArrayList;

public class RegistryImpl implements INaming {

    ArrayList<NamingRecord> namingRecords = null;

    public RegistryImpl() {
        namingRecords = new ArrayList<NamingRecord>();
    }

    @Override
    public void bind(String serviceName, ClientProxy clientProxy) {
        NamingRecord namingRecord = new NamingRecord(serviceName, clientProxy);
        namingRecords.add(namingRecord);
    }

    @Override
    public void rebind(String serviceName, ClientProxy clientProxy) {
        for (NamingRecord naming : namingRecords) {
            if (naming.getServiceName().equals(serviceName)) {
                naming.setServiceName(serviceName);
                naming.setClienteProxy(clientProxy);
            }
        }
    }

    @Override
    public void unbind(String serviceName) {
        for (NamingRecord naming : namingRecords) {
            if (naming.getServiceName().equals(serviceName)) {
                namingRecords.remove(naming);
            }
        }
    }

    @Override
    public ClientProxy lookup(String serviceName) {
        ClientProxy clientProxy = null;
        for (NamingRecord naming : namingRecords) {

            if (naming.getServiceName().equals(serviceName)) {
                clientProxy = naming.getClienteProxy();

            }
        }

        return clientProxy;
    }

    @Override
    public ArrayList<String> list() {
        ArrayList<String> listServiceNames = new ArrayList<String>();
        for (NamingRecord naming : namingRecords) {
            listServiceNames.add(naming.getServiceName());
        }

        return listServiceNames;
    }
}
