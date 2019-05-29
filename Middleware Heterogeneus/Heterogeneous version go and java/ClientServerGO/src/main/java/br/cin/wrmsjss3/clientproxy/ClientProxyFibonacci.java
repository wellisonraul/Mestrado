package br.cin.wrmsjss3.clientproxy;

import java.util.HashMap;
import java.util.Map;
import br.cin.wrmsjss3.aplicationclient.IFibonacci;
import br.cin.wrmsjss3.requestor.Invocation;
import br.cin.wrmsjss3.requestor.Requestor;
import br.cin.wrmsjss3.requestor.Termination;

public class ClientProxyFibonacci extends ClientProxy implements IFibonacci {
	
	@Override
	public Double FibonacciRecursive(double Number) {
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
        parameters.put("number", Number);


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
        
        // Result sent back to client
        return (Double) ter.getResult();
	}

}
