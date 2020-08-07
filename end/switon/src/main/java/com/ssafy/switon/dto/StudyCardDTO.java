package com.ssafy.switon.dto;

import java.sql.Date;

public class StudyCardDTO {
	
	int id;
	String name;
	String start_time;
	String end_time;
	int week;
	Date end_term;
	int users_current;
	int users_limit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public Date getEnd_term() {
		return end_term;
	}
	public void setEnd_term(Date end_term) {
		this.end_term = end_term;
	}
	public int getUsers_current() {
		return users_current;
	}
	public void setUsers_current(int users_current) {
		this.users_current = users_current;
	}
	public int getUsers_limit() {
		return users_limit;
	}
	public void setUsers_limit(int users_limit) {
		this.users_limit = users_limit;
	}
	public StudyCardDTO(int id, String name, String start_time, String end_time, int week, Date date,
			int users_current, int users_limit) {
		this.id = id;
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.week = week;
		this.end_term = date;
		this.users_current = users_current;
		this.users_limit = users_limit;
	}
	@Override
	public String toString() {
		return "StudyCardDTO [id=" + id + ", name=" + name + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", week=" + week + ", end_term=" + end_term + ", users_current=" + users_current + ", users_limit="
				+ users_limit + "]";
	}

	
}
