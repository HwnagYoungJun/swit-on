package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Study;

public interface StudyService {

	// 키워드로 스터디 검색
	List<Study> searchStudyByKeyword(String keyword);
}
