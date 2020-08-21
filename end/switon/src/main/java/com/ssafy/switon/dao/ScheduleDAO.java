package com.ssafy.switon.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ssafy.switon.dto.Schedule;

public interface ScheduleDAO {
	
	List<Schedule> selectSchedules();
	List<Schedule> selectSchedulesByStudyId(int studyId);
	Schedule selectScheduleById(int id);
	int insertSchedule(Schedule schedule);
	int updateSchedule(Schedule schedule);
	int deleteSchedule(int id);
	int selectRecentScheduleId(int userId);
	int finishSchedule(int id);
	List<Integer> selectNotFinishedScheduleIds(Timestamp time);
}
