package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Study;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Study> selectStudyByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		System.out.println(keyword);
		return sqlsession.selectList("study.selectStudyByKeyword", keyword);
	}

}
