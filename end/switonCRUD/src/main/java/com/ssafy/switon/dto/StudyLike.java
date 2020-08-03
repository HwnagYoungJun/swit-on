package com.ssafy.switon.dto;

public class StudyLike {
	int id;
	int user_id;
	int study_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStudy_id() {
		return study_id;
	}
	public void setStudy_id(int study_id) {
		this.study_id = study_id;
	}
	@Override
	public String toString() {
		return "StudyLike [id=" + id + ", user_id=" + user_id + ", study_id=" + study_id + "]";
	}
	
}
