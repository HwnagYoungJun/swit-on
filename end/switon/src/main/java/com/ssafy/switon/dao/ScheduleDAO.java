package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Schedule;

public interface ScheduleDAO {
	
	// 모든 스케쥴 조회
	List<Schedule> selectSchedules();
	// 스터디 아이디로 스케쥴 조회
	List<Schedule> selectSchedulesByStudyId(int studyId);
	// id로 스케쥴 상세 정보 조회
	Schedule selectScheduleById(int id);
	// 스케쥴 생성
	int insertSchedule(Schedule schedule);
	// 스케쥴 수정
	int updateSchedule(Schedule schedule);
	// 스케쥴 하나 삭제
	int deleteSchedule(int id);
}
