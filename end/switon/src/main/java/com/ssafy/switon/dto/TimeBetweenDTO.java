package com.ssafy.switon.dto;

import java.sql.Timestamp;

public class TimeBetweenDTO {
	
	Timestamp start;
	Timestamp end;
	
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "TimeBetweenDTO [start=" + start + ", end=" + end + "]";
	}
	public TimeBetweenDTO(Timestamp start, Timestamp end) {
		this.start = start;
		this.end = end;
	}
	
	public TimeBetweenDTO() {
		
	}

}
