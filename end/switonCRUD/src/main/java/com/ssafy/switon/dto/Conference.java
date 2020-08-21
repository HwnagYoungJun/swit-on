package com.ssafy.switon.dto;

public class Conference {
	int id;
	int studyId;
	String roomCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudyId() {
		return studyId;
	}
	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public Conference(int id, int studyId, String roomCode) {
		super();
		this.id = id;
		this.studyId = studyId;
		this.roomCode = roomCode;
	}
	public Conference() {
		super();
	}
	
	
}
