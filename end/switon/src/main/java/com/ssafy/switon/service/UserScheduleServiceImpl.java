package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ScheduleDAO;
import com.ssafy.switon.dao.UserScheduleDAO;
import com.ssafy.switon.dto.ParticipateInfo;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.dto.UserScheduleReturnDTO;
import com.ssafy.switon.dto.UserScheduleSimpleDTO;

@Service
public class UserScheduleServiceImpl implements UserScheduleService {

	@Autowired
	UserScheduleDAO userscheduleDAO;
	
	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Override
	public List<UserSchedule> selectAll() {
		return userscheduleDAO.selectUserSchedules();
	}

	@Override
	public List<UserSchedule> selectUserScheduleByUserId(int userId) {
		return userscheduleDAO.selectUserSchedules(userId);
	}

	@Override
	public boolean createUserSchedule(UserSchedule userschedule) {
		return userscheduleDAO.insertUserSchedule(userschedule)==1;
	}

	@Override
	public boolean changeStatus(int userId, int scheduleId, int status) {
		UserSchedule userSchedule = getParticipate(userId, scheduleId);
		int originalStatus = userSchedule.getStatus();
		if((originalStatus & (1 << (status - 1))) == 0) {
			userSchedule.setStatus(originalStatus + status);
		}
		return userscheduleDAO.updateStatus(userSchedule)==1;
	}
	
	@Override
	public boolean changeSuccess(int userId, int scheduleId, boolean success) {
		UserSchedule userSchedule = getParticipate(userId, scheduleId);
		userSchedule.setSuccess(success);
		return userscheduleDAO.updateStatus(userSchedule)==1;
	}

	@Override
	public boolean deleteUserSchedule(int id) {
		return userscheduleDAO.deleteUserSchedule(id)==1;
	}

	@Override
	public List<UserScheduleReturnDTO> getUserSchedules(int userId) {
		List<UserSchedule> userSchedules = userscheduleDAO.selectUserSchedules(userId);
		List<UserScheduleReturnDTO> list = new ArrayList<UserScheduleReturnDTO>();
		
		for(UserSchedule userSchedule : userSchedules) {
			Schedule schedule = scheduleDAO.selectScheduleById(userSchedule.getSchedule_id());
			UserScheduleReturnDTO dto = new UserScheduleReturnDTO(
					schedule,
					userSchedule.getStatus(),
					userSchedule.getSuccess()
				);
			list.add(dto);
		}
		return list;
	}

	@Override
	public boolean cancelParticipate(int userId, int scheduleId) {
		return userscheduleDAO.deleteParticipate(new UserSchedule(userId, scheduleId)) == 1;
	}

	@Override
	public UserSchedule getParticipate(int userId, int scheduleId) {
		return userscheduleDAO.selectParticipate(new UserSchedule(userId, scheduleId));
	}

	@Override
	public ParticipateInfo getParticipateInfo(int userId, int scheduleId) {
		UserSchedule userSchedule = userscheduleDAO.selectParticipate(new UserSchedule(userId, scheduleId));
		return new ParticipateInfo(userSchedule.getStatus(), userSchedule.getSuccess());
	}

}
