package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.UpperCategory;

public interface CategoryService {

	// 대분류 전체 정보 조회
	List<UpperCategory> searchUp();
	// 소분류 전체 정보 조회
	List<LowerCategory> searchLow();
	// 대분류에 해당하는 소분류 조회
	List<LowerCategory> searchUp_Low(int uppercategory_id);
	// 키워드로 대분류 조회
	List<UpperCategory> searchUpByKeyword(String keyword);
	// 키워드로 소분류 조회
	List<LowerCategory> searchLowByKeyword(String keyword);
}
