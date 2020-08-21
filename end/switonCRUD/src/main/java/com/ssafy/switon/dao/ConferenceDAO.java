package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Conference;

public interface ConferenceDAO {
	
	// 회의 전체 조회
	Conference selectConferenceById(int id);
	// 스터디 아이디로 회의 상세 조회
	List<Conference> selectConferenceByStudyId(int studyId);
	// 회의 등록
	int insertConference(Conference conference);
	// 회의 삭제
	int deleteConference(int id);
	
}
