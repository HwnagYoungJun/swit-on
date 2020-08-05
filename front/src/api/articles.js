import { boardArticles, articles } from './index';

function fetchArticles() {
	return articles.get('/');
}

function createArticle(studyId, boardName, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('file', articleData.file);
	return boardArticles.post(`/${studyId}/${boardName}`, formdata);
}

function deleteArticle(articleId) {
	return articles.delete(`/${articleId}`);
}

function updateArticle(articleId, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('file', articleData.file);
	return articles.put(`/${articleId}`, formdata);
}

function fetchArticle(articleId) {
	return articles.get(`/${articleId}`);
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

export {
	fetchArticles,
	fetchArticle,
	createArticle,
	deleteArticle,
	updateArticle,
	fetchRepositoryArticles,
	fetchQnaArticles,
	fetchNoticeArticles,
};
