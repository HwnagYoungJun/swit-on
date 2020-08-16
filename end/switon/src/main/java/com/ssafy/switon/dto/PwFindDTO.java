package com.ssafy.switon.dto;

public class PwFindDTO {
	
	String password;
	String password2;
	String token;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "PwFindDTO [password=" + password + ", password2=" + password2 + ", token=" + token + "]";
	}
	
	

}
