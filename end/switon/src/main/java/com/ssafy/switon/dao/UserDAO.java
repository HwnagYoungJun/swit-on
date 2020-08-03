package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;

public interface UserDAO {
	
	// 회원 정보 전체 조회
	List<UserInfoDTO> selectUsers();
	// id로 유저 정보 상세 조회
	UserInfoDTO selectUserById(int id);
	// 회원 가입(회원 추가)
	int insertUser(UserRegisterDTO registerDTO);
	// 이메일주소로 DB에 저장된 유저의 비밀번호(해쉬됨)를 가져오기
	String getUserPwdByEmail(String email);
	// 아이디(이메일주소)로 유저 id 가져오기
	int getUserIdByEmail(String email);
	// 유저명(name)으로 유저 id 가져오기
	int getUserIdByName(String name);

}
