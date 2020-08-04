<template>
	<div class="card-wrap">
		<ArticleCard
			v-for="article in articles"
			:key="article.id"
			:article="article"
		/>
		<!-- <ArticleCard />
		<ArticleCard /> -->
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import { fetchNoticeArticles } from '@/api/articles';
export default {
	data() {
		return {
			articles: [],
		};
	},
	components: {
		ArticleCard,
	},
	methods: {
		async fetchNotice() {
			const studyId = this.$route.params.id;
			const { data } = await fetchNoticeArticles(studyId);
			this.articles = data;
		},
	},
	created() {
		this.fetchNotice();
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
