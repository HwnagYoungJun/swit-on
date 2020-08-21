package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.UserSchedule;

public interface UserScheduleDAO {

	// 모든 유저 스케쥴 조회
	List<UserSchedule> selectUserSchedules();
	// 유저 아이디로 스케쥴 조회
	UserSchedule selectUserSchedule(int userId);
	// 유저스케쥴 생성
	int insertUserSchedule(UserSchedule userschedule);
	// 스케쥴 상태를 변경
	int updateUserSchedule(int status);
	// 스케쥴 삭제
	int deleteUserSchedule(int id);
	// 스케쥴 아이디로 참가한 유저 조회
}
