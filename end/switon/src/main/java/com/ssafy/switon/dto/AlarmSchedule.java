package com.ssafy.switon.dto;

public class AlarmSchedule {
	
	String msg;
	int type;
	ScheduleInfo scheduleInfo;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public ScheduleInfo getScheduleInfo() {
		return scheduleInfo;
	}
	public void setScheduleInfo(ScheduleInfo scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}
	public AlarmSchedule(String msg, int type, ScheduleInfo scheduleInfo) {
		this.msg = msg;
		this.type = type;
		this.scheduleInfo = scheduleInfo;
	}
	@Override
	public String toString() {
		return "AlarmSchedule [msg=" + msg + ", type=" + type + ", scheduleInfo=" + scheduleInfo + "]";
	}

}
