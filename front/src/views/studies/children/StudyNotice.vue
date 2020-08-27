<template>
	<section v-if="loading">
		<Loading />
	</section>
	<section v-else class="card-wrap">
		<UpperBtn></UpperBtn>
		<ArticleAddBtn v-if="isLeader" boardName="notice" />
		<div v-if="!articles.length" class="article-wrap">
			<ArticleNotFound class="article-feed-wrap" />
			<aside class="member-wrap">
				<div class="study-members">
					<p class="study-members-title">우리 스터디 :></p>
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
							board_name: 'notice',
							article_id: article.id,
						},
					}"
				>
					<ArticleFeed :article="article"> </ArticleFeed>
				</router-link>
			</div>
			<aside class="member-wrap">
				<div class="study-members">
					<p class="study-members-title">우리 스터디 :></p>
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
		</article>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
import ArticleFeed from '@/components/common/ArticleFeed.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import { fetchArticles } from '@/api/articles';
import { fetchStudy } from '@/api/studies';
import Loading from '@/components/common/Loading.vue';
import UpperBtn from '@/components/common/UpperBtn.vue';
export default {
	props: {
		id: Number,
		isLeader: Boolean,
	},
	data() {
		return {
			loading: false,
			limit: 0,
			articles: [],
			windowTop: 0,
			members: null,
		};
	},
	components: {
		ArticleFeed,
		ArticleAddBtn,
		ArticleNotFound,
		Loading,
		UpperBtn,
	},

	methods: {
		async fetchNotice() {
			this.fetchData();
			try {
				const studyId = this.id;
				this.loading = true;
				const { data } = await fetchArticles(studyId, 'notice', 0);
				this.articles = data;
				this.loading = false;
				this.limit += 5;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async fetchData() {
			try {
				const studyId = this.id;
				const { data } = await fetchStudy(studyId);
				this.members = data.members;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async infiniteLoading() {
			try {
				const studyId = this.id;
				const { data } = await fetchArticles(studyId, 'notice', this.limit);
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
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
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
			this.fetchNotice();
			window.addEventListener('scroll', () => {
				this.windowTop = window.scrollY;
			});
		},
	},
	created() {
		this.fetchNotice();
		window.addEventListener('scroll', () => {
			this.windowTop = window.scrollY;
		});
	},
};
</script>
<style lang="scss">
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
		word-break: break-all;
		overflow-x: hidden;
	}
	code {
		width: 100%;
		height: 100%;
		word-break: break-all;
	}
}
.card-wrap {
	height: 100%;
	display: flex;
	flex-wrap: wrap;
	position: relative;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
.study-members-title {
	font-weight: bold;
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
	.member-wrap {
		flex: 1;
		@media screen and (max-width: 992px) {
			display: none;
		}
		.study-members {
			flex: 1;
			.diff-user {
				margin-bottom: 8px;
			}
			li {
				.member-box {
					display: flex;
					align-items: center;
					margin-bottom: 8px;
					.member-image {
						width: 30px;
						height: 30px;
						margin-right: 8px;
						border-radius: 50%;
					}
				}
			}
			@media screen and (max-width: 768px) {
				display: none;
			}
		}
	}
}
</style>
