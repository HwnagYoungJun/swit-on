package com.ssafy.switon.dto;

import io.swagger.annotations.ApiParam;

public class StudySimple {
	
	@ApiParam(value = "모임 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "소모임 이름", required = true)
	String name;
	@ApiParam(value = "소모임 대표 이미지 주소", required = false, hidden = true)
	String logo;
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "StudySimpleDTO [id=" + id + ", name=" + name + ", logo=" + logo + "]";
	}
	
	
}
