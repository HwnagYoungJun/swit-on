<template>
	<div>
		<h1>{{ categoryName }}</h1>
		<h2>전체 스터디</h2>
		<hr />
		<CategoryForm :categoryName="categoryName"></CategoryForm>
	</div>
</template>

<script>
import { fetchStudies } from '@/api/studies';
import CategoryForm from '@/components/categorys/CategoryForm.vue';
export default {
	components: {
		CategoryForm,
	},
	data() {
		return {
			studyItems: [],
			categoryName: null,
		};
	},
	methods: {
		async fetchData() {
			const { data } = await fetchStudies();
			this.studyItems = data.studies;
		},
	},
	created() {
		this.categoryName = this.$route.params.UpperCategoryName;
	},
};
</script>

<style></style>
