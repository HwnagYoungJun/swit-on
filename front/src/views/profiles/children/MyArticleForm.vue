<template>
	<div class="container">
		<div class="radio-box">
			<input type="radio" id="a" value="a" v-model="status" />
			<label for="a">전체</label>
			<input type="radio" id="qna" value="qna" v-model="status" />
			<label for="qna">QNA</label>
			<input type="radio" id="rep" value="rep" v-model="status" />
			<label for="rep">저장소</label>
		</div>
		<ul class="myArticle-box">
			<li :key="article.id" v-for="article in WhatCheck">
				<router-link
					:to="`/study/${article.study.id}/${article.boardName}/${article.id}/`"
				>
					<ArticleCard :article="article" />
				</router-link>
			</li>
		</ul>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
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
		if (this.userName !== this.$cookies.get('name')) {
			this.$router.push({ path: '/404/' });
		}
		Promise.all([fetchMyQNA(this.userName), fetchMyRepository(this.userName)])
			.then(res => {
				res.forEach(el => {
					if (el.config.url === `accounts/${this.userName}/myqna`) {
						var tempQNA = el.data;
						tempQNA.map(el => {
							el.boardName = 'qna';
						});
						tempQNA.sort((a, b) =>
							a.created_at > b.created_at
								? -1
								: a.created_at < b.created_at
								? 1
								: 0,
						);
						this.qnaArticle = tempQNA;
					} else {
						var tempRepository = el.data;
						tempRepository.map(el => {
							el.boardName = 'repository';
						});
						tempRepository.sort((a, b) =>
							a.created_at > b.created_at
								? -1
								: a.created_at < b.created_at
								? 1
								: 0,
						);
						this.repositoryArticle = tempRepository;
					}
				});
			})
			.catch(error => {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			});
	},
};
</script>

<style lang="scss" scoped>
.myArticle-box {
	display: grid;
	gap: 1.5rem;
	grid-template-columns: repeat(2, 1fr);
	grid-template-rows: 1fr;
	@media screen and (max-width: 1024px) {
		display: flex;
		justify-content: center;
		flex-wrap: wrap;
	}
}
.radio-box {
	margin-bottom: 1rem;
	input {
		margin-right: 0.5rem;
	}
	label {
		margin-right: 1rem;
	}
}
</style>
