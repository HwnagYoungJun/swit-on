<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else class="card-wrap">
		<div v-if="!articles">
			<ArticleNotFound />
		</div>
		<div class="article-wrap" v-else>
			<router-link
				class="article-feed"
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
			<ArticleRank class="article-rank" />
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
<style lang="scss">
.card-wrap {
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	position: relative;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
.article-wrap {
	display: flex;
	flex-wrap: wrap;
	align-items: flex-start;
	.article-feed {
		flex: 2;
	}
	.article-rank {
		flex: 1;
	}
}
</style>
