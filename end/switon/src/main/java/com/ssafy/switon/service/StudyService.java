package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.UserStudyInfoDTO;

public interface StudyService {
	
	// 모든 스터디 리스트 반환
	List<Study> searchAll();
	// id로 스터디 정보 반환
	Study search(int id);
	// 새로운 스터디 생성, 생성된 스터디 id를 반환한다. 생성 실패시 0 반환
	int create(Study study, int userId);
	// 스터디 수정
	boolean modify(Study study);
	// 스터디 삭제
	boolean delete(int id);
	// 유저 아이디로 스터디 정보 리스트 반환 (스터디명, 현재인원, 전체인원, 기간(start_term, end_term), 모임장 이름, logo)
	List<UserStudyInfoDTO> searchUserStudies(int userId);

}
