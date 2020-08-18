package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.ParticipateInfo;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.UserRate;
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.dto.UserScheduleReturnDTO;
import com.ssafy.switon.dto.UserScheduleSimpleDTO;
import com.ssafy.switon.dto.UserSimpleDTO;

public interface UserScheduleService {

	// 모든 유저 스케쥴 조회
	List<UserSchedule> selectAll();
	// 유저 아이디로 스케쥴 조회
	List<UserSchedule> selectUserScheduleByUserId(int userId);
	// 유저스케쥴 생성
	String createUserSchedule(UserSchedule userschedule);
	// 스케쥴 상태를 변경
	boolean changeStatus(int userId, int scheduleId, int status);
	// 성공 여부를 변경
	boolean changeSuccess(int userId, int scheduleId, boolean success);
	// 스케쥴 삭제
	boolean deleteUserSchedule(int id);
	// 유저아이디로 참가한 스케쥴 리스트 조회
	List<UserScheduleReturnDTO> getUserSchedules(int userId);
	// 유저의 스케쥴 참가 취소
	boolean cancelParticipate(int userId, int scheduleId);
	// 유저의 참석 정보 조회
	UserSchedule getParticipate(int userId, int scheduleId);
	// 유저의 참석 정보 상세하게 반환
	ParticipateInfo getParticipateInfo(int userId, int scheduleId);
	// 참가자 정보들 반환
	List<UserSimpleDTO> searchParticipants(int scheduleId);
	// 유저의 참여율과 출석률을 반환한다.
	UserRate getUserParticipateRate(int userId, int studyId);
}
