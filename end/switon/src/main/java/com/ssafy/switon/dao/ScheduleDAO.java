package com.ssafy.switon.dao;

import java.sql.Timestamp;
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
	// 이 사람이 가장 최근에 만든 스케줄 아이디 하나 반환
	int selectRecentScheduleId(int userId);
	// 스케줄 끝남 상태로 만들기
	int finishSchedule(int id);
	// 종료시간이 됐는데 아직 종료되지 않은 스케줄만 반환
	List<Integer> selectNotFinishedScheduleIds(Timestamp time);
}
