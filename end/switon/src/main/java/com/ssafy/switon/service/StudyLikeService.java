package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.StudyLike;

public interface StudyLikeService {
	List<StudyLike> searchAll();
	StudyLike search(int id);
	StudyLike searchByUser_Study(int user_id, int study_id);
	int searchLikeCount(int study_id);
	boolean createStudyLike(StudyLike studylike);
	boolean deleteStudyLike(int id);
	boolean deleteStudyLikeByUser(int user_id, int study_id);
}
