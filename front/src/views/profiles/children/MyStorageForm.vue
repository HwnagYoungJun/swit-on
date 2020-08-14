<template>
	<div>
		<ul>
			<li :key="article.id" v-for="article in ariticles">
				<router-link
					:to="`/study/${article.study.id}/repository/${article.id}`"
				>
					<ArticleCard :article="article" />
				</router-link>
			</li>
		</ul>
		<!-- <InfiniteLoading
			@infinite="infiniteHandler"
			spinner="waveDots"
		></InfiniteLoading> -->
	</div>
</template>

<script>
// import InfiniteLoading from 'vue-infinite-loading';
import ArticleCard from '@/components/common/ArticleCard.vue';
import { fetchMyFav } from '@/api/auth';
export default {
	components: {
		ArticleCard,
		// InfiniteLoading,
	},
	props: {
		userName: String,
	},

	data() {
		return {
			limit: 0,
			ariticles: [],
		};
	},
	methods: {
		async fetchData() {
			const { data } = await fetchMyFav(this.userName);
			console.log(data);
			this.ariticles = data;
		},
	},
	created() {
		this.fetchData();
	},
};
</script>

<style></style>
