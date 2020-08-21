package com.ssafy.switon.service;

import java.sql.Timestamp;

public interface NotificationService {

	String noticeSchedule(Timestamp tenSecBefore, Timestamp tenSecAfter);
	String noticeSchedule2(Timestamp tenSecBefore, Timestamp tenSecAfter);


}
