package com.ssafy.switon.service;

import java.util.List;

<<<<<<< HEAD
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;
=======
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf

public interface UserService {
	
	// 모든 유저 리스트 반환
	List<UserInfoDTO> searchAll();
	// 아이디로 유저정보 반환
	UserInfoDTO search(int id);
	// 회원가입, T/F 반환
	boolean register(UserRegisterDTO registerDTO);
	// 입력된 아이디(email), 비밀번호로 로그인
	UserInfoDTO login(UserLoginDTO loginDTO);
	// 아이디(email) 중복 체크 (이미 있으면 T, 없으면 F)
	boolean emailAlreadyExists(String email);
	// 닉네임(name) 중복 체크 (이미 있으면 T, 없으면 F)
	boolean nameAlreadyExists(String name);

}
