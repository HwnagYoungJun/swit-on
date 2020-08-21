package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.CategoryDAO;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.UpperCategory;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	public List<UpperCategory> searchUp() {
		return categoryDAO.selectUp();
	}

	@Override
	public List<LowerCategory> searchLow() {
		return categoryDAO.selectLow();
	}

	@Override
	public List<LowerCategory> searchUp_Low(int uppercategory_id) {
		return categoryDAO.selectUp_Low(uppercategory_id);
	}

	@Override
	public List<UpperCategory> searchUpByKeyword(String keyword) {
		return categoryDAO.selectUpByKeyword(keyword);
	}

	@Override
	public List<LowerCategory> searchLowByKeyword(String keyword) {
		return categoryDAO.selectLowByKeyword(keyword);
	}

}
