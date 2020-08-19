package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Conference;

public interface ConferenceService {
	
	// id로 회의 상세 조회
	Conference searchConferenceById(int id);
	// 스터디 아이디로 회의 상세 조회
	List<Conference> searchConference(int studyId);
	// 회의 등록
	boolean createConference(Conference conference);
	// 회의 삭제
	boolean deleteConference(int id);
}
