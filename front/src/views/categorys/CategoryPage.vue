<template>
	<div>
		<CategoryNavForm></CategoryNavForm>
		<h1>{{ initName }}</h1>
		<h2>전체 스터디</h2>
		<hr />
		<CategoryForm :categoryName="categoryName"></CategoryForm>
	</div>
</template>

<script>
import { fetchStudies } from '@/api/studies';
import CategoryForm from '@/components/categorys/CategoryForm.vue';
import CategoryNavForm from '@/components/categorys/CategoryNavForm.vue';
export default {
	components: {
		CategoryForm,
		CategoryNavForm,
	},
	data() {
		return {
			studyItems: [],
		};
	},
	methods: {
		async fetchData() {
			const { data } = await fetchStudies();
			this.studyItems = data.studies;
		},
	},
	computed: {
		initName() {
			return this.$route.params.UpperCategoryName;
		},
	},
};
</script>

<style></style>
