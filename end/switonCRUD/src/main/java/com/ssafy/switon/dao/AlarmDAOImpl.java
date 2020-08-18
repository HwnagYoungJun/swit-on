package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Alarm;

@Repository
public class AlarmDAOImpl implements AlarmDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Alarm> selectAlarms() {
		return sqlsession.selectList("alarm.selectAlarms");
	}

	@Override
	public Alarm selectAlarmById(int id) {
		return sqlsession.selectOne("alarm.selectAlarmById", id);
	}

	@Override
	public int insertAlarm(Alarm alarm) {
		return sqlsession.insert("alarm.insertAlarm", alarm);
	}

	@Override
	public int deleteAlarmById(int id) {
		return sqlsession.delete("alarm.deleteArticleFav", id);
	}

	@Override
	public List<Alarm> selectAlarmByUserId(int user_id) {
		return sqlsession.selectList("alarm.selectAlarmByUserId", user_id);
	}

	@Override
	public int updateAlarmById(int id) {
		return sqlsession.update("alarm.updateReadTrueById", id);
	}

	@Override
	public int countAlarmByUserIdNotRead(int user_id) {
		return sqlsession.selectOne("alarm.selectAlarmCountByUserId", user_id);
	}

}
