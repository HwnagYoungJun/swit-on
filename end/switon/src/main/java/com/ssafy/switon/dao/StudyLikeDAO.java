package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.StudyLike;

public interface StudyLikeDAO {
	List<StudyLike> selectStudyLikes();
	StudyLike selectStudyLike(int id);
	StudyLike selectStudyLikeByUser_Study(int user_id, int study_id);
	int selectLikeCount(int study_id);
	int insertStudyLike(StudyLike studylike);
	int deleteStudyLike(int id);
	int deleteStudyLikeByUser(int user_id, int study_id);
}
