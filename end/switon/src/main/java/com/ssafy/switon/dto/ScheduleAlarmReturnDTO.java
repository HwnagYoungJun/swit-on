package com.ssafy.switon.dto;

public class ScheduleAlarmReturnDTO {
	
	int user_id;
	int schedule_id;
	String schedule_title;
	
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
	public String getSchedule_title() {
		return schedule_title;
	}
	public void setSchedule_title(String schedule_title) {
		this.schedule_title = schedule_title;
	}
	@Override
	public String toString() {
		return "ScheduleAlarmReturnDTO [user_id=" + user_id + ", schedule_id=" + schedule_id + ", schedule_title="
				+ schedule_title + "]";
	}
}
