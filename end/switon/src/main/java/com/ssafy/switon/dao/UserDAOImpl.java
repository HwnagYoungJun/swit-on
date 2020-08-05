package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<UserInfoDTO> selectUsers() {
		return sqlSession.selectList("user.selectUsers");
	}

	@Override
	public UserInfoDTO selectUserById(int id) {
		return sqlSession.selectOne("user.selectUserById", id);
	}

	@Override
	public String getUserPwdByEmail(String email) {
		return sqlSession.selectOne("user.getUserPwdByEmail", email);
	}

	@Override
	public int insertUser(UserRegisterDTO registerDTO) {
		return sqlSession.insert("user.insertUser", registerDTO);
	}

	@Override
	public int getUserIdByEmail(String email) {
		Integer userId = sqlSession.selectOne("user.getUserIdByEmail", email);
		return userId = userId == null ? 0 : userId;
	}

	@Override
	public int getUserIdByName(String name) {
		Integer userId = sqlSession.selectOne("user.getUserIdByName", name);
		return userId = userId == null ? 0 : userId;
	}

	@Override
	public int updateUser(UserInfoDTO userInfoDTO) {
		return sqlSession.update("user.updateUser", userInfoDTO);
	}

	@Override
	public int updateUserLegacy(UserInfoDTO userInfoDTO) {
		return sqlSession.update("user.updateUserLegacy", userInfoDTO);
	}


}
