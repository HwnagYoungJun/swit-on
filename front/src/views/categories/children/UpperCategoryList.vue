<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else>
		<div v-if="!studies.length">
			<StudyNotFound />
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
import { upperCategoryId } from '@/utils/category';
import Loading from '@/components/common/Loading.vue';
import StudyNotFound from '@/components/common/StudyNotFound.vue';

export default {
	components: {
		MainCard,
		Loading,
		StudyNotFound,
	},
	data() {
		return {
			studies: [],
			loading: false,
		};
	},
	props: {
		upperCategoryName: String,
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		categoryId() {
			return upperCategoryId(this.upperCategoryName);
		},
	},
	methods: {
		async fetchUpperStudy() {
			try {
				this.loading = true;
				const { data } = await axios.get(`${this.baseURL}study`, {
					params: {
						uppercategory_id: this.categoryId,
					},
				});
				this.studies = data;
				this.loading = false;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	watch: {
		$route: 'fetchUpperStudy',
	},
	created() {
		this.fetchUpperStudy();
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
