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
					board_name: 'repository',
					article_id: article.id,
				},
			}"
		>
			<ArticleFeed :article="article">
				<!-- <div slot="logo">
					<img src="@/assets/color2.png" alt="" />
				</div> -->
			</ArticleFeed>
		</router-link>
		<ArticleAddBtn boardName="repository" />
	</div>
</template>

<script>
import ArticleFeed from '@/components/common/ArticleFeed.vue';
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
		ArticleFeed,
		ArticleAddBtn,
		ArticleNotFound,
	},
	methods: {
		async fetchRepo() {
			const studyId = this.id;
			const { data } = await fetchArticles(studyId, 'repository');
			this.articles = data.length ? data : null;
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
