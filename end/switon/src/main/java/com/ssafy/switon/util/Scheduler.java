package com.ssafy.switon.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.switon.service.NotificationService;
import com.ssafy.switon.service.ScheduleService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.service.UserScheduleService;

@Component
public class Scheduler {
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	StudyService studyService;
	
	// 초 분 시 일 월 요일 (연도)
	// 매분마다 반복
	@Scheduled(cron = "0 * * * * ?")
	public void everyMinute() {
//		Date now = new Date();
//		String strDate = getCurrentTime(now);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
//		System.out.println("Java cron job expression:: " + strDate);
		String resultMsg = scheduleService.finishSchedules(timestamp);
		if(resultMsg != null) {
			System.out.println(resultMsg);
		}
		
		// 오차가 있을 수 있으니 앞뒤로 10초씩 여유를 두고 그 사이에 스타트 시간이 포함된 스케줄에 가입한 유저들에게 알림 쏘기
		// 입실 시간 알림
		Timestamp tenMinutesBeforeStart = new Timestamp(System.currentTimeMillis() - (1000 * 10) - (1000 * 60 * 10));
		Timestamp tenMinutesBeforeEnd = new Timestamp(System.currentTimeMillis() + (1000 * 10) - (1000 * 60 * 10));
		Timestamp tenMinutesAfterStart = new Timestamp(System.currentTimeMillis() - (1000 * 10) + (1000 * 60 * 10));
		Timestamp tenMinutesAfterEnd = new Timestamp(System.currentTimeMillis() + (1000 * 10) + (1000 * 60 * 10));
		String resultMsg2 = notificationService.noticeSchedule(tenMinutesBeforeStart, tenMinutesBeforeEnd);
		if(resultMsg2 != null) {
			System.out.println(resultMsg2);
		}
		// 퇴실 시간 알림
		String resultMsg3 = notificationService.noticeSchedule2(tenMinutesAfterStart, tenMinutesAfterEnd);
		if(resultMsg3 != null) {
			System.out.println(resultMsg2);
		}
		
	}
	// 매일(00:00) 반복
	@Scheduled(cron = "0 0 0 * * ?")
	public void everyDay() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis() - (1000 * 60 * 60 * 2));
		System.out.println(timestamp);
		// 안정적으로 계산하기 위해 2시간 빼줌 // 현재시간 24일 00:00이라면 23일 22:00보다 앞인 스터디들(23일까지로 해둔 스터디)을 종료시킬것
		String resultMsg = studyService.finishStudies(timestamp);
		if(resultMsg != null) {
			System.out.println(resultMsg);
		}
	}

//	private String getCurrentTime(Date now) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		return sdf.format(now);
//	}
}
