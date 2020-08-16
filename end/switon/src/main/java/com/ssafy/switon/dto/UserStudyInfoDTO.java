package com.ssafy.switon.dto;

import java.sql.Date;

// 스터디 ID, 스터디명, 현재인원, 전체인원
// 기간(start_term, end_term)
// 모임장 아이디, 모임장 이름
// 로고
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
