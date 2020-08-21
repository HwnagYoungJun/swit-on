package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.ParticipateInfo;
import com.ssafy.switon.dto.UserRate;
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.dto.UserScheduleReturnDTO;
import com.ssafy.switon.dto.UserSimpleDTO;

public interface UserScheduleService {

	List<UserSchedule> selectAll();
	List<UserSchedule> selectUserScheduleByUserId(int userId);
	String createUserSchedule(UserSchedule userschedule);
	boolean changeStatus(int userId, int scheduleId, int status);
	boolean changeSuccess(int userId, int scheduleId, boolean success);
	boolean deleteUserSchedule(int id);
	List<UserScheduleReturnDTO> getUserSchedules(int userId);
	boolean cancelParticipate(int userId, int scheduleId);
	UserSchedule getParticipate(int userId, int scheduleId);
	ParticipateInfo getParticipateInfo(int userId, int scheduleId);
	List<UserSimpleDTO> searchParticipants(int scheduleId);
	UserRate getUserParticipateRate(int userId, int studyId);
}
