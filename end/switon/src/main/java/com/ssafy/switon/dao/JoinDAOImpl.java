package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.JoinGiveDTO;
import com.ssafy.switon.dto.Medals;
import com.ssafy.switon.dto.Ranker;

@Repository
public class JoinDAOImpl implements JoinDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Join> selectJoins() {
		return sqlsession.selectList("join.selectJoins");
	}

	@Override
	public Join selectJoin(int id) {
		return sqlsession.selectOne("join.selectJoin", id);
	}

	@Override
	public int insertJoin(Join join) {
		return sqlsession.insert("join.insertJoin", join);
	}

	@Override
	public int deleteJoin(int id) {
		return sqlsession.delete("join.deleteJoin", id);
	}

	@Override
	public int insertJoinLeader(Join join) {
		return sqlsession.insert("join.insertJoin", join);
	}

	@Override
	public List<Join> selectJoinsByUserId(int userId) {
		return sqlsession.selectList("join.selectJoinsByUserId", userId);
	}

	@Override
	public int countUsersByStudyId(int studyId) {
		Integer cnt = sqlsession.selectOne("join.userCount", studyId);
		return cnt = cnt == null ? 0 : cnt;
	}

	@Override
	public int isMember(Join join) {
		Integer cnt = sqlsession.selectOne("join.isMember", join);
		return cnt = cnt == null ? 0 : cnt;
	}

	@Override
	public int deleteJoinByIds(Join join) {
		return sqlsession.delete("join.deleteJoinByIds", join);
	}

	@Override
	public List<Integer> selectMemberIds(int studyId) {
		return sqlsession.selectList("join.selectMemberIds", studyId);
	}

	@Override
	public List<Join> selectJoinsByStudyId(int studyId) {
		return sqlsession.selectList("join.selectJoinsByStudyId", studyId);
	}

	@Override
	public int updateJoinComplete(int studyId) {
		return sqlsession.update("join.updateJoinComplete", studyId);
	}

	@Override
	public List<Ranker> selectRankers(int studyId) {
		return sqlsession.selectList("join.selectRankers", studyId);
	}

	@Override
	public int giveMedal(JoinGiveDTO dto) {
		return sqlsession.update("join.giveMedal", dto);
	}

	@Override
	public Medals selectMedalsByUserId(int userId) {
		return sqlsession.selectOne("join.selectMedalsByUserId", userId);
	}

	@Override
	public int addScore(JoinGiveDTO dto) {
		return sqlsession.update("join.addScore", dto);
	}
	
	

}
