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
	public UserSchedule selectUserSchedule(int userId) {
		return sqlsession.selectOne("userschedule.selectUserScheduleByUserId", userId);
	}

	@Override
	public int insertUserSchedule(UserSchedule userschedule) {
		return sqlsession.insert("userschedule.insertUserSchedule", userschedule);
	}

	@Override
	public int updateUserSchedule(int status) {
		return sqlsession.update("userschedule.updateCheckIn", status);
	}

	@Override
	public int deleteUserSchedule(int id) {
		return sqlsession.delete("userschedule.deleteUserSchedule", id);
	}

}
