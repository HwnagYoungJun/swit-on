package com.ssafy.switon.util;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.switon.service.NotificationService;
import com.ssafy.switon.service.ScheduleService;
import com.ssafy.switon.service.StudyService;

@Component
public class Scheduler {
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	StudyService studyService;
	
	@Scheduled(cron = "0 * * * * ?")
	public void everyMinute() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String resultMsg = scheduleService.finishSchedules(timestamp);
		if(resultMsg != null) {
			System.out.println(resultMsg);
		}
		Timestamp tenMinutesStart = new Timestamp(System.currentTimeMillis() + (- (1000 * 10) + (1000 * 60 * 10)));
		Timestamp tenMinutesEnd = new Timestamp(System.currentTimeMillis() + (1000 * 10 + (1000 * 60 * 10))) ;
		String resultMsg2 = notificationService.noticeSchedule(tenMinutesStart, tenMinutesEnd);
		if(resultMsg2 != null) {
			System.out.println(resultMsg2);
		}
		String resultMsg3 = notificationService.noticeSchedule2(tenMinutesStart, tenMinutesEnd);
		if(resultMsg3 != null) {
			System.out.println(resultMsg3);
		}
		
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void everyDay() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis() - (1000 * 60 * 60 * 2));
		String resultMsg = studyService.finishStudies(timestamp);
		if(resultMsg != null) {
			System.out.println(resultMsg);
		}
	}
}
