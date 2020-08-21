package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.AlarmReturnDTO;

public interface AlarmService {
	
	List<Alarm> searchAll();	
	Alarm searchAlarmById(int id);
	boolean createAlarm(Alarm alarm);
	boolean deleteAlarmById(int id);	
	List<Alarm> searchAlarmByUserId(int user_id);	
	List<AlarmReturnDTO> searchAlarmByUserId(int userId, String string);
	boolean updateAlarmById(int id);	
	int searchCountNotReadByUserId(int user_id);
}
