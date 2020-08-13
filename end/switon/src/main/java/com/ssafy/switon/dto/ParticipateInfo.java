package com.ssafy.switon.dto;

public class ParticipateInfo {
	boolean isJoin;
	boolean checkIn;
	boolean checkOut;
	boolean timeover;
	boolean success;
	
	public boolean isJoin() {
		return isJoin;
	}
	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}
	public boolean isCheckIn() {
		return checkIn;
	}
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}
	public boolean isCheckOut() {
		return checkOut;
	}
	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}
	public boolean isTimeover() {
		return timeover;
	}
	public void setTimeover(boolean timeover) {
		this.timeover = timeover;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "ParticipateInfo [checkIn=" + checkIn + ", checkOut=" + checkOut + ", timeover=" + timeover
				+ ", success=" + success + "]";
	}
	public ParticipateInfo(int status, boolean success) {
		isJoin = true;
		checkIn = false;
		checkOut = false;
		timeover = false;
		if((status & 1) != 0) {
			checkIn = true;
		}
		if((status & (1 << 1)) != 0) {
			checkOut = true;
		}
		if((status & (1 << 2)) != 0) {
			timeover = true;
		}
		this.success = success;
	}
	public ParticipateInfo() {
	}
	
	
	
	
}
