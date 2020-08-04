<template>
	<div class="card-wrap">
		<ArticleCard
			v-for="article in articles"
			:key="article.id"
			:article="article"
		/>
		<ArticleAddBtn boardName="repository" />
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import { fetchRepositoryArticles } from '@/api/articles';
export default {
	data() {
		return {
			articles: [],
		};
	},
	components: {
		ArticleCard,
		ArticleAddBtn,
	},
	methods: {
		async fetchRepo() {
			const studyId = this.$route.params.id;
			const { data } = await fetchRepositoryArticles(studyId);
			this.articles = data.reverse();
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
	position: relative;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
</style>
