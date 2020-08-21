package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Schedule;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Schedule> selectSchedules() {
		return sqlsession.selectList("schedule.selectAll");
	}

	@Override
	public Schedule selectScheduleByStudyId(int studyId) {
		return sqlsession.selectOne("schedule.selectScheduleByStudyId", studyId);
	}

	@Override
	public Schedule selectScheduleById(int id) {
		return sqlsession.selectOne("schedule.selectScheduleById", id);
	}

	@Override
	public int insertSchedule(Schedule schedule) {
		return sqlsession.insert("schedule.insertSchedule", schedule);
	}

	@Override
	public int updateSchedule(Schedule schedule) {
		return sqlsession.update("schedule.updateSchedule", schedule);
	}

	@Override
	public int deleteSchedule(int id) {
		return sqlsession.delete("schedule.deleteSchedule", id);
	}

}
