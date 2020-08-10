// 로그인, 회원가입, 회원탈퇴
import { auth, baseAuth } from './index';

// 회원가입 API
function registerUser(userData) {
	return auth.post('accounts/register', userData);
}

// 로그인 API
function loginUser(userData) {
	return auth.post('accounts/login', userData);
}

function fetchProfile(userName) {
	return baseAuth.get(`accounts/${userName}`);
}

export { registerUser, loginUser, fetchProfile };
