package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.JoinGiveDTO;
import com.ssafy.switon.dto.Medals;
import com.ssafy.switon.dto.Ranker;

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
	// 소모임에 가입한 멤버들의 userId리스트 반환
	List<Integer> selectMemberIds(int studyId);
	// 스터디 아이디로 가입 정보들 반환
	List<Join> selectJoinsByStudyId(int studyId);
	// 스터디 아이디로 소모임 완료 표시
	int updateJoinComplete(int studyId);
	// 랭킹 3위까지 반환
	List<Ranker> selectRankers(int studyId);
	// 메달을 준다
	int giveMedal(JoinGiveDTO dto);
	// 유저 아이디로 메달 정보를 조회한다
	Medals selectMedalsByUserId(int userId);
	// 포인트를 준다.
	int addScore(JoinGiveDTO dto);
}
