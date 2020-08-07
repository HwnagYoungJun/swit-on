import { study } from './index';
// 스터디 데이터 조회 API
function fetchStudies() {
	return study.get('/');
}

// 스터디 생성 API
function createStudy(studyData) {
	const formdata = new FormData();
	formdata.append('img', studyData.img);
	formdata.append('name', studyData.name);
	formdata.append('description', studyData.description);
	formdata.append('lowercategory_id', studyData.lowercategory_id);
	formdata.append('isPrivate', studyData.isPrivate);
	formdata.append('isRecruit', studyData.isRecruit);
	formdata.append('start_time', studyData.start_time);
	formdata.append('end_time', studyData.end_time);
	formdata.append('start_term', studyData.start_term);
	formdata.append('end_term', studyData.end_term);
	formdata.append('week', studyData.week);

	return study.post('/', formdata);
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
