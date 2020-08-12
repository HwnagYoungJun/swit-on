<template>
	<div class="card">
		<div class="card-info">
			<div class="card-img">
				<slot name="logo">
					<img src="@/assets/dd.png" alt="" />
					<!-- {{ article.study.name }} -->
				</slot>
			</div>
			<div class="card-content">
				<p class="card-info-title">
					{{ article.title }}
					<span class="bread-span"><slot name="bread"> </slot></span>
				</p>
				<p>
					<span v-if="article.user">
						<span class="card-info-user">{{ article.user.name }}</span>
					</span>
					<span class="card-info-time">{{
						article.created_at | formatDate
					}}</span>
				</p>
				<p>
					{{ article.content | truncate }}
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
};
</script>

<style lang="scss" scoped>
.card {
	width: 480px;
	margin: 10px 5px 50px;
	padding: 30px 15px;
	border-radius: 2px;
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
		transform: translate(-85px, -30px) skewX(23deg);
	}
	.card-info {
		display: flex;
		align-items: center;
		font-size: $font-light;
		position: relative;
		overflow: hidden;
		.card-img {
			flex: 1;
			display: grid;
			place-items: center;
			img {
				width: 100px;
				border-radius: 50%;
			}
		}
		.card-content {
			flex: 2;
			.card-info-title {
				margin-bottom: 8px;
				font-size: $font-bold;
				position: relative;
				.bread-span {
					position: absolute;
					bottom: 3px;
					margin-left: 10px;
					font-size: $font-light;
				}
			}
			p {
				margin-top: 10px;
			}
			span {
				margin-right: 10px;
			}
		}
	}
}
</style>
