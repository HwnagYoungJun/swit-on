package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Study;

@Repository
public class StudyDAOImpl implements StudyDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Study> selectStudies() {
		return sqlSession.selectList("study.selectStudies");
	}

	@Override
	public Study selectStudyById(int id) {
		return sqlSession.selectOne("study.selectStudyById", id);
	}

	@Override
	public int insertStudy(Study study) {
		return sqlSession.insert("study.insertStudy", study);
	}

	@Override
	public int updateStudy(Study study) {
		return sqlSession.update("study.updateStudy", study);
	}

	@Override
	public int deleteStudy(int id) {
		return sqlSession.delete("study.deleteStudy", id);
	}

	@Override
	public int getRecentStudyIdByUserId(int userId) {
		Integer studyId = sqlSession.selectOne("study.getRecentStudyIdByUserId", userId);
		return studyId = studyId == null ? 0 : studyId;
	}

	@Override
	public List<Study> selectLeaderStudiesByUserId(int userId) {
		return sqlSession.selectList("study.selectLeaderStudiesByUserId", userId);
	}

	@Override
	public List<Study> selectStudiesByLowercategoryId(int lowercategory_id) {
		List<Study> list = sqlSession.selectList("study.selectStudiesByLowercategoryId", lowercategory_id);
		return list;
	}

}
