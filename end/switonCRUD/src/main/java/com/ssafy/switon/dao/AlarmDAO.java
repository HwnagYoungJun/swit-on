package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Alarm;

public interface AlarmDAO {
	
	// 전체 리스트 조회  
	List<Alarm> selectAlarms();	
	// id로 상세 조회  
	Alarm selectAlarmById(int id);
	// 알림 생성 
	int insertAlarm(Alarm alarm);
	// id로 삭제  
	int deleteAlarmById(int id);	
	// userId로 알림 리스트 조회 
	List<Alarm> selectAlarmByUserId(int user_id);	
	// id로 알림 읽음으로 등록 
	int updateAlarmById(int id);	
	// userId로 그 유저가 읽지않은 알림 갯수 조회
	int countAlarmByUserIdNotRead(int user_id);
}
