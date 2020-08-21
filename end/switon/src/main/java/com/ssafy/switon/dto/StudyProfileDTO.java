package com.ssafy.switon.dto;

import java.util.List;

public class StudyProfileDTO {
	List<StudyWithRate> finishedStudy;
	List<StudyWithRate> unfinishedStudy;
	public List<StudyWithRate> getFinishedStudy() {
		return finishedStudy;
	}
	public void setFinishedStudy(List<StudyWithRate> finishedStudy) {
		this.finishedStudy = finishedStudy;
	}
	public List<StudyWithRate> getUnfinishedStudy() {
		return unfinishedStudy;
	}
	public void setUnfinishedStudy(List<StudyWithRate> unfinishedStudy) {
		this.unfinishedStudy = unfinishedStudy;
	}
	@Override
	public String toString() {
		return "StudyProfileDTO [finishedStudy=" + finishedStudy + ", unfinishedStudy=" + unfinishedStudy + "]";
	}
	public StudyProfileDTO(List<StudyWithRate> finishedStudy, List<StudyWithRate> unfinishedStudy) {
		this.finishedStudy = finishedStudy;
		this.unfinishedStudy = unfinishedStudy;
	}
	
	
	
}

