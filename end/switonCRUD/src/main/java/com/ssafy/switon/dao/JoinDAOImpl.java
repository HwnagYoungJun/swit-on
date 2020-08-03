package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Join;

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

}
