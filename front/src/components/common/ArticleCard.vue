<template>
	<div class="card" v-if="article">
		<div class="card-info">
			<div class="card-content">
				<p class="card-info-title">
					{{ article.title }}
					<span class="bread-span"><slot name="bread"> </slot></span>
				</p>
				<p class="card-info-all">
					<img :src="BaseURL" class="card-info-image" />
					<span>
						<span v-if="article.user !== undefined" class="card-info-user">{{
							article.user.name
						}}</span>
						<span v-else class="card-info-user">{{ article.study.name }}</span>
					</span>
					<span class="card-info-time">{{
						article.created_at | formatDate
					}}</span>
				</p>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		article: Object,
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		BaseURL() {
			return this.article.user === undefined
				? `${this.baseURL}${this.article.study.logo}`
				: `${this.baseURL}${this.article.user.profile_image}`;
		},
	},
};
</script>

<style lang="scss">
.card {
	width: 480px;
	height: 7rem;
	margin: 10px 5px 10px;
	// padding: 20px 10px;
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
		//align-items: center;
		font-size: $font-light;
		position: relative;
		overflow: hidden;
		// .card-img {
		// 	flex: 1;
		// 	display: grid;
		// 	place-items: center;
		// 	img {
		// 		width: 80%;
		// 		// border-radius: 50%;
		// 	}
		// }
		.card-content {
			flex: 1;
			padding: 1rem;
			position: relative;
			.card-info-title {
				margin-top: 0;
				margin-bottom: 8px;
				font-size: $font-bold;
				position: relative;
				padding-bottom: 8px;
				left: 50px;
				.bread-span {
					background-color: rgba(86, 0, 199, 0.1);
					width: 40%;
					height: 6px;
					left: 0;
					position: absolute;
					bottom: 3px;
					font-size: $font-light;
				}
			}
			.card-info-all {
				display: flex;
				align-items: center;
				position: absolute;
				bottom: 5px;
				right: 5px;
				.card-info-image {
					width: 20px;
					height: 20px;
					margin-right: 2px;
					border-radius: 5px;
				}
			}
			.card-info-time {
				color: rgba(94, 54, 97, 0.5);
				font-weight: 600;
			}
			.card-info-user {
				color: rgba(94, 54, 97, 1);
				font-weight: 600;
			}
			p {
				margin-top: 10px;
				margin-bottom: 10px;
			}
			span {
				margin-right: 8px;
			}
		}
	}
}
</style>
