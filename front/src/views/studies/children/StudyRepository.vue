<template>
	<div class="card-wrap">
		<ArticleCard
			v-for="article in articles"
			:key="article.id"
			:article="article"
		/>
		<h1 v-for="article in articles" :key="article.id">
			{{ article.created_at | formatDate }}
		</h1>
		{{ string | truncate }}
		<!-- <ArticleCard />
		<ArticleCard /> -->
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import { fetchRepositoryArticles } from '@/api/articles';
export default {
	data() {
		return {
			articles: [],
			string:
				'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Magnam natus officia minus deserunt, vitae cumque harum. Libero cumque error culpa, non praesentium beatae, at quaerat quia quasi provident sunt impedit?Lorem, ipsum dolor sit amet consectetur adipisicing elit. Magnam natus officia minus deserunt, vitae cumque harum. Libero cumque error culpa, non praesentium beatae, at quaerat quia quasi provident sunt impedit?',
		};
	},
	components: {
		ArticleCard,
	},
	methods: {
		async fetchRepo() {
			const studyId = this.$route.params.id;
			const { data } = await fetchRepositoryArticles(studyId);
			this.articles = data;
		},
	},
	created() {
		this.fetchRepo();
	},
};
</script>
<style lang="scss" scoped>
.card-wrap {
	display: flex;
	flex-wrap: wrap;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
</style>
