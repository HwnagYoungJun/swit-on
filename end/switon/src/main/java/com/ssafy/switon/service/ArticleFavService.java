package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.dto.ArticleLike;

public interface ArticleFavService {
	
	// 글 즐겨찾기 모두 조회
	List<ArticleFav> searchAll();
	// 글 즐겨찾기 상세 조회
	ArticleFav search(int id);
	// 사용자가 즐겨찾기한 글 목록 조회
	List<ArticleFav> searchByUser(int user_id);
	// 글 즐겨찾기 등록
	boolean create(ArticleFav articlefav);
	// 글 즐겨찾기 삭제
	boolean delete(int id);
	// 해당 유저가 누른 글 즐겨찾기 삭제
	boolean deleteByUser(int user_id, int article_id);
	// 유저가 즐겨찾기 누른 글 조회
	ArticleFav searchByUser_Article(int userId, int articleId);
}
