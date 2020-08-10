package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Study;

public interface StudyDAO {

	// 키워드로 스터디 검색
	List<Study> selectStudyByKeyword(String keyword);
}
