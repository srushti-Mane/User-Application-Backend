package com.bridgelabz;

public class Response {
	Object obj;
	String msg;
	
	
	public Response(Object obj, String msg) {
		super();
		this.obj = obj;
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
