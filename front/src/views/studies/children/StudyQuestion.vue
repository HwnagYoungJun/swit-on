<template>
	<div class="test">
		<div class="card-wrap">
			<div v-if="articles === []">
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
			<ArticleAddBtn boardName="qna" />
		</div>
		<p>dd</p>
		<!-- <div> -->
		<infinite-loading
			@infinite="infiniteHandler"
			spinner="waveDots"
		></infinite-loading>
		<!-- </div> -->

		<!-- <p>dd</p> -->
		<!-- {{ articles }} -->
		<!-- <p>{{ $state }}</p> -->
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
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
			articles: [],
		};
	},
	components: {
		ArticleCard,
		ArticleAddBtn,
		ArticleNotFound,
		InfiniteLoading,
	},
	methods: {
		async fetchQna() {
			const studyId = this.id;
			const { data } = await fetchArticles(studyId, 'qna', this.limit);
			this.articles = data;
			this.limit += 5;
		},
		infiniteHandler() {},
	},
};
</script>
<style lang="scss">
.test {
	height: 100%;
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
</style>
