<template>
	<article class="feed-wrap">
		<section class="feed-title">
			<img
				:src="BaseURL"
				:alt="`${article.user.name}의 프로필사진`"
				class="img"
			/>
			<div>
				<p>{{ article.user.name }}</p>
				<time class="feed-time">{{ article.created_at | formatDate }}</time>
			</div>
		</section>
		<section class="feed-content">
			<p class="feed-content-title">{{ article.title }}</p>
			<Viewer :initialValue="article.content" />
		</section>
		<section class="feed-like">
			<p class="feed-like-info">좋아요 {{ article.like.like_cnt }}개</p>
		</section>
	</article>
</template>

<script>
import Viewer from '@toast-ui/vue-editor/src/Viewer.vue';

export default {
	props: {
		article: Object,
	},
	components: {
		Viewer,
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		BaseURL() {
			return this.article.user.profile_image === null
				? `${this.baseURL}upload/noProfile.png`
				: `${this.baseURL}${this.article.user.profile_image}`;
		},
	},
};
</script>

<style lang="scss">
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
	pre {
		width: 100%;
		word-break: break-all;
		box-sizing: border-box;
		overflow-x: hidden;
		code {
			width: 100%;
			height: 100%;
			word-break: break-all;
		}
	}
}
.feed-wrap {
	margin-bottom: 30px;
	padding: 15px 20px;
	border: 1px solid #e0e0e0;
	border-radius: 3px;
	background-color: #ffffff;
}
.feed-title {
	display: flex;
	align-items: center;
	position: relative;
	margin-bottom: 25px;
	.img {
		width: 40px;
		height: 40px;
		border-radius: 20%;
		margin-right: 20px;
	}
	.feed-time {
		margin-top: 5px;
		color: #5a5a5a;
		font-size: 12px;
	}
}
.feed-content {
	min-height: 100px;
	.feed-content-title {
		font-weight: bold;
	}
}
.feed-like {
	margin-top: 20px;
	font-size: $font-light;
	.feed-like-info {
		color: #5a5a5a;
	}
}
</style>
