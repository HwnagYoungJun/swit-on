<template>
	<div>
		<ul class="study-box">
			<li :key="study.id" v-for="study in studies" class="study-li">
				<router-link :to="`/study/${study.id}`">
					<StudyCard :study="study" />
				</router-link>
			</li>
		</ul>
		<div class="noStudy" v-if="!isStudy">
			<img src="@/assets/kti_(var.doran).png" alt="bb" />
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import StudyCard from '@/components/common/StudyCard.vue';
import { lowerCategoryId } from '@/utils/category';
export default {
	components: {
		StudyCard,
	},
	data() {
		return {
			testData: null,
			baseURL: process.env.VUE_APP_API_URL,
			studies: [],
		};
	},
	props: {
		upperCategory: Number,
		lowerCategory: String,
	},
	watch: {
		lowerCategory() {
			this.studies = [];
			if (this.lowerCategory === '전체') {
				this.fetchUpperStudy();
			} else {
				this.fetchLowerStudy();
			}
		},
		$route() {
			this.studies = [];
			this.fetchUpperStudy();
		},
	},
	computed: {
		isStudy() {
			return this.studies.length === 0 ? false : true;
		},
	},
	methods: {
		async fetchUpperStudy() {
			const { data } = await axios.get(`${this.baseURL}study`, {
				params: {
					uppercategory_id: this.upperCategory,
				},
			});
			this.studies = data;
		},
		async fetchLowerStudy() {
			const { data } = await axios.get(`${this.baseURL}study`, {
				params: {
					lowercategory_id: lowerCategoryId(this.lowerCategory),
				},
			});
			this.studies = data;
		},
	},
};
</script>

<style lang="scss" scoped>
.noStudy {
	img {
		width: 100%;
	}
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
