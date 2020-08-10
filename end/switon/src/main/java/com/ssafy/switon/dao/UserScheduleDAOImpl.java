package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.UserSchedule;

@Repository
public class UserScheduleDAOImpl implements UserScheduleDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<UserSchedule> selectUserSchedules() {
		return sqlsession.selectList("userschedule.selectAll");
	}

	@Override
	public List<UserSchedule> selectUserSchedules(int userId) {
		return sqlsession.selectList("userschedule.selectUserScheduleByUserId", userId);
	}

	@Override
	public int insertUserSchedule(UserSchedule userschedule) {
		return sqlsession.insert("userschedule.insertUserSchedule", userschedule);
	}

	@Override
	public int updateStatus(UserSchedule userSchedule) {
		return sqlsession.update("userschedule.updateCheckIn", userSchedule);
	}
	
	@Override
	public int updateSuccess(UserSchedule userSchedule) {
		return sqlsession.update("userschedule.updateSuccess", userSchedule);
	}

	@Override
	public int deleteUserSchedule(int id) {
		return sqlsession.delete("userschedule.deleteUserSchedule", id);
	}

	@Override
	public int deleteAllByScheduleId(int scheduleId) {
		return sqlsession.delete("userschedule.deleteAllByScheduleId", scheduleId);
	}

	@Override
	public int deleteParticipate(UserSchedule userSchedule) {
		return sqlsession.delete("userschedule.cancelParticipate", userSchedule);
	}

	@Override
	public UserSchedule selectParticipate(UserSchedule userSchedule) {
		return sqlsession.selectOne("userschedule.selectParticipate", userSchedule);
	}

}
