package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ScheduleDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dao.UserScheduleDAO;
import com.ssafy.switon.dto.ParticipateInfo;
import com.ssafy.switon.dto.RateDTO;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.ScheduleReturnDTO;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserRate;
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.dto.UserScheduleReturnDTO;
import com.ssafy.switon.dto.UserSimpleDTO;

@Service
public class UserScheduleServiceImpl implements UserScheduleService {

	@Autowired
	UserScheduleDAO userscheduleDAO;
	
	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Autowired
	StudyDAO studyDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public List<UserSchedule> selectAll() {
		return userscheduleDAO.selectUserSchedules();
	}

	@Override
	public List<UserSchedule> selectUserScheduleByUserId(int userId) {
		return userscheduleDAO.selectUserSchedules(userId);
	}

	@Override
	public String createUserSchedule(UserSchedule userschedule) {
		Schedule newSchedule = scheduleDAO.selectScheduleById(userschedule.getSchedule_id());
		Timestamp newStart = newSchedule.getStart();
		Timestamp newEnd = newSchedule.getEnd();
		int userId = userschedule.getUser_id();
		List<UserSchedule> scheduleList = userscheduleDAO.selectUserSchedules(userId);
		for(UserSchedule userSchedule : scheduleList) {
			Schedule schedule = scheduleDAO.selectScheduleById(userSchedule.getSchedule_id());
			Timestamp oldStart = schedule.getStart();
			Timestamp oldEnd = schedule.getEnd();
			if(newStart.before(oldEnd) && newEnd.after(oldStart)
					|| newEnd.after(oldStart) && newStart.before(oldEnd)){
				Study conflictStudy = studyDAO.selectStudyById(schedule.getStudy_id());
				String msg = "현재 참가중인 '" + conflictStudy.getName() + "' 스터디의 '" + schedule.getTitle() + "' 스케줄과 시간이 겹칩니다.";
				return msg;
			}
		}
		if(userscheduleDAO.insertUserSchedule(userschedule)==1) {
			return "success";
		}
		return "fail";
	}

	@Override
	public boolean changeStatus(int userId, int scheduleId, int status) {
		UserSchedule userSchedule = getParticipate(userId, scheduleId);
		int originalStatus = userSchedule.getStatus();
		if((originalStatus & (1 << (status - 1))) == 0) {
			userSchedule.setStatus(originalStatus + status);
			if(userSchedule.getStatus() == 7) {
				userSchedule.setSuccess(true);
				userscheduleDAO.updateSuccess(userSchedule);
			}
		}
		return userscheduleDAO.updateStatus(userSchedule)==1;
	}
	
	@Override
	public boolean changeSuccess(int userId, int scheduleId, boolean success) {
		UserSchedule userSchedule = getParticipate(userId, scheduleId);
		userSchedule.setSuccess(success);
		return userscheduleDAO.updateStatus(userSchedule)==1;
	}

	@Override
	public boolean deleteUserSchedule(int id) {
		return userscheduleDAO.deleteUserSchedule(id)==1;
	}

	@Override
	public List<UserScheduleReturnDTO> getUserSchedules(int userId) {
		List<UserSchedule> userSchedules = userscheduleDAO.selectUserSchedules(userId);
		List<UserScheduleReturnDTO> list = new ArrayList<UserScheduleReturnDTO>();
		
		for(UserSchedule userSchedule : userSchedules) {
			Schedule originalSchedule = scheduleDAO.selectScheduleById(userSchedule.getSchedule_id());
			ScheduleReturnDTO schedule = new ScheduleReturnDTO(originalSchedule);
			Study study = studyDAO.selectStudyById(schedule.getStudy_id());
			schedule.setStudy_name(study.getName());
			UserScheduleReturnDTO dto = new UserScheduleReturnDTO(
					schedule,
					userSchedule.getStatus(),
					userSchedule.getSuccess()
				);
			list.add(dto);
		}
		return list;
	}

	@Override
	public boolean cancelParticipate(int userId, int scheduleId) {
		return userscheduleDAO.deleteParticipate(new UserSchedule(userId, scheduleId)) == 1;
	}

	@Override
	public UserSchedule getParticipate(int userId, int scheduleId) {
		return userscheduleDAO.selectParticipate(new UserSchedule(userId, scheduleId));
	}

	@Override
	public ParticipateInfo getParticipateInfo(int userId, int scheduleId) {
		UserSchedule userSchedule = userscheduleDAO.selectParticipate(new UserSchedule(userId, scheduleId));
		if(userSchedule == null) {
			return null;
		}
		return new ParticipateInfo(userSchedule.getStatus(), userSchedule.getSuccess());
	}

	@Override
	public List<UserSimpleDTO> searchParticipants(int scheduleId) {
		List<UserSimpleDTO> members = new ArrayList<UserSimpleDTO>();
		List<UserSchedule> userSchedules = userscheduleDAO.selectUserSchedulesByScheduleId(scheduleId);
		for(UserSchedule userSchedule : userSchedules) {
			int userId = userSchedule.getUser_id();
			UserInfoDTO user = userDAO.selectUserById(userId);
			UserSimpleDTO member = new UserSimpleDTO(user);
			members.add(member);
		}
		return members;
	}

	@Override
	public UserRate getUserParticipateRate(int userId, int studyId) {
		RateDTO dto = new RateDTO(userId, studyId);
		RateDTO part = userscheduleDAO.selectParticipationRate(dto);
		RateDTO attend = userscheduleDAO.selectAttendanceRate(dto);
		double participation = 1.0;
		double attendance = 1.0;
		if(part.getTotal() != 0) {
			participation = part.getMine() / part.getTotal();			
		}
		if(attend.getTotal() != 0) {
			attendance = attend.getMine() / attend.getTotal();
		}
		return new UserRate(participation, attendance);
	}

}
