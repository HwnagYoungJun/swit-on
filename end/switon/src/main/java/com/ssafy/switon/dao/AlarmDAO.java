package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Alarm;

public interface AlarmDAO {
	
	List<Alarm> selectAlarms();	
	Alarm selectAlarmById(int id);
	int insertAlarm(Alarm alarm);
	int deleteAlarmById(int id);	
	List<Alarm> selectAlarmByUserId(int user_id);	
	int updateAlarmById(int id);	
	int countAlarmByUserIdNotRead(int user_id);
}
