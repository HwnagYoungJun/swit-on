package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.UserScheduleDAO;
import com.ssafy.switon.dto.UserSchedule;

@Service
public class UserScheduleServiceImpl implements UserScheduleService {

	@Autowired
	UserScheduleDAO userscheduleDAO;
	
	@Override
	public List<UserSchedule> selectAll() {
		return userscheduleDAO.selectUserSchedules();
	}

	@Override
	public UserSchedule selectUserScheduleByUserId(int userId) {
		return userscheduleDAO.selectUserSchedule(userId);
	}

	@Override
	public boolean createUserSchedule(UserSchedule userschedule) {
		return userscheduleDAO.insertUserSchedule(userschedule)==1;
	}

	@Override
	public boolean updateUserSchedule(int status) {
		return userscheduleDAO.updateUserSchedule(status)==1;
	}

	@Override
	public boolean deleteUserSchedule(int id) {
		return userscheduleDAO.deleteUserSchedule(id)==1;
	}

}
