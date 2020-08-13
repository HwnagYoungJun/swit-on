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
					board_name: 'notice',
					article_id: article.id,
				},
			}"
		>
			<ArticleCard :article="article">
				<div slot="logo">
					<img src="@/assets/dd.png" alt="" />
				</div>
			</ArticleCard>
		</router-link>
		<ArticleAddBtn v-if="isLeader" boardName="notice" />
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
		isLeader: Boolean,
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
		async fetchNotice() {
			const studyId = this.id;
			const { data } = await fetchArticles(studyId, 'notice');
			this.articles = data.length ? data : null;
		},
	},
	created() {
		this.fetchNotice();
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
