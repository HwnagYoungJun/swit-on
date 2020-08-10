package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ScheduleDAO;
import com.ssafy.switon.dto.Schedule;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Override
	public List<Schedule> selectAll() {
		return scheduleDAO.selectSchedules();
	}

	@Override
	public Schedule selectScheduleByStudyId(int studyId) {
		return scheduleDAO.selectScheduleByStudyId(studyId);
	}

	@Override
	public Schedule selectScheduleById(int id) {
		return scheduleDAO.selectScheduleById(id);
	}

	@Override
	public boolean createSchedule(Schedule schedule) {
		return scheduleDAO.insertSchedule(schedule)==1;
	}

	@Override
	public boolean updateSchedule(Schedule schedule) {
		return scheduleDAO.updateSchedule(schedule)==1;
	}

	@Override
	public boolean deleteSchedule(int id) {
		return scheduleDAO.deleteSchedule(id)==1;
	}

}
