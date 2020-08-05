<template>
	<router-link>
		<div class="card">
			<div class="card-info">
				<p class="card-info-title">{{ article.title }}</p>
				<div>
					<span class="study-info">
						<img src="@/assets/color.png" alt="" />
						<!-- <span>{{ article.study.name }}</span> -->
					</span>
					<span class="user-info">
						<img src="@/assets/dd.png" alt="" />
						<!-- <span>{{ article.user.name }}</span> -->
					</span>
					<p>{{ article.created_at | formatDate }}</p>
				</div>
			</div>
			<div class="card-content">
				<p>{{ article.content }}</p>
			</div>
			<!-- {{ article }} -->
		</div>
	</router-link>
</template>

<script>
import { fetchRepositoryArticle } from '@/api/articles';

export default {
	data() {
		return {
			article: null,
		};
	},
	methods: {
		async fetchArticle() {
			const studyId = this.$route.params.id;
			const repoId = this.$route.params.repo_id;
			const { data } = await fetchRepositoryArticle(studyId, repoId);
			this.article = data;
		},
	},
	created() {
		this.fetchArticle();
	},
};
</script>

<style lang="scss" scoped>
.card {
	width: 505px;
	margin: 10px 5px 50px;
	padding: 15px;
	border-bottom: 1px solid rgb(228, 228, 228);
	.card-info {
		margin-bottom: 30px;
		font-size: $font-light;
		position: relative;
		.card-info-title {
			margin-bottom: 8px;
			font-size: $font-bold;
		}
		div {
			color: rgb(90, 90, 90);
			.study-info {
				display: flex;
				// flex-direction: column;
				// justify-content: center;
				align-items: center;
				img {
					margin-right: 5px;
					width: 20px;
				}
			}
			.user-info {
				display: flex;
				flex-direction: column;
				align-items: center;
				position: absolute;
				top: 10px;
				right: 10px;
				img {
					width: 50px;
				}
			}
			p {
				margin-top: 10px;
			}
		}
	}
}
</style>
