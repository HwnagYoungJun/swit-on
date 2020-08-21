package com.ssafy.switon.dto;


public class Message {
    private String myId;
    private String otherId;
    private String message;
    
	public String getMyId() {
		return myId;
	}
	public void setMyId(String myId) {
		this.myId = myId;
	}
	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [myId=" + myId + ", otherId=" + otherId + ", message=" + message + "]";
	}
	public Message(String myId, String otherId, String message) {
		super();
		this.myId = myId;
		this.otherId = otherId;
		this.message = message;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}