package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.LowerCategorySimpleDTO;
import com.ssafy.switon.dto.UpperCategory;

public interface CategoryDAO {
	
	List<UpperCategory> selectUp();
	List<LowerCategory> selectLow();
	List<LowerCategory> selectUp_Low(int uppercategory_id);
	LowerCategory selectLowOne(int id);
	UpperCategory selectUpOne(int id);
	List<UpperCategory> selectUpByKeyword(String keyword);
	List<LowerCategorySimpleDTO> selectLowByKeyword(String keyword);
	String selectUpperNameByLowerCategoryId(int id);
}
