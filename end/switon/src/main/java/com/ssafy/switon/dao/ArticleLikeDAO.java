package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.ArticleLike;

public interface ArticleLikeDAO {
	
	// 글좋아요 정보 전체 조회
	List<ArticleLike> selectArticleLikes();
	// 글좋아요 정보 상세 조회
	ArticleLike selectArticleLike(int id);
	// 해당 유저가 좋아요 누른 글 조회
	ArticleLike selectArticleLikeByUser_Article(int user_id, int article_id);
	// 해당 글 좋아요수 조회
	int selectLikeCount(int article_id);
	// 글좋아요 등록
	int insertArticleLike(ArticleLike articlelike);
	// 글좋아요 삭제
	int deleteArticleLike(int id);
	// 해당 유저가 누른 글좋아요 삭제
	int deleteArticleLikeByUser(int user_id, int article_id);
	// 해당 스터디에서 유저가 받은 좋아요 수를 반환한다.
	int cntLikesUserGot (int user_id, int study_id);
	// 해당 스터디에서 유저가 가장 최근에 받은 좋아요 id를 반환한다. (시간 비교용)
	int selectRecentLikeId(int user_id, int study_id);
}
