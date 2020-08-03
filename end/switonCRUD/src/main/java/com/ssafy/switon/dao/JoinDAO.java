package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Join;

public interface JoinDAO {
	// 소모임 가입 정보 전체 조회
	List<Join> selectJoins();
	// 소모임 가입 정보 상세 조회
	Join selectJoin(int id);
	// 소모임 가입 정보 등록
	int insertJoin(Join join);
	// 소모임 가입 정보 삭제
	int deleteJoin(int id);
}
