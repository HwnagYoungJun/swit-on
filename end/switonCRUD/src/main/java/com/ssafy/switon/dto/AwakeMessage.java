package com.ssafy.switon.dto;

public class AwakeMessage {
	private String name;
	private String message;
	
	public AwakeMessage() {
		
	}
	
	public AwakeMessage(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}
}
