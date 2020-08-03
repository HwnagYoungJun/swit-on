package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Join;

public interface JoinService {
	
	// 소모임 가입 정보 모두 조회
	List<Join> searchAll();
	// 소모임 가입 정보 상세 조회
	Join search(int id);
	// 소모임 가입 정보 등록
	boolean create(Join join);
	// 소모임 가입(모임장)
	boolean createLeader(Join join);
	// 소모임 가입 정보 삭제
	boolean delete(int id);
	// 유저의 소모임 가입 정보 가져오기
	List<Join> searchAllByUserId(int userId);
	// 가입여부 반환
	boolean isMember(int studyId, int userId);
	
}
