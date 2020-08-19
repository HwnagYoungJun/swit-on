package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.AlarmDAO;
import com.ssafy.switon.dao.ScheduleDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserScheduleDAO;
import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.AlarmSchedule;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.ScheduleAlarmReturnDTO;
import com.ssafy.switon.dto.ScheduleInfo;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.TimeBetweenDTO;

@Service
public class NotificationServiceImpl implements NotificationService {
//	template.convertAndSend("/topic/notification/" + {글작성자 아이디}, awake);
	
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	UserScheduleDAO userScheduleDAO;
	
	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Autowired
	StudyDAO studyDAO;
	
	@Autowired
	AlarmDAO alarmDAO;
	

	@Override
	public String noticeSchedule(Timestamp tenSecBefore, Timestamp tenSecAfter) {
		TimeBetweenDTO timeDTO = new TimeBetweenDTO(tenSecBefore, tenSecAfter);
		List<ScheduleAlarmReturnDTO> list = userScheduleDAO.searchUsersToAlarm(timeDTO);
		String returnMsg;
		if(list.size() != 0) {
			returnMsg = "스케줄 입실 알람 (";
			for(ScheduleAlarmReturnDTO dto : list) {
				int user_id = dto.getUser_id();
				int schedule_id = dto.getSchedule_id();
				String schedule_title = dto.getSchedule_title();
				Schedule schedule = scheduleDAO.selectScheduleById(schedule_id);
				Study study = studyDAO.selectStudyById(schedule.getStudy_id());
				returnMsg = returnMsg + user_id + "번 유저에게 스케쥴 알람 발송 ";
				AlarmSchedule alarmSchedule = new AlarmSchedule(schedule_title + " 스케줄 입실 시간",
						1, new ScheduleInfo(schedule_id, schedule_title, study.getId(), study.getName()));
				// Msg = "ㅇㅇㅇ 스케줄 출석 시간입니다! 출석하러 이동해볼까요?"
				// type = 1: 스케줄 출석 알림, 2: 댓글, 3: 좋아요
				// ScheduleInfo = 스케줄 정보 스케줄 아이디, 스케줄 이름, 스터디 아이디, 스터디 이름
//				template.convertAndSend("/topic/notification/" + user_id, alarmSchedule);
				Alarm alarm = new Alarm();
				alarm.setUser_id(user_id);
				alarm.setMsg(study.getName() + " 스케줄 입실 시간");
				alarm.setStudy_id(study.getId());
				alarm.setArticle_id(0);
				alarm.setType(1);
				alarmDAO.insertAlarm(alarm);
				returnMsg += "성공! | ";
			}
			returnMsg += ")";
			return returnMsg;
		}
		return null;
	}


	@Override
	public String noticeSchedule2(Timestamp tenSecBefore, Timestamp tenSecAfter) {
		TimeBetweenDTO timeDTO = new TimeBetweenDTO(tenSecBefore, tenSecAfter);
		List<ScheduleAlarmReturnDTO> list = userScheduleDAO.searchUsersToAlarm(timeDTO);
		String returnMsg;
		if(list.size() != 0) {
			returnMsg = "스케줄 퇴실 알람 (";
			for(ScheduleAlarmReturnDTO dto : list) {
				int user_id = dto.getUser_id();
				int schedule_id = dto.getSchedule_id();
				String schedule_title = dto.getSchedule_title();
				Schedule schedule = scheduleDAO.selectScheduleById(schedule_id);
				Study study = studyDAO.selectStudyById(schedule.getStudy_id());
				returnMsg = returnMsg + user_id + "번 유저에게 스케쥴 알람 발송 ";
				AlarmSchedule alarmSchedule = new AlarmSchedule(schedule_title + " 스케줄 퇴실 시간입니다! 마무리하러 이동해볼까요?",
						1, new ScheduleInfo(schedule_id, schedule_title, study.getId(), study.getName()));
				// Msg = "ㅇㅇㅇ 스케줄 출석 시간입니다! 출석하러 이동해볼까요?"
				// type = 1: 스케줄 출석 알림, 2: 댓글, 3: 좋아요
				// ScheduleInfo = 스케줄 정보 스케줄 아이디, 스케줄 이름, 스터디 아이디, 스터디 이름
//				template.convertAndSend("/topic/notification/" + user_id, alarmSchedule);
				Alarm alarm = new Alarm();
				alarm.setUser_id(user_id);
				alarm.setMsg(study.getName() + " 스케줄 퇴실 시간");
				alarm.setStudy_id(study.getId());
				alarm.setArticle_id(0);
				alarm.setType(1);
				alarmDAO.insertAlarm(alarm);
				returnMsg += "성공! | ";
			}
			returnMsg += ")";
			return returnMsg;
		}
		return null;
	}

}
