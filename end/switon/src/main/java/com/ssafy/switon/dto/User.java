package com.ssafy.switon.dto;

public class User {
	
	int id;
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
	public User(int id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}
	public User(UserInfoDTO user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
