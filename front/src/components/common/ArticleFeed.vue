<template>
	<div class="feed-wrap">
		<div class="feed-title">
			<img src="@/assets/color.png" alt="profile-image" class="img" />
			<div>
				<p>{{ article.user.name }}</p>
				<p class="feed-time">{{ article.created_at | formatDate }}</p>
			</div>
		</div>
		<div class="feed-content">
			<p class="feed-content-title">{{ article.title }}</p>
			<Viewer :initialValue="article.content" />
		</div>
		<div class="feed-like">
			<p class="feed-like-info">좋아요 {{ article.like.like_cnt }}개</p>
		</div>
	</div>
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
