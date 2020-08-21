package com.ssafy.switon.dto;

public class Ranker {
	
	int user_id;
	String user_name;
	int score;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Ranker [user_id=" + user_id + ", user_name=" + user_name + ", score=" + score + "]";
	}
	
	
}
