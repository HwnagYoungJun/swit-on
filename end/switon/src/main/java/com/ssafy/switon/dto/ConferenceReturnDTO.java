package com.ssafy.switon.dto;

import java.util.List;

public class ConferenceReturnDTO {
	
	List<Conference> conferences;
	boolean isLeader;
	
	public ConferenceReturnDTO(List<Conference> conferences, boolean isLeader) {
		this.conferences = conferences;
		this.isLeader = isLeader;
	}
	
	public ConferenceReturnDTO() {
		
	}


	@Override
	public String toString() {
		return "ConferenceReturnDTO [conferences=" + conferences + ", isLeader=" + isLeader + "]";
	}

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	
	
}
