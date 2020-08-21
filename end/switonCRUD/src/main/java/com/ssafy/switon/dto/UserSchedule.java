package com.ssafy.switon.dto;

public class UserSchedule {
	
	int id;
	int user_id;
	int schedule_id;
	int status;
	boolean success;
	
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
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "UserSchedule [id=" + id + ", user_id=" + user_id + ", schedule_id=" + schedule_id + ", status=" + status
				+ ", success=" + success + "]";
	}
	
	public UserSchedule(int user_id, int schedule_id) {
		this.user_id = user_id;
		this.schedule_id = schedule_id;
	}
	public UserSchedule(int id, int user_id, int schedule_id, int status, boolean success) {
		this.id = id;
		this.user_id = user_id;
		this.schedule_id = schedule_id;
		this.status = status;
		this.success = success;
	}
	
}
