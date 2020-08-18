package com.ssafy.switon.dto;

public class LowerCategoryWithUpperName {
	
	int id;
	String name;
	String upper_name;
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
	public String getUpper_name() {
		return upper_name;
	}
	public void setUpper_name(String upper_name) {
		this.upper_name = upper_name;
	}
	public LowerCategoryWithUpperName(LowerCategorySimpleDTO lowerCategorySimpleDTO, String upper_name) {
		this.id = lowerCategorySimpleDTO.getId();
		this.name = lowerCategorySimpleDTO.getName();
		this.upper_name = upper_name;
	}
	
	

}
