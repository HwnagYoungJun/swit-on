package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.RateDTO;
import com.ssafy.switon.dto.ScheduleAlarmReturnDTO;
import com.ssafy.switon.dto.TimeBetweenDTO;
import com.ssafy.switon.dto.UserSchedule;

public interface UserScheduleDAO {

	List<UserSchedule> selectUserSchedules();
	List<UserSchedule> selectUserSchedules(int userId);
	int insertUserSchedule(UserSchedule userschedule);
	int updateStatus(UserSchedule userSchedule);
	int updateSuccess(UserSchedule userSchedule);
	int deleteUserSchedule(int id);
	List<UserSchedule> selectUserSchedulesByScheduleId(int scheduleId);
	int deleteAllByScheduleId(int scheduleId);
	int deleteParticipate(UserSchedule userSchedule);
	UserSchedule selectParticipate(UserSchedule userSchedule);
	int updateSchedule(UserSchedule userSchedule);
	List<ScheduleAlarmReturnDTO> searchUsersToAlarm(TimeBetweenDTO dto);
	List<ScheduleAlarmReturnDTO> searchUsersToAlarm2(TimeBetweenDTO dto);
	RateDTO selectParticipationRate(RateDTO dto);
	RateDTO selectAttendanceRate(RateDTO dto);
}
