package br.cin.wrmsjss3.clientproxy;

import br.cin.wrmsjss3.aplicationserver.IFibonacci;
import br.cin.wrmsjss3.requestor.Invocation;
import br.cin.wrmsjss3.requestor.Requestor;
import br.cin.wrmsjss3.requestor.Termination;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class ClientProxyFibonacci extends ClientProxy implements IFibonacci {

    private static final long serialVersionUID = 1L;

    public ClientProxyFibonacci() {
        try {
            this.setHost(InetAddress.getLocalHost().getHostAddress());
            this.setPort((new Random().nextInt(2000) + 2000));
            this.setObjectId(1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long recursiveFibonacci(long number) {
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
        parameters.add(number);


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

        // Result sent back to client
        return (Long) ter.getResult();
    }

}
