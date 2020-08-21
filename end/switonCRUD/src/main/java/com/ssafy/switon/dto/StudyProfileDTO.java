package com.ssafy.switon.dto;

import java.util.List;

public class StudyProfileDTO {
	List<Study> finishedStudy;
	List<Study> unfinishedStudy;
	
	public StudyProfileDTO(List<Study> finishedStudy, List<Study> unfinishedStudy) {
		super();
		this.finishedStudy = finishedStudy;
		this.unfinishedStudy = unfinishedStudy;
	}
}

