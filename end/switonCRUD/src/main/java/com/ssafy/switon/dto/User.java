package com.ssafy.switon.dto;

public class User {
	
	String email;
	String name;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	public User(UserInfoDTO user) {
		this.email = user.getEmail();
		this.name = user.getName();
	}
	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", name=" + name + "]";
	}
	
	

}
