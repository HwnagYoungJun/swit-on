package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dto.Join;

@Service
public class JoinServiceImpl implements JoinService {

	@Autowired
	JoinDAO joinDAO;
	
	@Override
	public List<Join> searchAll() {
		return joinDAO.selectJoins();
	}

	@Override
	public Join search(int id) {
		return joinDAO.selectJoin(id);
	}

	@Override
	public boolean create(Join join) {
		return joinDAO.insertJoin(join)==1;
	}

	@Override
	public boolean delete(int id) {
		return joinDAO.deleteJoin(id)==1;
	}

	@Override
	public boolean createLeader(Join join) {
		return joinDAO.insertJoinLeader(join) == 1;
	}

	@Override
	public List<Join> searchAllByUserId(int userId) {
		return joinDAO.selectJoinsByUserId(userId);
	}

}
