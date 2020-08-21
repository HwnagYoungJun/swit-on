<template>
	<section>
		<ul class="myStorage-box">
			<li v-if="ariticles.length === 0">
				<section class="study-not-found">
					<p>저장소가 없어요 :(</p>
				</section>
			</li>
			<li v-else :key="article.id" v-for="article in ariticles">
				<router-link
					:to="`/study/${article.study.id}/repository/${article.id}`"
				>
					<ArticleCard :article="article" />
				</router-link>
			</li>
		</ul>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
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
			try {
				const { data } = await fetchMyFav(this.userName);
				this.ariticles = data;
			} catch (error) {
				bus.$emit('show:toast', error.response.data.msg);
			}
		},
	},
	created() {
		if (this.userName !== this.$cookies.get('name')) {
			this.$router.push({ path: '/404/' });
		}
		this.fetchData();
	},
};
</script>

<style lang="scss" scoped>
.myStorage-box {
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
.study-not-found {
	width: 100%;
	height: 3rem;
	p {
		color: rgb(100, 100, 100);
		font-weight: bold;
	}
}
</style>
