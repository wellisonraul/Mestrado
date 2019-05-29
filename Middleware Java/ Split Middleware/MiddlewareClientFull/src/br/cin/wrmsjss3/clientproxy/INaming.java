package br.cin.wrmsjss3.clientproxy;

import br.cin.wrmsjss3.clientproxy.ClientProxy;

public interface INaming {
    ClientProxy lookup(String serviceName);
}
