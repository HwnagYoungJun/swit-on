package com.ssafy.switon.dto;

import java.util.List;

public class SearchReturnDTO {
	
	List<StudySimple> Studies;
	List<LowerCategoryWithUpperName> lowercategories;
	List<UpperCategory> uppercategories;
	
	public List<StudySimple> getStudies() {
		return Studies;
	}
	public void setStudies(List<StudySimple> studies) {
		Studies = studies;
	}
	public List<LowerCategoryWithUpperName> getLowercategories() {
		return lowercategories;
	}
	public void setLowercategories(List<LowerCategoryWithUpperName> lowercategories) {
		this.lowercategories = lowercategories;
	}
	public List<UpperCategory> getUppercategories() {
		return uppercategories;
	}
	public void setUppercategories(List<UpperCategory> uppercategories) {
		this.uppercategories = uppercategories;
	}
	public SearchReturnDTO(List<StudySimple> studies, List<LowerCategoryWithUpperName> lowercategories,
			List<UpperCategory> uppercategories) {
		Studies = studies;
		this.lowercategories = lowercategories;
		this.uppercategories = uppercategories;
	}
	@Override
	public String toString() {
		return "SearchReturnDTO [Studies=" + Studies + ", lowercategories=" + lowercategories + ", uppercategories="
				+ uppercategories + "]";
	}
	
	

}
