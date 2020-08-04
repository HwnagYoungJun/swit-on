package com.ssafy.switon.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public List<UserInfoDTO> searchAll() {
		return userDao.selectUsers();
	}

	@Override
	public UserInfoDTO search(int id) {
		return userDao.selectUserById(id);
	}

	@Override
	public boolean register(UserRegisterDTO registerDTO) {
		// 패스워드 암호화 해주기
		if(registerDTO != null && registerDTO.getEmail() != null && registerDTO.getName() != null && 
				registerDTO.getPassword() != null && registerDTO.getPassword2() != null
				&& registerDTO.getPassword().equals(registerDTO.getPassword2())) {
			
			if(emailAlreadyExists(registerDTO.getEmail())) {
				System.out.println("** 이미 가입한 이메일입니다.");
				return false;
			}
			
			if(nameAlreadyExists(registerDTO.getName())) {
				System.out.println("** 이미 가입한 닉네임입니다.");
				return false;
			}
			
			String hashedPwd = BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt());
			registerDTO.setPassword(hashedPwd);
			registerDTO.setPassword2(hashedPwd);
			return userDao.insertUser(registerDTO) == 1;
		}
		return false;
	}

	@Override
	public UserInfoDTO login(UserLoginDTO loginDTO) {
		String savedPwd = userDao.getUserPwdByEmail(loginDTO.getEmail());
		if(savedPwd != null && BCrypt.checkpw(loginDTO.getPassword(), savedPwd)) {
			int id = userDao.getUserIdByEmail(loginDTO.getEmail());
			return userDao.selectUserById(id);
		} else {
			return null;			
		}
	}

	@Override
	public boolean emailAlreadyExists(String email) {
		int id = userDao.getUserIdByEmail(email);
		// id가 0보다 크면 이미 같은 email로 가입한 사람 존재
		return id > 0;
	}
	
	@Override
	public boolean nameAlreadyExists(String name) {
		int id = userDao.getUserIdByName(name);
		return id > 0;
	}

	@Override
	public boolean modify(UserInfoDTO userInfoDTO) {
		return userDao.updateUser(userInfoDTO) == 1;
	}
}
