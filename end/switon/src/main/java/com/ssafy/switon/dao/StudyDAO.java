package com.ssafy.switon.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ssafy.switon.dto.Study;

public interface StudyDAO {
	
	List<Study> selectStudies();
	Study selectStudyById(int id);
	int insertStudy(Study study);
	int updateStudy(Study study);
	int deleteStudy(int id);
	int getRecentStudyIdByUserId(int userId);
	List<Study> selectLeaderStudiesByUserId(int userId);
	List<Study> selectStudiesByLowercategoryId(int lowercategory_id);
	List<Study> selectStudyByKeyword(String keyword);
	List<Integer> selectNotFinishedStudyIds(Timestamp time);
	int updateStudyFinish(int id);
	List<Study> selectStudyByKeywordEnter(String keyword);
	List<Study> selectEndStudyByUserId(int user_id);
	List<Study> selectNotEndStudyByUserId(int user_id);
}
