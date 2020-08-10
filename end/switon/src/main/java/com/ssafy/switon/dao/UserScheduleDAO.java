package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.UserSchedule;

public interface UserScheduleDAO {

	// 모든 유저 스케쥴 조회
	List<UserSchedule> selectUserSchedules();
	// 유저 아이디로 스케쥴 조회
	List<UserSchedule> selectUserSchedules(int userId);
	// 유저스케쥴 생성
	int insertUserSchedule(UserSchedule userschedule);
	// 스케쥴 상태를 변경
	int updateStatus(UserSchedule userSchedule);
	
	int updateSuccess(UserSchedule userSchedule);
	// 스케쥴 삭제
	int deleteUserSchedule(int id);
	// 스케쥴 아이디로 참가한 유저 조회
	
	// 스케줄 아이디의 모든 스케줄 참석정보 삭제
	int deleteAllByScheduleId(int scheduleId);
	// 스케줄 아이디 + 유저 아이디로 참석 정보 삭제
	int deleteParticipate(UserSchedule userSchedule);
	// 스케줄 아이디 + 유저 아이디로 참석 정보 조회
	UserSchedule selectParticipate(UserSchedule userSchedule);
}
