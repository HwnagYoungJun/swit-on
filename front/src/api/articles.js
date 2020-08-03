import { articles } from './index';

function fetchArticles() {
	return articles.get('/');
}

function createArticle(articleData) {
	return articles.post('/', articleData);
}

function deleteArticle(articleId) {
	return articles.delete(`/${articleId}`);
}

function updateArticle(articleId, articleData) {
	return articles.put(`/${articleId}`, articleData);
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
