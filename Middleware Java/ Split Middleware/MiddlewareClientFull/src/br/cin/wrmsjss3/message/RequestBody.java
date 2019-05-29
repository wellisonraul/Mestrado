package br.cin.wrmsjss3.message;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestBody implements Serializable {
    private ArrayList<Object> parameters = new ArrayList<Object>();
    private static final long serialVersionUID = 1L;

    public RequestBody(ArrayList<Object> parameters) {
        setParameters(parameters);
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Object> parameters) {
        this.parameters = parameters;
    }

}
