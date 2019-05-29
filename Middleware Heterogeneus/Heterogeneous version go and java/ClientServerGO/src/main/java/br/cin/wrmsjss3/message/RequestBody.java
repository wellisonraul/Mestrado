package br.cin.wrmsjss3.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RequestBody implements Serializable {
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private static final long serialVersionUID = 1L;

    public RequestBody(HashMap<String, Object> parameters) {
        setParameters(parameters);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }


    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }


}
