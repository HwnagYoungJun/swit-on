package com.ssafy.switon.dto;

import java.sql.Timestamp;

public class Alarm {
	
	int id;		
	int user_id;
	int type;	
	String msg;	
	int study_id; 
	int article_id;
	int board_type;
	Timestamp created_at;
	boolean read;
	
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public Alarm() {
		super();
	}
	public Alarm(int user_id, int type, String msg, int study_id, int article_id, int board_type) {
		super();
		
		this.user_id = user_id;
		this.type = type;
		this.msg = msg;
		this.study_id = study_id;
		this.article_id = article_id;
		this.board_type = board_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStudy_id() {
		return study_id;
	}
	public void setStudy_id(int study_id) {
		this.study_id = study_id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
	
}
