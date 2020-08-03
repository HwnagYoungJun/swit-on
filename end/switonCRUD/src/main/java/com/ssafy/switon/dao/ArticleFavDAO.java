package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.ArticleFav;

public interface ArticleFavDAO {
	// 글 즐겨찾기 정보 전체 조회
	List<ArticleFav> selectArticleFavs();
	// 글 즐겨찾기 정보 상세 조회
	ArticleFav selectArticleFav(int id);
	// 사용자가 즐겨찾기한 글 목록 조회
	List<ArticleFav> selectArticleFavByUser(int user_id);
	// 글 즐겨찾기 등록
	int insertArticleFav(ArticleFav articlefav);
	// 글 즐겨찾기 삭제
	int deleteArticleFav(int id);
}
