package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.AlarmReturnDTO;

public interface AlarmService {
	
	// 전체 리스트 조회  
	List<Alarm> searchAll();	
	// id로 상세 조회  
	Alarm searchAlarmById(int id);
	// 알림 생성 
	boolean createAlarm(Alarm alarm);
	// id로 삭제  
	boolean deleteAlarmById(int id);	
	// userId로 알림 리스트 조회 
	List<Alarm> searchAlarmByUserId(int user_id);	
	// userId로 보드 이름도 넣어서 조회
	List<AlarmReturnDTO> searchAlarmByUserId(int userId, String string);
	// id로 알림 읽음으로 등록 
	boolean updateAlarmById(int id);	
	// userId로 그 유저가 읽지않은 알림 갯수 조회
	int searchCountNotReadByUserId(int user_id);
}
