package com.ssafy.switon.dto;

import java.util.List;

public class StudyProfileDTO {
	List<Study> finishedStudy;
	List<Study> unfinishedStudy;
	
	public StudyProfileDTO(List<Study> finishedStudy, List<Study> unfinishedStudy) {
		this.finishedStudy = finishedStudy;
		this.unfinishedStudy = unfinishedStudy;
	}

	@Override
	public String toString() {
		return "StudyProfileDTO [finishedStudy=" + finishedStudy + ", unfinishedStudy=" + unfinishedStudy + "]";
	}

	public List<Study> getFinishedStudy() {
		return finishedStudy;
	}

	public void setFinishedStudy(List<Study> finishedStudy) {
		this.finishedStudy = finishedStudy;
	}

	public List<Study> getUnfinishedStudy() {
		return unfinishedStudy;
	}

	public void setUnfinishedStudy(List<Study> unfinishedStudy) {
		this.unfinishedStudy = unfinishedStudy;
	}
	
	
}

