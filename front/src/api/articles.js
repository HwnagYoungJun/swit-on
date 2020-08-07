import { boardArticles, baseAuth } from './index';

function fetchFeeds() {
	return baseAuth.get('/feeds');
}
function fetchArticles(studyId, boardName) {
	return boardArticles.get(`/${studyId}/${boardName}/`);
}

function createArticle(studyId, boardName, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('file', articleData.file);
	return boardArticles.post(`/${studyId}/${boardName}`, formdata);
}

function deleteArticle(studyId, boardName, articleId) {
	return boardArticles.delete(`/${studyId}/${boardName}/${articleId}`);
}

function updateArticle(studyId, boardName, articleId, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('file', articleData.file);
	return boardArticles.put(`/${studyId}/${boardName}/${articleId}`, formdata);
}

function fetchArticle(studyId, boardName, articleId) {
	return boardArticles.get(`/${studyId}/${boardName}/${articleId}`);
}

function fetchRepositoryArticles(studyId) {
	return boardArticles.get(`/${studyId}/repository/`);
}
function fetchQnaArticles(studyId) {
	return boardArticles.get(`/${studyId}/qna/`);
}
function fetchNoticeArticles(studyId) {
	return boardArticles.get(`/${studyId}/notice/`);
}

function createComment(studyId, boardName, articleId, commentData) {
	return boardArticles.post(
		`/${studyId}/${boardName}/${articleId}/comments`,
		commentData,
	);
}

export {
	fetchArticles,
	fetchArticle,
	createArticle,
	deleteArticle,
	updateArticle,
	fetchRepositoryArticles,
	fetchQnaArticles,
	fetchNoticeArticles,
	fetchFeeds,
	createComment,
};
