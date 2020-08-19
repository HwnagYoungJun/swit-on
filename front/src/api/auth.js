// 로그인, 회원가입, 회원탈퇴
import { auth, baseAuth, alarm } from './index';

// 회원가입 API
function registerUser(userData) {
	return auth.post('accounts/register', userData);
}

// 로그인 API
function loginUser(userData) {
	return auth.post('accounts/login', userData);
}

// 검증 API
function ValidEmail(email) {
	return auth.get(`check/?email=${email}`);
}

function ValidName(name) {
	return auth.get(`check/?name=${name}`);
}

// 유저 API
function fetchAlarms(userId) {
	return alarm.get(`user/${userId}`);
}

function fetchProfile(userName) {
	return baseAuth.get(`accounts/${userName}`);
}

function fetchMyStudy(userName) {
	console.log(`accounts/${userName}/endstudy`);
	return baseAuth.get(`accounts/${userName}/endstudy`);
}

function fetchMyRepository(userName) {
	console.log(`accounts/${userName}/myrepository`);
	return baseAuth.get(`accounts/${userName}/myrepository`);
}
function fetchMyQNA(userName) {
	console.log(`accounts/${userName}/myqna`);
	return baseAuth.get(`accounts/${userName}/myqna`);
}

function fetchMySchedule(userName) {
	console.log(`accounts/${userName}/myschedule`);
	return baseAuth.get(`accounts/${userName}/myschedule`);
}
function fetchMyFav(userName) {
	console.log(`accounts/${userName}/fav`);
	return baseAuth.get(`accounts/${userName}/fav`);
}
export {
	fetchMyFav,
	registerUser,
	loginUser,
	fetchProfile,
	fetchMyStudy,
	fetchMyRepository,
	fetchMyQNA,
	fetchMySchedule,
	fetchAlarms,
	ValidEmail,
	ValidName,
};
