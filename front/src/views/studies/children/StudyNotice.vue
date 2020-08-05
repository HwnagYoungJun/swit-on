<template>
	<div class="card-wrap">
		<ArticleCard
			v-for="article in articles"
			:key="article.id"
			:article="article"
		/>
		<ArticleAddBtn boardName="notice" />
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import { fetchNoticeArticles } from '@/api/articles';
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
	position: relative;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
</style>
