import { articles } from './index';

function fetchArticles() {
	return articles.get('/');
}

function createArticle(studyId, boardName, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('file', articleData.file);

	return articles.post(`/${studyId}/${boardName}`, formdata);
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

export {
	fetchArticles,
	fetchArticle,
	createArticle,
	deleteArticle,
	updateArticle,
};
