package com.ssafy.switon.dto;

public class Conference {
	int id;
	int study_id;
	String room_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudyId() {
		return study_id;
	}
	public void setStudyId(int study_id) {
		this.study_id = study_id;
	}
	public String getRoomCode() {
		return room_code;
	}
	public void setRoomCode(String roomCode) {
		this.room_code = roomCode;
	}
	public Conference(int id, int study_id, String roomCode) {
		super();
		this.id = id;
		this.study_id = study_id;
		this.room_code = roomCode;
	}
	public Conference() {
		super();
	}
	
	
}
