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
	// 소모임 가입(리더)
	int insertJoinLeader(Join join);
	// 소모임 가입 정보 삭제
	int deleteJoin(int id);
	// 유저의 소모임 가입 정보 전체 조회
	List<Join> selectJoinsByUserId(int userId);
	// 소모임의 현재 가입자수 반환
	int countUsersByStudyId(int studyId);
	// 소모임 가입여부 반환(가입시 1, 미가입시 0, 오류시 그 외의 값)
	int isMember(Join join);
	// 소모임id와 userId로 레코드 삭제(탈퇴처리)
	int deleteJoinByIds(Join join);
}
