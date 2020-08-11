package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Study;

public interface StudyDAO {
	
	// 스터디 전체 조회
	List<Study> selectStudies();
	// id로 스터디 상세 조회
	Study selectStudyById(int id);
	// 스터디 추가
	int insertStudy(Study study);
	// 스터디 수정
	int updateStudy(Study study);
	// 스터디 삭제
	int deleteStudy(int id);
	// 유저 아이디로 최근에 만든 스터디 아이디 하나 반환
	int getRecentStudyIdByUserId(int userId);
	// 유저 아이디로 가입한 스터디 전체 검색
	List<Study> selectLeaderStudiesByUserId(int userId);
	// 카테고리 아이디로 소모임 리스트 검색
	List<Study> selectStudiesByLowercategoryId(int lowercategory_id);
	// 키워드로 스터디 검색
	List<Study> selectStudyByKeyword(String keyword);
}
