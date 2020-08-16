<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else class="card-wrap">
		<ArticleAddBtn boardName="qna" />
		<div v-if="!articles.length">
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
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import ArticleCard from '@/components/common/ArticleCard.vue';
import ArticleAddBtn from '@/components/common/ArticleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import { fetchArticles } from '@/api/articles';
import Loading from '@/components/common/Loading.vue';
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
		};
	},
	components: {
		ArticleCard,
		ArticleAddBtn,
		ArticleNotFound,
		Loading,
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
	methods: {
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
</style>
