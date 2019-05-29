package br.cin.wrmsjss3.clientproxy;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import br.cin.wrmsjss3.requestor.Invocation;
import br.cin.wrmsjss3.requestor.Requestor;
import br.cin.wrmsjss3.requestor.Termination;

public class ClientProxyRegistry extends ClientProxy {

    private Gson gson = new Gson();

    public ClientProxyRegistry(String host, int port) {
        setHost(host);
        setPort(port);
    }

    public ClientProxy lookup(String serviceName) {
        Invocation inv = new Invocation();
        Termination ter = new Termination();
        Map<String, Object> parameters = new HashMap<String, Object>();
        class Local {
        }
        ;
        String methodName = null;
        Requestor requestor = new Requestor();

        // Constrói os paramêtros para solicitação
        methodName = Local.class.getEnclosingMethod().getName();
        parameters.put("ServiceName", serviceName);

        // Information sent to Requestor
        inv.setObjectId(this.getObject_id());
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

        return (ClientProxy) clientProxy(ter.getResult());
    }

    public ClientProxy clientProxy(Object inputObject) {
        String result = inputObject.toString();
        return gson.fromJson(result, ClientProxyFibonacci.class);
    }
}
