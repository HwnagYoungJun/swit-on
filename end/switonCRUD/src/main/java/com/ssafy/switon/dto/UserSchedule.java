package com.ssafy.switon.dto;

public class UserSchedule {
	
	int id;
	int userId;
	int scheduleId;
	int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserSchedule [id=" + id + ", userId=" + userId + ", scheduleId=" + scheduleId + ", status=" + status
				+ "]";
	}
}
