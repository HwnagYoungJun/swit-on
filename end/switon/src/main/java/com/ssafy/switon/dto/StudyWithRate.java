package com.ssafy.switon.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class StudyWithRate {
	
	int id;
	String name;
	int user_id; 
	String description;
	String code;
	int isPrivate;
	int isRecruit;
	Date start_term;
	Date end_term;
	Timestamp created_at;
	int week; // 비트마스킹으로 월~일 표시
	String start_time;
	String end_time;
	int lowercategory_id;
	String logo;
	int users_limit;
	int users_current;
	int uppercategory_id;
	String uppercategory_name;
	
	UserRate rate;

	public StudyWithRate(Study study, UserRate rate) {
		this.id = study.getId();
		this.name = study.getName();
		this.user_id = study.getUser_id();
		this.description = study.getDescription();
		this.code = study.getCode();
		this.isPrivate = study.getIsPrivate();
		this.isRecruit = study.getIsRecruit();
		this.start_term = study.getStart_term();
		this.end_term = study.getEnd_term();
		this.created_at = study.getCreated_at();
		this.week = study.getWeek();
		this.start_time = study.getStart_time();
		this.end_time = study.getEnd_time();
		this.lowercategory_id = study.getLowercategory_id();
		this.logo = study.getLogo();
		this.users_limit = study.getUsers_limit();
		this.users_current = study.getUsers_current();
		this.uppercategory_id = study.getUppercategory_id();
		this.uppercategory_name = study.getUppercategory_name();
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "StudyWithRate [id=" + id + ", name=" + name + ", user_id=" + user_id + ", description=" + description
				+ ", code=" + code + ", isPrivate=" + isPrivate + ", isRecruit=" + isRecruit + ", start_term="
				+ start_term + ", end_term=" + end_term + ", created_at=" + created_at + ", week=" + week
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", lowercategory_id=" + lowercategory_id
				+ ", logo=" + logo + ", users_limit=" + users_limit + ", users_current=" + users_current
				+ ", uppercategory_id=" + uppercategory_id + ", uppercategory_name=" + uppercategory_name + ", rate="
				+ rate + "]";
	}

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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getIsRecruit() {
		return isRecruit;
	}

	public void setIsRecruit(int isRecruit) {
		this.isRecruit = isRecruit;
	}

	public Date getStart_term() {
		return start_term;
	}

	public void setStart_term(Date start_term) {
		this.start_term = start_term;
	}

	public Date getEnd_term() {
		return end_term;
	}

	public void setEnd_term(Date end_term) {
		this.end_term = end_term;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
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

	public int getLowercategory_id() {
		return lowercategory_id;
	}

	public void setLowercategory_id(int lowercategory_id) {
		this.lowercategory_id = lowercategory_id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getUsers_limit() {
		return users_limit;
	}

	public void setUsers_limit(int users_limit) {
		this.users_limit = users_limit;
	}

	public int getUsers_current() {
		return users_current;
	}

	public void setUsers_current(int users_current) {
		this.users_current = users_current;
	}

	public int getUppercategory_id() {
		return uppercategory_id;
	}

	public void setUppercategory_id(int uppercategory_id) {
		this.uppercategory_id = uppercategory_id;
	}

	public String getUppercategory_name() {
		return uppercategory_name;
	}

	public void setUppercategory_name(String uppercategory_name) {
		this.uppercategory_name = uppercategory_name;
	}

	public UserRate getRate() {
		return rate;
	}

	public void setRate(UserRate rate) {
		this.rate = rate;
	}

}
