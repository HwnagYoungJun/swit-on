package com.ssafy.switon.dto;

public class UserScheduleReturnDTO {
	
	ScheduleReturnDTO schedule;
	boolean checkIn;
	boolean checkOut;
	boolean timeover;
	boolean success;
	
	public ScheduleReturnDTO getSchedule() {
		return schedule;
	}
	public void setSchedule(ScheduleReturnDTO schedule) {
		this.schedule = schedule;
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
		return "ParticipateCheck [schedule=" + schedule + ", checkIn=" + checkIn + ", checkOut=" + checkOut
				+ ", timeover=" + timeover + ", success=" + success + "]";
	}
	
	public UserScheduleReturnDTO(ScheduleReturnDTO schedule, int status, boolean success) {
		this.schedule = schedule;
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
}
