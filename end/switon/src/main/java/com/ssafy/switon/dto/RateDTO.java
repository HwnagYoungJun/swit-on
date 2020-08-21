package com.ssafy.switon.dto;

public class RateDTO {
	
	int user_id;
	int study_id;
	int mine;
	int total;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStudy_id() {
		return study_id;
	}
	public void setStudy_id(int study_id) {
		this.study_id = study_id;
	}
	public int getMine() {
		return mine;
	}
	public void setMine(int mine) {
		this.mine = mine;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public RateDTO(int user_id, int study_id) {
		this.user_id = user_id;
		this.study_id = study_id;
	}
	
	public RateDTO() {
		
	}
}
