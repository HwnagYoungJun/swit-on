package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserRegisterDTO;

public interface UserDAO {
	
	List<UserInfoDTO> selectUsers();
	UserInfoDTO selectUserById(int id);
	int insertUser(UserRegisterDTO registerDTO);
	String getUserPwdByEmail(String email);
	int getUserIdByEmail(String email);
	int getUserIdByName(String name);
	int updateUser(UserInfoDTO userInfoDTO);
	int updateUserLegacy(UserInfoDTO userInfoDTO);
	int updateUserPwd(UserDTO userDTO);

}
