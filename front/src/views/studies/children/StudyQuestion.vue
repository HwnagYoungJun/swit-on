<template>
	<section v-if="loading">
		<Loading />
	</section>
	<section v-else class="card-wrap">
		<UpperBtn></UpperBtn>
		<ArticleAddBtn boardName="qna" />
		<div v-if="!articles.length" class="article-wrap">
			<ArticleNotFound class="article-feed-wrap" />
			<aside class="member-wrap">
				<div class="study-members">
					<p>우리 스터디 :></p>
					<ul>
						<li v-for="member in members" :key="member.id">
							<router-link class="member-box" :to="`/profile/${member.name}`">
								<img
									v-if="member.profile_image"
									:src="`${baseURL}${member.profile_image}`"
									:alt="`${member.name}의 프로필 사진`"
									class="member-image"
								/>
								<img
									v-else
									:src="`${baseURL}upload/noProfile.png`"
									:alt="`${member.name}의 프로필 대체 사진`"
									class="member-image"
								/>
								{{ member.name }}
							</router-link>
						</li>
					</ul>
				</div>
			</aside>
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
							board_name: 'qna',
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
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import ArticleRank from '@/components/common/ArticleRank.vue';
import { fetchArticles } from '@/api/articles';
import { bestMember, bestArticle } from '@/api/studies';
import Loading from '@/components/common/Loading.vue';
import UpperBtn from '@/components/common/UpperBtn.vue';
// The checker

export default {
	props: {
		id: Number,
	},
	data() {
		return {
			loading: false,
			limit: 0,
			articles: [],
			windowTop: 0,
			bestMember: ['1등이없어요', '2등이없어요', '3등이없어요'],
			bestArticle: ['1등이없어요', '2등이없어요', '3등이없어요'],
		};
	},
	components: {
		ArticleRank,
		ArticleFeed,
		ArticleAddBtn,
		ArticleNotFound,
		Loading,
		UpperBtn,
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
		$route() {
			this.fetchQna();
			this.fetchBest();
			window.addEventListener('scroll', () => {
				this.windowTop = window.scrollY;
			});
		},
	},
	methods: {
		async fetchBest() {
			try {
				const studyId = this.id;
				const { data } = await bestMember(studyId);
				const res = await bestArticle(studyId);
				this.bestMember = data.rankers;
				this.bestArticle = res.data.bestQnas;
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
		async fetchQna() {
			try {
				const studyId = this.id;
				this.loading = true;
				const { data } = await fetchArticles(studyId, 'qna', 0);
				this.articles = data;
				this.loading = false;
				this.limit += 5;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},

		async infiniteLoading() {
			try {
				const studyId = this.id;
				const { data } = await fetchArticles(studyId, 'qna', this.limit);
				this.limit += 5;
				if (data.length) {
					this.articles = [...this.articles, ...data];
				}
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	created() {
		this.fetchQna();
		this.fetchBest();
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
	width: 100%;
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
		height: 100vh;
		@media screen and (max-width: 992px) {
			display: none;
		}
	}
}
</style>
