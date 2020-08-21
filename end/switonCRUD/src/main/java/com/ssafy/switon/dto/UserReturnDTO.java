package com.ssafy.switon.dto;

public class UserReturnDTO {
	
	User user;
	String token;
	public UserReturnDTO(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "UserReturnDTO [user=" + user + ", token=" + token + "]";
	}
	
	
}
