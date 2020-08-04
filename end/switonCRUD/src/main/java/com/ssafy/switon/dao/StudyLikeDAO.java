package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.StudyLike;

public interface StudyLikeDAO {
	// 소모임 좋아요 전체 조회
	List<StudyLike> selectStudyLikes();
	// 소모임 좋아요 조회
	StudyLike selectStudyLike(int id);
	// 해당 유저가 좋아요를 누른 글 조회
	StudyLike selectStudyLikeByUser_Study(int user_id, int study_id);
	// 해당 소모임 좋아요수 조회
	int selectLikeCount(int study_id);
	// 소모임 좋아요 등록
	int insertStudyLike(StudyLike studylike);
	// 소모임 좋아요 삭제
	int deleteStudyLike(int id);
}
