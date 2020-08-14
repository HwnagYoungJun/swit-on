package com.ssafy.switon.dto;

public class FeedsIndexDTO {
	
	int userId;
	int startIdx;
	int endIdx;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public int getEndIdx() {
		return endIdx;
	}
	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}
	public FeedsIndexDTO(int userId, int startIdx, int endIdx) {
		this.userId = userId;
		this.startIdx = startIdx;
		this.endIdx = endIdx;
	}
	@Override
	public String toString() {
		return "FeedsIndexDTO [userId=" + userId + ", startIdx=" + startIdx + ", endIdx=" + endIdx + "]";
	}
}
