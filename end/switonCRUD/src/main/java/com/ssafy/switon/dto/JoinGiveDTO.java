package com.ssafy.switon.dto;

public class JoinGiveDTO {
	
	int user_id;
	int study_id;
	int point;
	int medal;
	
	public int getMedal() {
		return medal;
	}
	public void setMedal(int medal) {
		this.medal = medal;
	}
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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "JoinGiveDTO [user_id=" + user_id + ", study_id=" + study_id + ", point=" + point + ", medal=" + medal
				+ "]";
	}
	public JoinGiveDTO(int userId, int studyId) {
		this.user_id = userId;
		this.study_id = studyId;
	}
	
	public JoinGiveDTO() {
		
	}

}
