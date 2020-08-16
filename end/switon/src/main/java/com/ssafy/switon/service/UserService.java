package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.User;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserInfoWithMedals;
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;

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
	// 회원 정보 수정
	boolean modify(UserInfoDTO userInfoDTO);
	// 유저 탈퇴 처리
	boolean signOut(int userId);
	// 이름으로 유저 아이디 찾기
	int searchUserIdByName(String name);
	// 이메일로 유저 찾기
	User findUserByEmail(String email);
	// 이메일로 유저 아이디 찾기
	int findUserIdByEmail(String email);
	// 비밀번호 변경하기
	boolean pwChange(int id, String password);
	// (회원정보 수정시) 닉네임이 중복인지 검사
	boolean nameAlreadyExist(int userId, String newName);
	// 유저 정보에 메달을 추가해주자
	UserInfoWithMedals addMedalsToUserInfo(UserInfoDTO user);

}
