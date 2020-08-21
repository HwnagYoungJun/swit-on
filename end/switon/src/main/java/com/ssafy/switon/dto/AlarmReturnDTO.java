package com.ssafy.switon.dto;

import java.sql.Timestamp;

public class AlarmReturnDTO {
	
	int id;		
	int user_id;
	int type;	
	String msg;	
	int study_id; 
	int article_id;
	int board_type;
	String board_name;
	Timestamp created_at;
	boolean read;
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
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
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
	@Override
	public String toString() {
		return "AlarmReturnDTO [id=" + id + ", user_id=" + user_id + ", type=" + type + ", msg=" + msg + ", study_id="
				+ study_id + ", article_id=" + article_id + ", board_type=" + board_type + ", board_name=" + board_name
				+ ", created_at=" + created_at + ", read=" + read + "]";
	}
	public AlarmReturnDTO(Alarm alarm,
			String board_name) {
		this.id = alarm.getId();
		this.user_id = alarm.getUser_id();
		this.type = alarm.getType();
		this.msg = alarm.getMsg();
		this.study_id = alarm.getStudy_id();
		this.article_id = alarm.getArticle_id();
		this.board_type = alarm.getBoard_type();
		this.board_name = board_name;
		this.created_at = alarm.getCreated_at();
		this.read = alarm.getRead();
	}
	
}
