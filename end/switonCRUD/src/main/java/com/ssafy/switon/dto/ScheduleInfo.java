package com.ssafy.switon.dto;

public class ScheduleInfo {
	
	int schedule_id;
	String schedule_title;
	int study_id;
	String study_name;
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
	public int getStudy_id() {
		return study_id;
	}
	public void setStudy_id(int study_id) {
		this.study_id = study_id;
	}
	public String getStudy_name() {
		return study_name;
	}
	public void setStudy_name(String study_name) {
		this.study_name = study_name;
	}
	public ScheduleInfo(int schedule_id, String schedule_title, int study_id, String study_name) {
		this.schedule_id = schedule_id;
		this.schedule_title = schedule_title;
		this.study_id = study_id;
		this.study_name = study_name;
	}
	
	

}
