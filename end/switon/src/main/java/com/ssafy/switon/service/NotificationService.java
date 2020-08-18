package com.ssafy.switon.service;

import java.sql.Timestamp;

public interface NotificationService {

	// 스케줄에 참가한 유저들에게 입실 알림을 보내준다.
	String noticeSchedule(Timestamp tenSecBefore, Timestamp tenSecAfter);
	// 퇴실 알림
	String noticeSchedule2(Timestamp tenSecBefore, Timestamp tenSecAfter);
	
	

}
