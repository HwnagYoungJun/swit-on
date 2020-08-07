<template>
	<div>
		<ul class="study-box">
			<li :key="study.id" v-for="study in studies" class="study-li">
				<StudyCard :study="study" />
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
		lowerCategory: String,
	},
	watch: {
		lowerCategory() {
			this.studies = [];
			this.fetchStudy();
			console.log(this.lowerCategory);
		},
	},
	computed: {
		isStudy() {
			return this.studies.length === 0 ? false : true;
		},
	},
	methods: {
		async fetchStudy() {
			const { data } = await axios.get(`${this.baseURL}study`, {
				params: {
					lowercategory_id: this.lowerCategoryId(this.lowerCategory),
				},
			});
			console.log(this.lowerCategoryId(this.lowerCategory));
			this.studies = data;
		},
		lowerCategoryId(lowerCategoryString) {
			switch (lowerCategoryString) {
				case 'Django':
					return 1;
				case 'Spring':
					return 2;
				case 'Node.js':
					return 3;
				case 'Vue':
					return 4;
				case 'React':
					return 5;
				case 'html/CSS':
					return 6;
				case 'JS':
					return 7;
				case 'DB':
					return 8;
				case '기타':
					return 9;
				case '안드로이드':
					return 10;
				case 'IOS':
					return 11;
				case '플러터':
					return 12;
				case '리액트네이티브':
					return 13;
				case '파이썬':
					return 15;
			}
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
	justify-content: space-between;
	width: 100%;
	flex-wrap: wrap;
	.study-li {
		width: 20%;
		overflow: hidden;
	}
	@media screen and (max-width: 1024px) {
		.study-li {
			width: 30%;
			overflow: hidden;
		}
	}
	@media screen and (max-width: 640px) {
		.study-li {
			width: 45%;
			overflow: hidden;
		}
	}
}
</style>
