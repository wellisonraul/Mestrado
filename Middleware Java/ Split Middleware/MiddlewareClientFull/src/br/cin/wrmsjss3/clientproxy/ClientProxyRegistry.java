package br.cin.wrmsjss3.clientproxy;

import java.util.ArrayList;

import br.cin.wrmsjss3.requestor.Invocation;
import br.cin.wrmsjss3.requestor.Requestor;
import br.cin.wrmsjss3.requestor.Termination;

public class ClientProxyRegistry extends ClientProxy implements INaming {

    private static final long serialVersionUID = 1L;


    public ClientProxyRegistry(String host, int port) {
        this.host = host;
        this.port = port;
        this.objectId = 0;
    }

    @Override
    public ClientProxy lookup(String serviceName) {
        Invocation inv = new Invocation();
        Termination ter = new Termination();
        ArrayList<Object> parameters = new ArrayList<Object>();
        class Local {
        }
        ;
        String methodName = null;
        Requestor requestor = new Requestor();

        // Constrói os paramêtros para solicitação
        methodName = Local.class.getEnclosingMethod().getName();
        parameters.add(serviceName);

        // Information sent to Requestor
        inv.setObjectId(this.getObjectId());
        inv.setIpAddres(this.getHost());
        inv.setPortNumber(this.getPort());
        inv.setOperationName(methodName);
        inv.setParameters(parameters);

        // invoke requestor
        try {
            ter = requestor.invoke(inv);
        } catch (Exception e) {
            System.out.println(e);
        }

        return (ClientProxy) ter.getResult();
    }

}
