package br.cin.wrmsjss3.clientproxyregistryname;

import java.util.ArrayList;

import br.cin.wrmsjss3.clientproxy.ClientProxy;

public interface INaming {
	public ArrayList<String> list();
	void bind(String serviceName, ClientProxy clientProxy);
	void rebind(String serviceName, ClientProxy clientProxy);
	void unbind(String serviceName);
	ClientProxy lookup(String serviceName);
}
