package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.List;

import com.ssafy.switon.dto.BestUsersReturnDTO;
import com.ssafy.switon.dto.SearchReturnDTO;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyCardDTO;
import com.ssafy.switon.dto.StudyReturnDTO;
import com.ssafy.switon.dto.UserStudyInfoDTO;

public interface StudyService {
	
	// 모든 스터디 리스트 반환
	List<Study> searchAll();
	// id로 스터디 정보 반환
	Study search(int id);
	// id로 스터디 정보 반환 + 카테고리 정보도 반환
	StudyReturnDTO search(int id, boolean isJoined, boolean isLeader, int userId);
	// 새로운 스터디 생성, 생성된 스터디 id를 반환한다. 생성 실패시 0 반환
	int create(Study study, int userId);
	// 스터디 수정
	boolean modify(Study study);
	// 스터디 삭제
	boolean delete(int id);
	// 유저 아이디로 스터디 정보 리스트 반환 (스터디명, 현재인원, 전체인원, 기간(start_term, end_term), 모임장 이름, logo)
	List<UserStudyInfoDTO> searchUserStudies(int userId);
	// 카테고리 아이디로 스터디 검색
	List<Study> searchStudiesByLowercategory(int lowercategory_id);
	// 상위카테고리 아이디로 스터디 검색
	List<Study> searchStudiesByUppercategory(int uppercategory_id);
	// 하위 카테고리 아이디로 스터디카드 검색
	List<StudyCardDTO> searchStudyCardsByLowercategoryId(int lowercategory_id);
	// 상위카테고리 아이디로 스터디카드 검색
	List<StudyCardDTO> searchStudyCardsByUppercategory(int uppercategory_id);
	// 스터디 카드 전체 검색
	List<StudyCardDTO> searchStudyCards();
	// 키워드로 스터디 검색
	SearchReturnDTO searchStudyByKeyword(String keyword);
	// 스터디를 종료해주기, join에서도 isFinish 처리해줘야함
	String finishStudies(Timestamp timestamp);
	// 베스트 유저 3명 (글쓴 수, 좋아요 수) 찾기
	BestUsersReturnDTO findBestUsers(int studyId);
	// 검색어 입력 후 엔터를 쳤을 때는 스터디 결과를 완전히 반환
	List<StudyCardDTO> searchStudiesByKeywordEnter(String keyword);

	// 유저 아이디로 종료된 스터디 전체 조회
	List<Study> searchEndStudyByUserId(int user_id);
	// 유저 아이디로 종료되지않은 스터디 전체 조회
	List<Study> searchNotEndStudyByUserId(int user_id);

}
