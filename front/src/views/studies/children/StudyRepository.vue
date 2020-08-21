<template>
	<section v-if="loading">
		<Loading />
	</section>
	<section v-else class="card-wrap">
		<UpperBtn></UpperBtn>
		<ArticleAddBtn boardName="repository" />
		<div v-if="!articles.length">
			<ArticleNotFound />
		</div>
		<article class="article-wrap" v-else>
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
			<aside class="rank-wrap">
				<ArticleRank
					:bestMember="bestMember"
					:bestArticle="bestArticle"
					:studyId="id"
				/>
			</aside>
		</article>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
import ArticleFeed from '@/components/common/ArticleFeed.vue';
import ArticleRank from '@/components/common/ArticleRank.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import Loading from '@/components/common/Loading.vue';
import UpperBtn from '@/components/common/UpperBtn.vue';
import { fetchArticles } from '@/api/articles';
import { bestMember, bestArticle } from '@/api/studies';

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
			bestMember: ['1등이없어요', '2등이없어요', '3등이없어요'],
			bestArticle: ['1등이없어요', '2등이없어요', '3등이없어요'],
		};
	},
	components: {
		ArticleFeed,
		ArticleRank,
		ArticleAddBtn,
		ArticleNotFound,
		Loading,
		UpperBtn,
	},
	methods: {
		async fetchBest() {
			try {
				const studyId = this.id;
				const { data } = await bestMember(studyId);
				const res = await bestArticle(studyId);
				this.bestMember = data.rankers;
				this.bestArticle = res.data.bestRepos;
				if (this.bestMember.length < 3) {
					for (let i = this.bestMember.length; i < 3; i++) {
						this.bestMember.push(i);
					}
				}
				if (this.bestArticle.length < 3) {
					for (let i = this.bestArticle.length; i < 3; i++) {
						this.bestArticle.push(i);
					}
				}
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
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
				if (!this.windowTop == 0) {
					return;
				}
				this.infiniteLoading();
			}
		},
		$route() {
			this.fetchRepo();
			this.fetchBest();
			window.addEventListener('scroll', () => {
				this.windowTop = window.scrollY;
			});
		},
	},
	created() {
		this.fetchRepo();
		this.fetchBest();
		window.addEventListener('scroll', () => {
			this.windowTop = window.scrollY;
		});
	},
};
</script>
<style lang="scss">
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
	width: 100%;
	.article-feed-wrap {
		flex: 2;
		width: 66%;
		display: flex;
		flex-direction: column;
		margin-right: 100px;
		@media screen and (max-width: 992px) {
			margin-right: 0;
			width: 100%;
		}
		.tui-editor-contents {
			p {
				width: 100%;
				height: 100%;
				word-break: break-all;
				.colour {
					word-break: break-all;
				}
				img {
					width: 100%;
					height: 100%;
					object-fit: cover;
				}
			}
			pre {
				box-sizing: border-box;
				width: 100%;
				overflow-x: hidden;
				word-break: break-all;
			}
			code {
				width: 100%;
				height: 100%;
				word-break: break-all;
			}
		}
	}
	.rank-wrap {
		flex: 1;
		width: 33%;
		height: 100vh;
		@media screen and (max-width: 992px) {
			display: none;
		}
	}
}
</style>
