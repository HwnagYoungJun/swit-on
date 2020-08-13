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
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import { fetchMyFav } from '@/api/auth';
export default {
	components: {
		ArticleCard,
	},
	props: {
		userName: String,
	},

	data() {
		return {
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
