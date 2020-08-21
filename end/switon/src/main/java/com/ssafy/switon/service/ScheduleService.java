package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.List;

import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.ScheduleReturnDTO;

public interface ScheduleService {
	
	List<Schedule> selectAll();
	List<ScheduleReturnDTO> selectSchedulesByStudyId(int studyId, int userId);
	Schedule selectScheduleById(int id);
	String createSchedule(Schedule schedule);
	boolean updateSchedule(Schedule schedule);
	boolean deleteSchedule(int id);
	String finishSchedules(Timestamp time);
	
}
