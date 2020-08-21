package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.LowerCategorySimpleDTO;
import com.ssafy.switon.dto.UpperCategory;

public interface CategoryService {

	List<UpperCategory> searchUp();
	List<LowerCategory> searchLow();
	List<LowerCategory> searchUp_Low(int uppercategory_id);
	List<UpperCategory> searchUpByKeyword(String keyword);
	List<LowerCategorySimpleDTO> searchLowByKeyword(String keyword);
	boolean lowerCategoryIdExists(int id);
	boolean upperCategoryIdExists(int id);
}
