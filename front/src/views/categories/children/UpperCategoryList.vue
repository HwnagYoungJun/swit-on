<template>
	<div>
		<div class="noStudy" v-if="!studies">
			<img src="@/assets/kti_(var.doran).png" alt="bb" />
		</div>
		<!-- <ul v-else class="study-box">
			<li :key="study.id" v-for="study in studies" class="study-li">
				<StudyCard :study="study" />
			</li>
		</ul> -->
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
import axios from 'axios';
import MainCard from '@/components/common/MainCard.vue';
import { upperCategoryId } from '@/utils/category';
export default {
	components: {
		MainCard,
	},
	data() {
		return {
			studies: null,
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
			const { data } = await axios.get(`${this.baseURL}study`, {
				params: {
					uppercategory_id: this.categoryId,
				},
			});
			this.studies = data.length ? data : null;
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
