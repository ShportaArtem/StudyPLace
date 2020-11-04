package web.command.http;

import web.command.CommandResult;
import web.controller.RequestType;

public class HttpCommandResult implements CommandResult{
	private String result;
	private RequestType type;

	
	public HttpCommandResult(RequestType rq, String forward){
		this.type = rq;
		this.result = forward;
	}
	
	public RequestType getType() {
		return type;
	};

	public String getResult() {
		return result;
	};
	
	public void setResult(String forward) {
		this.result = forward;
	}

	public void setType(RequestType rq) {
		this.type = rq;
	}

}
