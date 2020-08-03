package com.ssafy.switon.dto;

import io.swagger.annotations.ApiParam;

public class Board {
	
	
	
	public Board(int study_id, int type) {
		this.study_id = study_id;
		this.type = type;
	}
	int id;
	int study_id;
	int type;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", study_id=" + study_id + ", type=" + type + "]";
	}
	
	
}
