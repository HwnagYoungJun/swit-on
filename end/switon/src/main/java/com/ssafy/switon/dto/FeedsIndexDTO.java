package com.ssafy.switon.dto;

public class FeedsIndexDTO {
	
	int user_id;
	int start_idx;
	int amount;
	
	
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int userId) {
		this.user_id = userId;
	}
	public int getStartIdx() {
		return start_idx;
	}
	public void setStartIdx(int startIdx) {
		this.start_idx = startIdx;
	}
	public int getamount() {
		return amount;
	}
	public void setamount(int amount) {
		this.amount = amount;
	}
	public FeedsIndexDTO(int userId, int startIdx, int amount) {
		this.user_id = userId;
		this.start_idx = startIdx;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "FeedsIndexDTO [userId=" + user_id + ", startIdx=" + start_idx + ", amount=" + amount + "]";
	}
}
