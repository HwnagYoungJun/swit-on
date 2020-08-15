<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else>
		<div class="noStudy" v-if="!studies">
			<img src="@/assets/kti_(var.doran).png" alt="bb" />
		</div>
		<div v-else class="popular-wrap">
			<router-link
				:key="study.id"
				v-for="study in studies"
				:to="`/study/${study.id}`"
			>
				<MainCard :study="study" colorPick="black" />
			</router-link>
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import axios from 'axios';
import MainCard from '@/components/common/MainCard.vue';
import { lowerCategoryId } from '@/utils/category';
import Loading from '@/components/common/Loading.vue';

export default {
	components: {
		MainCard,
		Loading,
	},
	data() {
		return {
			studies: null,
			loading: false,
		};
	},
	props: {
		upperCategoryName: String,
		lowerCategoryName: String,
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		categoryId() {
			return lowerCategoryId(this.lowerCategoryName);
		},
	},
	methods: {
		async fetchLowerStudy() {
			try {
				this.loading = true;
				const { data } = await axios.get(`${this.baseURL}study`, {
					params: {
						lowercategory_id: this.categoryId,
					},
				});
				this.loading = false;
				this.studies = data.length ? data : null;
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
	},
	watch: {
		$route: 'fetchLowerStudy',
	},
	created() {
		this.fetchLowerStudy();
	},
};
</script>

<style lang="scss" scoped>
.noStudy {
	img {
		width: 100%;
	}
}
.popular-wrap {
	width: 80%;
	margin: 0 auto;
	display: flex;
	justify-content: space-evenly;
	flex-wrap: wrap;
}
.study-box {
	display: flex;
	justify-content: flex-start;
	width: 100%;
	flex-wrap: wrap;
	.study-li {
		width: 20%;
		margin: 0 2.5%;
		overflow: hidden;
	}
	@media screen and (max-width: 1024px) {
		.study-li {
			width: 30%;
			margin: 0 1.65%;
			overflow: hidden;
		}
	}
	@media screen and (max-width: 640px) {
		.study-li {
			width: 45%;
			justify-content: space-between;
			overflow: hidden;
		}
	}
}
</style>
