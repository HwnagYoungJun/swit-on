package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.UserSchedule;

public interface UserScheduleService {

	// 모든 유저 스케쥴 조회
	List<UserSchedule> selectAll();
	// 유저 아이디로 스케쥴 조회
	UserSchedule selectUserScheduleByUserId(int userId);
	// 유저스케쥴 생성
	boolean createUserSchedule(UserSchedule userschedule);
	// 스케쥴 상태를 변경
	boolean updateUserSchedule(int status);
	// 스케쥴 삭제
	boolean deleteUserSchedule(int id);
	// 스케쥴 아이디로 참가한 유저 조회
}
