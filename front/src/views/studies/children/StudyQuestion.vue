<template>
	<div class="card-wrap">
		<router-link
			v-for="article in articles"
			:key="article.id"
			:to="{
				name: 'BoardArticleDetail',
				params: {
					id,
					board_name: 'qna',
					article_id: article.id,
				},
			}"
		>
			<ArticleCard :article="article" />
		</router-link>
		<ArticleAddBtn boardName="qna" />
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import { fetchArticles } from '@/api/articles';
export default {
	props: {
		id: Number,
	},
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
		async fetchQna() {
			const studyId = this.id;
			const { data } = await fetchArticles(studyId, 'qna');
			this.articles = data.reverse();
		},
	},
	created() {
		this.fetchQna();
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
