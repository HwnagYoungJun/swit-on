<template>
	<div>
		<input type="radio" id="a" value="a" v-model="status" />
		<label for="a">전체</label>
		<input type="radio" id="qna" value="qna" v-model="status" />
		<label for="qna">QNA</label>
		<input type="radio" id="rep" value="rep" v-model="status" />
		<label for="rep">저장소</label>
		<ul>
			<li :key="article.id" v-for="article in WhatCheck">
				<!-- <router-link :to="`study`"> -->
				<ArticleCard :article="article" />
				<!-- </router-link> -->
			</li>
		</ul>
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import { fetchMyRepository, fetchMyQNA } from '@/api/auth';
export default {
	components: {
		ArticleCard,
	},
	props: {
		userName: String,
	},
	data() {
		return {
			status: 'a',
			qnaArticle: [],
			repositoryArticle: [],
		};
	},
	methods: {},
	computed: {
		WhatCheck() {
			switch (this.status) {
				case 'a':
					return this.allArticle;
				case 'qna':
					return this.qnaArticle;
				case 'rep':
					return this.repositoryArticle;
				default:
					return 0;
			}
		},
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		allArticle() {
			var tempArticle = this.repositoryArticle.concat(this.qnaArticle);
			tempArticle.sort((a, b) =>
				a.created_at > b.created_at ? -1 : a.created_at < b.created_at ? 1 : 0,
			);
			return tempArticle;
		},
	},
	created() {
		Promise.all([
			fetchMyQNA(this.userName),
			fetchMyRepository(this.userName),
		]).then(res => {
			res.forEach(el => {
				if (el.config.url === `accounts/${this.userName}/myqna`) {
					var tempQNA = el.data;
					this.qnaArticle = tempQNA;
				} else {
					var tempRepository = el.data;
					this.repositoryArticle = tempRepository;
				}
			});
		});
	},
};
</script>

<style></style>
