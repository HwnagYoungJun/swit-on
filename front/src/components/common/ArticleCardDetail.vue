<template>
	<div v-if="article">
		<nav aria-label="Breadcrumb" class="breadcrumb">
			<ol>
				<li>
					<router-link :to="{ name: 'dashboard' }" tabindex="-1">{{
						article.study.name
					}}</router-link
					><span aria-hidden="true">></span>
				</li>
				<li>
					<router-link
						:to="{ name: `${board_name}` }"
						aria-current="page"
						tabindex="-1"
						>{{ board_name }}</router-link
					>
				</li>
			</ol>
		</nav>
		<div class="card-detail-wrap">
			<div class="card-detail">
				<div class="card-detail-title">
					<p>{{ article.title }}</p>
				</div>
				<div class="card-detail-content">
					<Viewer :initialValue="article.content" />
					<div v-if="article.file">
						<a
							v-if="FileCheck"
							:href="`${BaseUrl}${article.file}`"
							target="_blank"
							>{{ article.file | fileDownload }}</a
						>
						<a v-else :href="`${BaseUrl}${article.file}`" download>{{
							article.file | fileDownload
						}}</a>
					</div>
					<div class="logo" v-if="board_name !== 'notice'">
						<div class="logo-likebox">
							<i
								@click="articleUnLike"
								v-if="isLiked"
								class="icon ion-md-heart like"
							></i>
							<i
								@click="articleLike"
								v-else
								class="icon ion-md-heart unlike"
							></i>
							<span>좋아요 {{ likeCount }}개</span>
						</div>
						<div class="logo-btnbox">
							<div class="bookmark" v-if="board_name === 'repository'">
								<i
									v-if="isBookmarked"
									@click="removeBookmark"
									class="icon ion-md-bookmark bookmark"
								></i>
								<i
									v-else
									@click="addBookmark"
									class="icon ion-md-bookmark unlike"
								></i>
							</div>
							<span class="span-btn" @click="editRouter">수정</span>
							<span class="span-btn" @click="removeArticle">삭제</span>
						</div>
					</div>
					<div class="content-info">
						<router-link :to="`/profile/${article.user.name}`">{{
							article.user.name
						}}</router-link>
					</div>
					<div class="comment-body">
						<ul>
							<li
								class="comment-list"
								v-for="comment in article.comments"
								:key="comment.id"
							>
								<div class="comment-contentbox">
									<span class="comment-user">{{ comment.user.name }}</span>
									{{ comment.content }}
								</div>
								<div class="comment-btnbox">
									<i
										v-if="comment.like.liked"
										@click="commentUnLike(comment.id)"
										class="icon ion-md-heart like"
									></i>
									<i
										v-else
										@click="commentLike(comment.id)"
										class="icon ion-md-heart unlike"
									></i>
									<i
										v-if="getName === comment.user.name"
										@click="removeComment(comment.id)"
										class="icon ion-md-trash unlike"
									>
									</i>
								</div>
							</li>
						</ul>
					</div>
					<div class="comment-form">
						<input
							@keypress.enter="
								AddComment();
								resetContent();
							"
							v-model="commentContent"
							type="text"
							class="comment"
							autofocus
							placeholder="댓글달기"
						/>
						<button
							@click.prevent="
								AddComment();
								resetContent();
							"
							class="comment-btn"
						>
							게시
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import { mapGetters } from 'vuex';
import {
	fetchArticle,
	createComment,
	deleteComment,
	deleteArticle,
	createArticleLike,
	deleteArticleLike,
	createArticleCommentLike,
	deleteArticleCommentLike,
	createArticleBookmark,
	deleteArticleBookmark,
} from '@/api/articles';
import '@toast-ui/editor/dist/toastui-editor.css';
import Viewer from '@toast-ui/vue-editor/src/Viewer.vue';
export default {
	props: {
		id: Number,
		board_name: String,
		article_id: Number,
	},
	components: {
		Viewer,
	},
	data() {
		return {
			article: null,
			initText: null,
			commentContent: null,
		};
	},
	computed: {
		...mapGetters(['getName']),
		isLiked() {
			return this.article.like.liked;
		},
		likeCount() {
			return this.article.like.like_cnt;
		},
		isBookmarked() {
			return this.article.isfavorite;
		},
		BaseUrl() {
			return process.env.VUE_APP_API_URL;
		},
		FileCheck() {
			return this.FilenameCheck(this.article.file);
		},
	},

	methods: {
		FilenameCheck(filename) {
			var _fileLen = filename.length;

			var _lastDot = filename.lastIndexOf('.');

			// 확장자 명만 추출한 후 소문자로 변경
			var _fileExt = filename.substring(_lastDot, _fileLen).toLowerCase();

			return this.checkExtension(_fileExt);
		},
		checkExtension(fileExt) {
			var _fileExt = fileExt;
			var _result;

			// 미리보기 가능한 확장자는 배열에 넣기
			var _arrExt = new Array('.bmp', '.gif', '.jpg', '.png', '.jpeg');

			// 배열 요소를 검사하여 체크
			for (var i = 0; i < _arrExt.length; i++) {
				if (_arrExt[i] == _fileExt) {
					_result = true;
					break;
				} else _result = false;
			}

			return _result;
		},
		async fetchData() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				const { data } = await fetchArticle(studyId, boardName, articleId);
				console.log(data);
				this.article = data;
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		isCommentLiked(flag) {
			return flag;
		},
		async addBookmark() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await createArticleBookmark(studyId, boardName, articleId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async removeBookmark() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await deleteArticleBookmark(studyId, boardName, articleId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async AddComment() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				const content = this.commentContent;
				await createComment(studyId, boardName, articleId, {
					content,
				});
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async removeComment(commentId) {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await deleteComment(studyId, boardName, articleId, commentId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async articleLike() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await createArticleLike(studyId, boardName, articleId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async articleUnLike() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await deleteArticleLike(studyId, boardName, articleId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async commentLike(commentId) {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await createArticleCommentLike(
					studyId,
					boardName,
					articleId,
					commentId,
				);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async commentUnLike(commentId) {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await deleteArticleCommentLike(
					studyId,
					boardName,
					articleId,
					commentId,
				);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async removeArticle() {
			try {
				const studyId = this.id;
				const boardName = this.board_name;
				const articleId = this.article_id;
				await deleteArticle(studyId, boardName, articleId);
				this.$router.push(`/study/${studyId}/${boardName}`);
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		resetContent() {
			this.commentContent = null;
		},
		editRouter() {
			this.$router.push(
				`/study/${this.id}/${this.board_name}/${this.article_id}/edit`,
			);
		},
	},
	created() {
		this.fetchData();
	},
	watch: {
		$route: 'fetchData',
	},
};
</script>

<style lang="scss">
.comment-list {
	display: flex;
	justify-content: space-between;
	align-items: center;
}
.comment-btnbox {
	font-size: $font-normal;
	i {
		margin-right: 5px;
	}
}
.breadcrumb {
	ol {
		padding: 0;
	}
	li {
		display: inline;
	}
	a {
		color: rgb(136, 136, 136);
		font-size: $font-light;
		text-decoration: none;
	}
	span {
		margin: 5px;
	}
}
.card-detail-wrap {
	width: 100%;
	margin-bottom: 50px;
	padding: 15px 0;
	border-radius: 3px;
}
.card-detail-title {
	display: flex;
	margin: 10px;
	p {
		// margin: 5px 15px;
		font-size: $font-bold;
	}
}
.img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}
.card-detail-content {
	padding: 10px;
	.tui-editor-contents {
		p {
			width: 100%;
			height: 100%;
			word-break: break-all;
			.colour {
				word-break: break-all;
			}
			img {
				width: 100%;
				height: 100%;
				object-fit: cover;
			}
		}
	}
	.main-img {
		width: 100%;
		margin: 30px 0;
	}
	.logo {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin: 50px 0 10px;
		color: crimson;
		font-size: $font-bold;
		.logo-likebox {
			display: flex;
			align-items: center;
		}
		.logo-btnbox {
			display: flex;
			align-items: center;
			.span-btn {
				margin-left: 5px;
			}
		}
		i {
			margin-right: 5px;
			cursor: pointer;
		}
		span {
			font-size: $font-light;
			color: #999999;
		}
		.bookmark {
			color: #ffe066;
		}
	}
	.content-info {
		margin: 10px 0 15px;
		a {
			margin-right: 15px;
			font-weight: bold;
			color: #636363;
		}
		span {
			display: inline-block;
			width: 80px;
			margin: 0 3px;
			padding: 3px 0;
			border: 1px solid #6c23c0;
			border-radius: 20px;
			color: #6c23c0;
			font-size: $font-light;
			background: none;
			text-align: center;
		}
	}
	.comment-form {
		position: relative;
		.comment {
			width: 100%;
			height: 45px;
			padding: 0.8rem;
			border: none;
			border-top: 1px solid #dbdbdb;
			color: #999999;
			font-size: $font-normal;
			&:focus {
				outline: none;
			}
		}
		.comment-btn {
			position: absolute;
			top: 15px;
			right: 10px;
			background: none;
			border: none;
			color: $main-color;
			cursor: pointer;
			&:focus {
				outline: none;
			}
		}
	}
	.comment-user {
		margin-right: 8px;
		font-weight: bold;
		cursor: pointer;
	}
}
.like {
	color: crimson;
}
.unlike {
	color: grey;
}
.span-btn {
	cursor: pointer;
}
</style>
