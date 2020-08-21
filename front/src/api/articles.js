import { boardArticles, baseAuth } from './index';

function fetchFeeds(index) {
	return baseAuth.get(`/feeds/?index=${index}`);
}

function fetchArticles(studyId, boardName, index) {
	return boardArticles.get(`/${studyId}/${boardName}?index=${index}`);
}

function createArticle(studyId, boardName, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('upload', articleData.file);
	return boardArticles.post(`/${studyId}/${boardName}/`, formdata);
}

function deleteArticle(studyId, boardName, articleId) {
	return boardArticles.delete(`/${studyId}/${boardName}/${articleId}/`);
}

function updateArticle(studyId, boardName, articleId, articleData) {
	const formdata = new FormData();
	formdata.append('title', articleData.title);
	formdata.append('content', articleData.content);
	formdata.append('upload', articleData.file);
	return boardArticles.put(`/${studyId}/${boardName}/${articleId}/`, formdata);
}

function createArticleBookmark(studyId, boardName, articleId) {
	return boardArticles.post(`/${studyId}/${boardName}/${articleId}/fav/`);
}
function deleteArticleBookmark(studyId, boardName, articleId) {
	return boardArticles.delete(`/${studyId}/${boardName}/${articleId}/fav/`);
}

function fetchArticle(studyId, boardName, articleId) {
	return boardArticles.get(`/${studyId}/${boardName}/${articleId}/`);
}

function createComment(studyId, boardName, articleId, commentData) {
	return boardArticles.post(
		`/${studyId}/${boardName}/${articleId}/comments/`,
		commentData,
	);
}
function deleteComment(studyId, boardName, articleId, commentId) {
	return boardArticles.delete(
		`/${studyId}/${boardName}/${articleId}/comments/${commentId}`,
	);
}

function createArticleLike(studyId, boardName, articleId) {
	return boardArticles.post(`/${studyId}/${boardName}/${articleId}/like/`);
}
function deleteArticleLike(studyId, boardName, articleId) {
	return boardArticles.delete(`/${studyId}/${boardName}/${articleId}/like/`);
}

function createArticleCommentLike(studyId, boardName, articleId, commentId) {
	return boardArticles.post(
		`/${studyId}/${boardName}/${articleId}/${commentId}/like/`,
	);
}
function deleteArticleCommentLike(studyId, boardName, articleId, commentId) {
	return boardArticles.delete(
		`/${studyId}/${boardName}/${articleId}/${commentId}/like/`,
	);
}

export {
	fetchArticles,
	fetchArticle,
	createArticle,
	deleteArticle,
	updateArticle,
	fetchFeeds,
	createComment,
	deleteComment,
	createArticleLike,
	deleteArticleLike,
	createArticleCommentLike,
	deleteArticleCommentLike,
	createArticleBookmark,
	deleteArticleBookmark,
};
