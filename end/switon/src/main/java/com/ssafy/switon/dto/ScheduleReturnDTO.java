package com.ssafy.switon.dto;

import java.sql.Timestamp;

import io.swagger.annotations.ApiParam;

public class ScheduleReturnDTO {
	
	@ApiParam(value = "스케줄 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "소모임 id (FK)", required = false, hidden = true)
	int study_id;
	@ApiParam(value = "소모임 이름", required = false, hidden = true)
	String study_name;
	@ApiParam(value = "스케줄 이름", required = true)
	String title;
	@ApiParam(value = "스케줄 시작시간 (예: 2020-07-20 14:00)", required = true)
	Timestamp start;
	@ApiParam(value = "스케줄 종료시간 (예: 2020-07-20 18:00)", required = true)
	Timestamp end;
	@ApiParam(value = "스케줄 배경색")
	String bg_color;
	@ApiParam(value = "스케줄 생성한 사람 id (FK)", required = false, hidden = true)
	int user_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public String getBg_color() {
		return bg_color;
	}
	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "ScheduleReturnDTO [id=" + id + ", study_id=" + study_id + ", study_name=" + study_name + ", title="
				+ title + ", start=" + start + ", end=" + end + ", bg_color=" + bg_color + ", user_id=" + user_id + "]";
	}
	public ScheduleReturnDTO(Schedule schedule) {
		this.id = schedule.getId();
		this.study_id = schedule.getStudy_id();
		this.title = schedule.getTitle();
		this.start = schedule.getStart();
		this.end = schedule.getEnd();
		this.bg_color = schedule.getBg_color();
		this.user_id = schedule.getUser_id();
	}

	
}
