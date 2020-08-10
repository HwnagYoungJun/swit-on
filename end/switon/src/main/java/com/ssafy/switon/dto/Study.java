package com.ssafy.switon.dto;

import java.sql.Date;
import java.sql.Timestamp;

import io.swagger.annotations.ApiParam;

public class Study {
	
	@ApiParam(value = "모임 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "소모임 이름", required = true)
	String name;
	@ApiParam(value = "모임장 user_id(FK), token에서 읽어올 것", required = false, hidden = true)
	int user_id; 
	@ApiParam(value = "모임설명", required = true)
	String description;
	@ApiParam(value = "비공개 모임일 경우 가입코드")
	String code;
	@ApiParam(value = "비공개 여부, true=1, false=0", required = true)
	int isPrivate;
	@ApiParam(value = "모집 여부, true=1, false=0", defaultValue = "1")
	int isRecruit;
	@ApiParam(value = "시작일, 형식: 2020-07-20", required = true)
	Date start_term;
	@ApiParam(value = "완료일, 형식: 2020-08-02", required = true)
	Date end_term;
	@ApiParam(value = "생성일", readOnly = true, required = false)
	Timestamp created_at;
	@ApiParam(value = "비트마스킹으로 월~일 표시")
	int week; // 비트마스킹으로 월~일 표시
	@ApiParam(value = "시작 시간, 형식: 14:00", required = true)
	String start_time;
	@ApiParam(value = "끝나는 시간, 형식: 18:00", required = true)
	String end_time;
	@ApiParam(value = "소모임 소분류 id(FK)", required = true)
	int lowercategory_id;
	@ApiParam(value = "소모임 대표 이미지 주소", required = false, hidden = true)
	String logo;
	@ApiParam(value = "소모임 최대 회원 수 제한", required = true)
	int users_limit;
	@ApiParam(value = "현재 인원 수", required = false, hidden = true)
	int users_current;
	@ApiParam(value = "소모임 대분류(상위카테고리) id", required = false, hidden = true)
	int uppercategory_id;
	@ApiParam(value = "소모임 소분류(상위카테고리) 이름", required = false, hidden = true)
	String uppercategory_name;
	
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
	public int getUsers_current() {
		return users_current;
	}
	public void setUsers_current(int users_current) {
		this.users_current = users_current;
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
	@Override
	public String toString() {
		return "Study [id=" + id + ", name=" + name + ", user_id=" + user_id + ", description=" + description
				+ ", code=" + code + ", isPrivate=" + isPrivate + ", isRecruit=" + isRecruit + ", start_term="
				+ start_term + ", end_term=" + end_term + ", created_at=" + created_at + ", week=" + week
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", lowercategory_id=" + lowercategory_id
				+ ", logo=" + logo + ", users_limit=" + users_limit + ", users_current=" + users_current
				+ ", uppercategory_id=" + uppercategory_id + ", uppercategory_name=" + uppercategory_name + "]";
	}
}
