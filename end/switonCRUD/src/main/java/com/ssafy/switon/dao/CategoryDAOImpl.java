package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.UpperCategory;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<UpperCategory> selectUp() {
		return sqlsession.selectList("category.selectUpperCategory");
	}

	@Override
	public List<LowerCategory> selectLow() {
		return sqlsession.selectList("category.selectLowerCategory");
	}

	@Override
	public List<LowerCategory> selectUp_Low(int uppercategory_id) {
		return sqlsession.selectList("category.selectUp_LowCategory", uppercategory_id);
	}

	@Override
	public List<UpperCategory> selectUpByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return sqlsession.selectList("category.selectUpperCategoryByKeyword", keyword);
	}

	@Override
	public List<LowerCategory> selectLowByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return sqlsession.selectList("category.selectLowerCategoryByKeyword", keyword);
	}

}
