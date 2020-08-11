package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ScheduleDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserScheduleDAO;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.ScheduleReturnDTO;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.UserSchedule;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Autowired
	UserScheduleDAO userScheduleDAO;
	
	@Autowired
	UserScheduleService userScheduleService;
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	StudyDAO studyDAO;

	
	@Override
	public List<Schedule> selectAll() {
		return scheduleDAO.selectSchedules();
	}

	@Override
	public List<ScheduleReturnDTO> selectSchedulesByStudyId(int studyId) {
		List<Schedule> schedules = scheduleDAO.selectSchedulesByStudyId(studyId);
		List<ScheduleReturnDTO> dtos = new ArrayList<ScheduleReturnDTO>();
		for(Schedule schedule : schedules) {
			ScheduleReturnDTO dto = new ScheduleReturnDTO(schedule);
			Study study = studyService.search(schedule.getStudy_id());
			dto.setStudy_name(study.getName());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Schedule selectScheduleById(int id) {
		return scheduleDAO.selectScheduleById(id);
	}

	@Override
	public String createSchedule(Schedule schedule) {
		List<Schedule> otherList = scheduleDAO.selectSchedulesByStudyId(schedule.getStudy_id());
		Timestamp newStart = schedule.getStart();
		Timestamp newEnd = schedule.getEnd();
		for(Schedule orderSchedule : otherList){
			Timestamp oldStart = orderSchedule.getStart();
			Timestamp oldEnd = orderSchedule.getEnd();
			// B시작시간이 A종료시간보다 앞인 경우 + B종료시간이 A시작시간보다 뒤인 경우
			if(newStart.before(oldEnd) && newEnd.after(oldStart)
					// OR B종료시간이 A시작시간보다 뒤인 경우 + B시작시간이 A의 종료시간보다 앞인 경우 <- 오류
					|| newEnd.after(oldStart) && newStart.before(oldEnd)){
				// 오류인 경우 어떤 스터디의 어떤 스케줄과 겹치는지 이름을 반환
				String msg = "'" + schedule.getTitle() + "' 스케줄과 시간이 겹칩니다.";
				return msg;
			}
		}
		int result = scheduleDAO.insertSchedule(schedule);
		if(result == 1) {
			int userId = schedule.getUser_id();
			int recentScheduleId = scheduleDAO.selectRecentScheduleId(userId);
			String leaderResult = userScheduleService.createUserSchedule(new UserSchedule(userId, recentScheduleId));
			if(leaderResult.equals("success")) {
				System.out.println("모임장의 스케줄 가입 성공!");
				return "success";
			} else {
				scheduleDAO.deleteSchedule(recentScheduleId);
				System.out.println(leaderResult);
				System.out.println("모임장이 스케줄에 가입할 수 없기 때문에 스케줄이 삭제되었습니다.");
				return leaderResult + " 모임장이 스케줄에 가입할 수 없는 상태이기 때문에 스케줄 생성이 취소되었습니다.";
			}
		}
		return "fail";
	}

	@Override
	public boolean updateSchedule(Schedule schedule) {
		return scheduleDAO.updateSchedule(schedule)==1;
	}

	@Override
	public boolean deleteSchedule(int id) {
		// 유저의 해당 스케줄 참석 정보도 같이 없애기
		userScheduleDAO.deleteAllByScheduleId(id);
		return scheduleDAO.deleteSchedule(id)==1;
	}

}
