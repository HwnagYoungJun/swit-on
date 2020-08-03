package com.ssafy.switon.dto;

public class LowerCategory {
	int id;
	String name;
	int uppercategory_id;
	
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
	public int getUppercategory_id() {
		return uppercategory_id;
	}
	public void setUppercategory_id(int uppercategory_id) {
		this.uppercategory_id = uppercategory_id;
	}
	@Override
	public String toString() {
		return "LowerCategory [id=" + id + ", name=" + name + ", uppercategory_id=" + uppercategory_id + "]";
	}
	
	
}
