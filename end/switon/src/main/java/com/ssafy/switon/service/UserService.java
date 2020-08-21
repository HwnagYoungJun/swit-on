package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.User;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserInfoWithMedals;
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;

public interface UserService {
	
	List<UserInfoDTO> searchAll();
	UserInfoDTO search(int id);
	boolean register(UserRegisterDTO registerDTO);
	UserInfoDTO login(UserLoginDTO loginDTO);
	boolean emailAlreadyExists(String email);
	boolean nameAlreadyExists(String name);
	boolean modify(UserInfoDTO userInfoDTO);
	boolean signOut(int userId);
	int searchUserIdByName(String name);
	User findUserByEmail(String email);
	int findUserIdByEmail(String email);
	boolean pwChange(int id, String password);
	boolean nameAlreadyExist(int userId, String newName);
	UserInfoWithMedals addMedalsToUserInfo(UserInfoDTO user);

}
