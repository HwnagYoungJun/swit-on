package com.ssafy.switon.dto;

import java.sql.Timestamp;

public class Schedule {
	
	int id;
	int studyId;
	String title;
	Timestamp start;
	Timestamp end;
	String bgColor;
	int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudyId() {
		return studyId;
	}
	public void setStudyId(int studyId) {
		this.studyId = studyId;
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
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", studyId=" + studyId + ", title=" + title + ", start=" + start + ", end=" + end
				+ ", bgColor=" + bgColor + ", userId=" + userId + "]";
	}
	
	

}
