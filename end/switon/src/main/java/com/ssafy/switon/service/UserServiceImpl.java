package com.ssafy.switon.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Medals;
import com.ssafy.switon.dto.User;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserInfoWithMedals;
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	JoinDAO joinDao;
	
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
			
			// 패스워드 암호화 해주기
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
	
	@Override
	public boolean nameAlreadyExist(int userId, String newName) {
		int newNameUserId = userDao.getUserIdByName(newName);
		// 내가 쓰던 이름이 아닌 동시에 다른 사람이 쓰고 있는 이름이라면 true 반환
		return newNameUserId != userId || newNameUserId > 0;
	}

	@Override
	public boolean signOut(int userId) {
		UserInfoDTO userInfoDTO = userDao.selectUserById(userId);
		return userDao.updateUserLegacy(userInfoDTO) == 1;
	}

	@Override
	public int searchUserIdByName(String name) {
		return userDao.getUserIdByName(name);
	}

	@Override
	public User findUserByEmail(String email) {
		UserInfoDTO user = userDao.selectUserById(userDao.getUserIdByEmail(email));
		return new User(user);
	}

	@Override
	public int findUserIdByEmail(String email) {
		return userDao.getUserIdByEmail(email);
	}

	@Override
	public boolean pwChange(int id, String password) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt());
		userDTO.setPassword(hashedPwd);
		return userDao.updateUserPwd(userDTO) == 1;
	}

	@Override
	public UserInfoWithMedals addMedalsToUserInfo(UserInfoDTO user) {
		Medals medals = joinDao.selectMedalsByUserId(user.getId());
		UserInfoWithMedals userInfo = new UserInfoWithMedals(user, medals);
		return userInfo;
	}
}
