import { study } from './index';

// 스터디 데이터 조회 API
function fetchStudies() {
	return study.get('/');
}

// 스터디 생성 API
function createStudy(studyData) {
	return study.post('/create', studyData);
}

function deleteStudy(studyId) {
	return study.delete(`/${studyId}`);
}

function updateStudy(studyId, studyData) {
	return study.put(`/${studyId}`, studyData);
}

function fetchStudy(studyId) {
	return study.get(`/${studyId}`);
}

function JoinStudy(studyId) {
	return study.post(`/${studyId}/join`);
}

export {
	fetchStudies,
	createStudy,
	deleteStudy,
	updateStudy,
	fetchStudy,
	JoinStudy,
};
