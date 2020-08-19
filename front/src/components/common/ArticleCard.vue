<template>
	<section class="card" v-if="article">
		<div class="card-info">
			<div class="card-content">
				<h3 class="card-info-title">
					{{ trunk(article.title) }}
					<span class="bread-span"></span>
				</h3>
				<p class="card-info-board">
					<slot name="bread"> </slot>
				</p>
				<p class="card-info-all">
					<img :src="BaseURL" class="card-info-image" alt="카드로고" />
					<span>
						<span v-if="article.user !== undefined" class="card-info-user">{{
							article.user.name
						}}</span>
						<span v-else class="card-info-user">{{ article.study.name }}</span>
					</span>
					<time class="card-info-time">{{
						article.created_at | formatDate
					}}</time>
				</p>
			</div>
		</div>
	</section>
</template>

<script>
export default {
	props: {
		article: Object,
	},
	methods: {
		trunk(str) {
			if (str.length > 25) {
				return str.substr(0, 25) + ' ...';
			}
			return str;
		},
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		BaseURL() {
			return this.article.user === undefined
				? this.article.study.logo === null
					? `${this.baseURL}upload/noStudy.jpg`
					: `${this.baseURL}${this.article.study.logo}`
				: this.article.user.profile_image === null
				? `${this.baseURL}upload/noProfile.png`
				: `${this.baseURL}${this.article.user.profile_image}`;
		},
	},
};
</script>

<style lang="scss">
.card {
	width: 30rem;
	height: 7rem;
	margin: 10px 5px 10px;
	border-radius: 5px;
	background: #fff;
	box-shadow: 0px 5px 4px rgba(175, 175, 175, 0.2);
	position: relative;
	overflow: hidden;
	&::before {
		content: '';
		display: block;
		width: 150px;
		height: 200px;
		background: $btn-purple-opacity;
		position: absolute;
		transform: translate(-90px, -30px) skewX(23deg);
		opacity: 0.7;
	}
	.card-info {
		display: flex;
		height: 100%;
		font-size: $font-light;
		position: relative;
		overflow: hidden;
		.card-info-board {
			position: absolute;
			font-weight: 600;
			left: 15px;
			bottom: 0px;
			color: white;
		}
		.card-content {
			flex: 1;
			padding: 0.4rem 1rem;
			position: relative;
			.card-info-title {
				display: inline-block;
				margin-top: 0;
				margin-bottom: 8px;
				font-size: $font-normal * 1.1;
				font-weight: normal;
				position: relative;
				padding-bottom: 8px;
				left: 50px;
				.bread-span {
					background-color: rgba(86, 0, 199, 0.1);
					height: 6px;
					left: 0;
					right: 0;
					position: absolute;
					bottom: 3px;
					border-radius: 1px;
					font-size: $font-light;
				}
			}
			.card-info-all {
				display: flex;
				align-items: center;
				position: absolute;
				bottom: 0px;
				right: 5px;
				.card-info-image {
					width: 20px;
					height: 20px;
					margin-right: 2px;
					border-radius: 5px;
				}
				span {
					margin-right: 8px;
				}
			}
			.card-info-time {
				color: rgba(77, 45, 80, 0.7);
			}
			.card-info-user {
				color: rgba(94, 54, 97, 1);
				font-weight: 600;
			}
			p {
				margin-top: 10px;
				margin-bottom: 10px;
			}
		}
	}
}
</style>
