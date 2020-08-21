package com.ssafy.switon.dto;

import java.util.List;

public class StudyReturnDTO {
	
	Study study;
	String leaderName;
	Boolean isJoined;
	Boolean isLeader;
	Like like;
	List<UserSimpleDTO> members;
	List<Ranker> rankers;
	
	
	
	public List<Ranker> getRankers() {
		return rankers;
	}
	public void setRankers(List<Ranker> rankers) {
		this.rankers = rankers;
	}
	public List<UserSimpleDTO> getMembers() {
		return members;
	}
	public void setMembers(List<UserSimpleDTO> members) {
		this.members = members;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
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
		return "StudyReturnDTO [study=" + study + ", leaderName=" + leaderName + ", isJoined=" + isJoined
				+ ", isLeader=" + isLeader + ", like=" + like + ", members=" + members + ", rankers=" + rankers + "]";
	}
	
	
	
}
