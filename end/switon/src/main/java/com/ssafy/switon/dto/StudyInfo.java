package com.ssafy.switon.dto;

public class StudyInfo {
	int id;
	int user;
	int like;
	int schedule;
	int articles;
	int value;
	
	public int getArticles() {
		return articles;
	}
	public void setArticles(int articles) {
		this.articles = articles;
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int user, int like, int schedule, int articles) {
		this.value = user + (like * 2) + (schedule * 20) + (articles * 30);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}
	
	
}
