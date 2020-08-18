package com.ssafy.switon.dto;

public class UserScheduleSimpleDTO {
	int id;
	Schedule schedule;
	int status;
	boolean success;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "UserScheduleReturnDTO [id=" + id + ", schedule=" + schedule + ", status=" + status + ", success="
				+ success + "]";
	}
	public UserScheduleSimpleDTO(int id, Schedule schedule, int status, boolean success) {
		this.id = id;
		this.schedule = schedule;
		this.status = status;
		this.success = success;
	}
	
	
}
