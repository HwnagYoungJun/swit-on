package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.StudyInfo;

@Repository
public class StudyInfoImpl implements StudyInfoDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<StudyInfo> selectStudyInfo() {
		return sqlsession.selectList("study.selectStudyInfo");
	}

}
