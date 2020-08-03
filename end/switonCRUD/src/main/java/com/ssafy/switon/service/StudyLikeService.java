package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.StudyLike;

public interface StudyLikeService {
	// 소모임 좋아요 전체 조회
	List<StudyLike> searchAll();
	// 소모임 좋아요 조회
	StudyLike search(int id);
	// 소모임좋아요 수 조회
	int searchLikeCount(int study_id);
	// 소모임 좋아요 등록
	boolean createStudyLike(StudyLike studylike);
	// 소모임 좋아요 삭제
	boolean deleteStudyLike(int id);
}
