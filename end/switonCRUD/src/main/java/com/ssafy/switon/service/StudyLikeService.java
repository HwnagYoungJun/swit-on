package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.StudyLike;

public interface StudyLikeService {
	// 소모임 좋아요 전체 조회
	List<StudyLike> searchAll();
	// 소모임 좋아요 조회
	StudyLike search(int id);
	// 해당 유저가 좋아요를 누른 글을 조회
	StudyLike searchByUser_Study(int user_id, int study_id);
	// 소모임좋아요 수 조회
	int searchLikeCount(int study_id);
	// 소모임 좋아요 등록
	boolean createStudyLike(StudyLike studylike);
	// 소모임 좋아요 삭제
	boolean deleteStudyLike(int id);
	// 해당 유저가 누른 소모임 좋아요 삭제
	boolean deleteStudyLikeByUser(int user_id, int study_id);
}
