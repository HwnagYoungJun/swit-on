package com.ssafy.switon.dao;

import java.sql.Timestamp;
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
	public List<Schedule> selectSchedulesByStudyId(int studyId) {
		return sqlsession.selectList("schedule.selectSchedulesByStudyId", studyId);
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
	
	public int selectRecentScheduleId(int userId) {
		return sqlsession.selectOne("schedule.selectRecentScheduleId", userId);
	}

	@Override
	public int finishSchedule(int id) {
		return sqlsession.update("schedule.finishSchedule", id);
	}

	@Override
	public List<Integer> selectNotFinishedScheduleIds(Timestamp time) {
		return sqlsession.selectList("schedule.selectNotFinishedScheduleIds", time);
	}

}
