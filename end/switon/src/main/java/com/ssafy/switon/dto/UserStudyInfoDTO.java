package com.ssafy.switon.dto;

public class UserStudyInfoDTO {
	
	Study study;
	UserInfoDTO leaderInfo;
	int userCntCurrent;
	int medal;
	
	
	public int getMedal() {
		return medal;
	}
	public void setMedal(int medal) {
		this.medal = medal;
	}
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	public UserInfoDTO getLeaderInfo() {
		return leaderInfo;
	}
	public void setLeaderInfo(UserInfoDTO leaderInfo) {
		this.leaderInfo = leaderInfo;
	}
	public int getUserCntCurrent() {
		return userCntCurrent;
	}
	public void setUserCntCurrent(int userCntCurrent) {
		this.userCntCurrent = userCntCurrent;
	}
	@Override
	public String toString() {
		return "UserStudyInfoDTO [study=" + study + ", leaderInfo=" + leaderInfo + ", userCntCurrent=" + userCntCurrent
				+ ", medal=" + medal + "]";
	}
	
}
