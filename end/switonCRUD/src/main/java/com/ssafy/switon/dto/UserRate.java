package com.ssafy.switon.dto;

public class UserRate {
	
	double participation;
	double attendance;
	public double getParticipation() {
		return participation;
	}
	public void setParticipation(double participation) {
		this.participation = participation;
	}
	public double getAttendance() {
		return attendance;
	}
	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
	@Override
	public String toString() {
		return "UserRate [participation=" + participation + ", attendance=" + attendance + "]";
	}
	public UserRate(double participation, double attendance) {
		this.participation = participation;
		this.attendance = attendance;
	}	
}
