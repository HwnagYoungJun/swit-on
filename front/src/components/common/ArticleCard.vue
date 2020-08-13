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
				<p class="card-info-all">
					<span v-if="article.user">
						<span class="card-info-user">{{ article.user.name }}</span>
					</span>
					<span class="card-info-time">{{
						article.created_at | formatDate
					}}</span>
				</p>
				<p class="card-content-viewer">
					<!-- <Viewer :initialValue="article.content | truncate" /> -->
					<!-- {{ article.content | truncate }} -->
				</p>
			</div>
		</div>
	</div>
</template>

<script>
// import Viewer from '@toast-ui/vue-editor/src/Viewer.vue';

export default {
	props: {
		article: Object,
	},
	components: {
		// Viewer,
	},
};
</script>

<style lang="scss">
.card {
	width: 480px;
	height: 150px;
	margin: 10px 5px 10px;
	// padding: 20px 10px;
	border-radius: 5px;
	background: #fff;
	box-shadow: 0px 5px 4px rgba(175, 175, 175, 0.2);
	position: relative;
	overflow: hidden;
	// &::before {
	// 	content: '';
	// 	display: block;
	// 	width: 150px;
	// 	height: 200px;
	// 	background: $btn-purple-opacity;
	// 	position: absolute;
	// 	transform: translate(-55px, -30px) skewX(23deg);
	// }
	.card-info {
		display: flex;
		//align-items: center;
		font-size: $font-light;
		position: relative;
		overflow: hidden;
		.card-img {
			flex: 1;
			display: grid;
			place-items: center;
			img {
				width: 80%;
				// border-radius: 50%;
			}
		}
		.card-content {
			flex: 2;
			.card-info-title {
				// text-decoration: underline;
				// text-decoration-color:linear-gradient(to right, #9200b9 8%, #6c23c0 75%, #5600c7);
				margin-bottom: 8px;
				font-size: $font-bold;
				position: relative;
				// border-bottom: 2px solid rgba(169, 169, 169, 1);
				padding-bottom: 15px;
				.bread-span {
					background-color: rgba(86, 0, 199, 0.1);
					width: 65%;
					height: 6px;
					left: 0;
					position: absolute;
					bottom: 3px;
					font-size: $font-light;
				}
			}
			.card-info-all {
				position: absolute;
				bottom: 5px;
				right: 5px;
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
				margin-right: 10px;
			}
		}
	}
}
</style>
