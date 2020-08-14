<template>
	<div class="card-wrap">
		<div v-if="articles === []">
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
		<InfiniteLoading
			@infinite="infiniteHandler"
			spinner="waveDots"
		></InfiniteLoading>
	</div>
</template>

<script>
import ArticleFeed from '@/components/common/ArticleFeed.vue';
import ArticleRank from '@/components/common/ArticleRank.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import { fetchArticles } from '@/api/articles';
import InfiniteLoading from 'vue-infinite-loading';

export default {
	props: {
		id: Number,
	},
	data() {
		return {
			limit: 0,
			articles: null,
		};
	},
	components: {
		ArticleFeed,
		ArticleRank,
		ArticleAddBtn,
		ArticleNotFound,
		InfiniteLoading,
	},
	methods: {
		async fetchRepo() {
			const studyId = this.id;
			const { data } = await fetchArticles(studyId, 'repository', this.limit);
			console.log(data);
			this.limit += 5;
			this.articles === [] ? data : [...this.articles, data];
		},
		async infiniteHandler($state) {
			try {
				const studyId = this.id;
				const { data } = await fetchArticles(
					studyId,
					'repository',
					this.limit + 5,
				);
				console.log(data);
				if (data.length) {
					this.articles.concat(data);
					$state.loaded();
					this.limit += 5;
					if (this.articles.length / 5 === 0) {
						$state.complete();
					}
				} else {
					$state.complete();
				}
			} catch (error) {
				console.log(error);
			}
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
