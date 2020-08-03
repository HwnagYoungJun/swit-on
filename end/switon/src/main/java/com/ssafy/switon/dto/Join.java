package com.ssafy.switon.dto;

import java.sql.Date;

public class Join {
	int id;
	int user_id;
	int study_id;
	int isComplete;
	Date reg_date;
	int isLeader;
	
	
	public int getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}
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
	public int getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "Join [id=" + id + ", user_id=" + user_id + ", study_id=" + study_id + ", isComplete=" + isComplete
				+ ", reg_date=" + reg_date + ", isLeader=" + isLeader + "]";
	}
}
