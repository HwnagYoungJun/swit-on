package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.UpperCategory;

public interface CategoryDAO {
	
	// 대분류 전체 조회
	List<UpperCategory> selectUp();
	// 소분류 전체 조회
	List<LowerCategory> selectLow();
	// 대분류에 해당하는 소분류 전체 조회
	List<LowerCategory> selectUp_Low(int uppercategory_id);
	// 소분류 카테고리 하나 조회
	LowerCategory selectLowOne(int id);
	// 대분류 카테고리 하나 조회
	UpperCategory selectUpOne(int id);
	
}
