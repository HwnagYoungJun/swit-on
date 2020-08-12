package com.ssafy.switon.dto;

public class ReturnMsg {
	
	String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "{ \"msg\": " +  "\"" + msg + "\"}";
	}

	public ReturnMsg(String msg) {
		this.msg = msg;
	}
	
}
