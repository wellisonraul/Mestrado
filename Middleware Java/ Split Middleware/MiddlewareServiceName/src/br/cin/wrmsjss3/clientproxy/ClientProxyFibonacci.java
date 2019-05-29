package br.cin.wrmsjss3.clientproxy;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
        return 0;
    }

}
