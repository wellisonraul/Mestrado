package br.cin.wrmsjss3.requestor;

import java.util.HashMap;
import java.util.Map;

public class Invocation {
    private String ipAddres;
    private int portNumber;
    private int objectId;
    private String operationName;
    private HashMap<String, Object> parameters;


    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getIpAddres() {
        return ipAddres;
    }

    public void setIpAddres(String ipAddres) {
        this.ipAddres = ipAddres;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = (HashMap<String, Object>) parameters;

    }


}
