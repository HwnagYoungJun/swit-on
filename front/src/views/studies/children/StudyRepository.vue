<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else class="card-wrap">
		<div v-if="!articles">
			<ArticleNotFound />
		</div>
		<div class="article-wrap" v-else>
			<div class="article-feed-wrap">
				<router-link
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
					<ArticleFeed :article="article" />
				</router-link>
			</div>
			<div class="rank-wrap">
				<ArticleRank />
			</div>
		</div>
		<ArticleAddBtn boardName="repository" />
	</div>
</template>

<script>
import ArticleFeed from '@/components/common/ArticleFeed.vue';
import ArticleRank from '@/components/common/ArticleRank.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import Loading from '@/components/common/Loading.vue';
import { fetchArticles } from '@/api/articles';

export default {
	props: {
		id: Number,
	},
	data() {
		return {
			articles: null,
			loading: false,
		};
	},
	components: {
		ArticleFeed,
		ArticleRank,
		ArticleAddBtn,
		ArticleNotFound,
		Loading,
	},
	methods: {
		async fetchRepo() {
			const studyId = this.id;
			this.loading = true;
			const { data } = await fetchArticles(studyId, 'repository');
			this.loading = false;
			this.articles = data.length ? data : null;
		},
	},
	created() {
		this.fetchRepo();
	},
};
</script>
<style lang="scss" scoped>
.article-wrap {
	display: flex;
	flex-wrap: wrap;
	.article-feed-wrap {
		flex: 2;
		display: flex;
		flex-direction: column;
		margin-right: 100px;
		@media screen and (max-width: 992px) {
			margin-right: 0;
		}
	}
	.rank-wrap {
		flex: 1;
		height: 100%;
		position: sticky;
		top: 50px;
		@media screen and (max-width: 992px) {
			display: none;
		}
	}
}
</style>
