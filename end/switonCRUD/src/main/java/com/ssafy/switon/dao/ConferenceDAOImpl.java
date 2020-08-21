package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Conference;

@Repository
public class ConferenceDAOImpl implements ConferenceDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public Conference selectConferenceById(int id) {
		return sqlsession.selectOne("conference.selectConferenceById", id);
	}

	@Override
	public List<Conference> selectConferenceByStudyId(int studyId) {
		return sqlsession.selectList("conference.selectConferenceByStudyId", studyId);
	}

	@Override
	public int insertConference(Conference conference) {
		return sqlsession.insert("conference.insertConference", conference);
	}

	@Override
	public int deleteConference(int id) {
		return sqlsession.delete("conference.deleteConference", id);
	}

}
