<template>
	<div>
		<CategoryNavForm />
		<br />
		<h1 @click="resetLowerCategory">{{ initName }}</h1>
		<br />
		<LowerCategoryNavForm
			@changeLower="changeLower"
			:upperCategory="upperCategoryId"
		/>
		<br />
		<h2>{{ lowerCategory }} 스터디</h2>
		<hr />
		<CategoryForm :lowerCategory="lowerCategory" />
	</div>
</template>

<script>
import { fetchStudies } from '@/api/studies';
import CategoryForm from '@/components/categorys/CategoryForm.vue';
import CategoryNavForm from '@/components/categorys/CategoryNavForm.vue';
import LowerCategoryNavForm from '@/components/categorys/children/LowerCategoryNavForm.vue';
export default {
	components: {
		CategoryForm,
		CategoryNavForm,
		LowerCategoryNavForm,
	},
	data() {
		return {
			studyItems: [],
			lowerCategory: '전체',
		};
	},
	methods: {
		async fetchData() {
			const { data } = await fetchStudies();
			this.studyItems = data.studies;
		},
		changeLower(categoryName) {
			this.lowerCategory = categoryName;
		},
		resetLowerCategory() {
			this.lowerCategory = '전체';
		},
	},
	computed: {
		initName() {
			return this.$route.params.UpperCategoryName;
		},
		upperCategoryId() {
			var categoryId = 0;
			var categoryArray = [
				'웹',
				'모바일',
				'프로그래밍언어',
				'AI',
				'CS',
				'언어',
			];
			switch (this.initName) {
				case categoryArray[0]:
					categoryId = 1;
					break;
				case categoryArray[1]:
					categoryId = 2;
					break;
				case categoryArray[2]:
					categoryId = 3;
					break;
				case categoryArray[3]:
					categoryId = 4;
					break;
				case categoryArray[4]:
					categoryId = 5;
					break;
				case categoryArray[5]:
					categoryId = 6;
					break;
			}
			return categoryId;
		},
	},
};
</script>

<style lang="scss" scoped>
h1 {
	display: inline;
}
</style>
