<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else class="card-wrap">
		<div v-if="articles === []">
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
			<ArticleAddBtn boardName="repository" />
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
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
			limit: 0,
			loading: false,
			articles: [],
			windowTop: 0,
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
			try {
				const studyId = this.id;
				this.loading = true;
				const { data } = await fetchArticles(studyId, 'repository', this.limit);
				this.loading = false;
				this.limit += 5;
				this.articles = data;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async infiniteLoading() {
			try {
				const studyId = this.id;
				const { data } = await fetchArticles(studyId, 'repository', this.limit);
				this.limit += 5;
				if (data.length) {
					this.articles = [...this.articles, ...data];
				}
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	computed: {
		isInfinite() {
			return (
				document.querySelector('body').scrollHeight >
				this.windowTop + screen.height
			);
		},
	},
	watch: {
		isInfinite: function() {
			if (!this.isInfinite) {
				this.infiniteLoading();
			}
		},
	},
	created() {
		this.fetchRepo();
		window.addEventListener('scroll', () => {
			this.windowTop = window.scrollY;
		});
	},
};
</script>
<style lang="scss" scoped>
.card-wrap {
	height: 100%;
	display: flex;
	flex-wrap: wrap;
	position: relative;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
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
