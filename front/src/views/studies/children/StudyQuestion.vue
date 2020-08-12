<template>
	<div class="card-wrap">
		<div v-if="!articles">
			<ArticleNotFound />
		</div>
		<router-link
			v-else
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
			<ArticleCard :article="article">
				<div slot="logo">
					<img src="@/assets/color.png" alt="" />
				</div>
			</ArticleCard>
		</router-link>
		<ArticleAddBtn boardName="qna" />
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import { fetchArticles } from '@/api/articles';
export default {
	props: {
		id: Number,
	},
	data() {
		return {
			articles: null,
		};
	},
	components: {
		ArticleCard,
		ArticleAddBtn,
		ArticleNotFound,
	},
	methods: {
		async fetchQna() {
			const studyId = this.id;
			const { data } = await fetchArticles(studyId, 'qna');
			this.articles = data.length ? data : null;
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
