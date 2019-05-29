package br.cin.wrmsjss3.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReplyBody implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/*private float operation_result;

	public float getOperation_result() {
		return operation_result;
	}

	public void setOperation_result(float operation_result) {
		this.operation_result = operation_result;
	}*/

    private Map<String, Object> operation_result = new HashMap<String, Object>();

    public ReplyBody() {
    }

    public ReplyBody(Map<String, Object> operation_result) {
        setOperation_result(operation_result);
    }

    public Map<String, Object> getOperation_result() {
        return operation_result;
    }

    public void setOperation_result(Map<String, Object> operation_result) {
        this.operation_result = operation_result;
    }

}
