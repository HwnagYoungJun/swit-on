package com.ssafy.switon.dto;

public class StudyReturnDTO {
	
	Study study;
	Boolean isJoined;
	Boolean isLeader;
	Like like;
	
	
	
	public Like getLike() {
		return like;
	}
	public void setLike(Like like) {
		this.like = like;
	}
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	public Boolean getIsJoined() {
		return isJoined;
	}
	public void setIsJoined(Boolean isJoined) {
		this.isJoined = isJoined;
	}
	public Boolean getIsLeader() {
		return isLeader;
	}
	public StudyReturnDTO(Study study, Boolean isJoined, Boolean isLeader, Like like) {
		this.study = study;
		this.isJoined = isJoined;
		this.isLeader = isLeader;
		this.like = like;
	}
	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}
	@Override
	public String toString() {
		return "StudyReturnDTO [study=" + study + ", isJoined=" + isJoined + ", isLeader=" + isLeader + "]";
	}
	
}
