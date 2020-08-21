package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.AlarmDAO;
import com.ssafy.switon.dto.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmDAO alarmDAO;
	
	@Override
	public List<Alarm> searchAll() {
		return alarmDAO.selectAlarms();
	}

	@Override
	public Alarm searchAlarmById(int id) {
		return alarmDAO.selectAlarmById(id);
	}

	@Override
	public boolean createAlarm(Alarm alarm) {
		return alarmDAO.insertAlarm(alarm)==1;
	}

	@Override
	public boolean deleteAlarmById(int id) {
		return alarmDAO.deleteAlarmById(id)==1;
	}

	@Override
	public List<Alarm> searchAlarmByUserId(int user_id) {
		return alarmDAO.selectAlarmByUserId(user_id);
	}

	@Override
	public boolean updateAlarmById(int id) {
		return alarmDAO.updateAlarmById(id)==1;
	}

	@Override
	public int searchCountNotReadByUserId(int user_id) {
		return alarmDAO.countAlarmByUserIdNotRead(user_id);
	}

}
