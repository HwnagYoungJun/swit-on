<template>
	<div>
		<CategoryNavForm />
		<br />
		<h1 @click="resetLowerCategory">{{ initName }}</h1>
		<br />
		<LowerCategoryNavForm
			@changeLower="changeLower"
			:upperCategory="upperCategory"
		/>
		<br />
		<h2>{{ lowerCategory }} 스터디</h2>
		<hr />
		<CategoryForm
			:lowerCategory="lowerCategory"
			:upperCategory="upperCategory"
		/>
	</div>
</template>

<script>
import CategoryForm from '@/components/categorys/CategoryForm.vue';
import CategoryNavForm from '@/components/categorys/CategoryNavForm.vue';
import LowerCategoryNavForm from '@/components/categorys/children/LowerCategoryNavForm.vue';
import { upperCategoryId } from '@/utils/category';
export default {
	components: {
		CategoryForm,
		CategoryNavForm,
		LowerCategoryNavForm,
	},
	data() {
		return {
			lowerCategory: '전체',
		};
	},
	methods: {
		changeLower(categoryName) {
			this.lowerCategory = categoryName;
		},
		resetLowerCategory() {
			this.lowerCategory = '전체';
		},
	},
	watch: {
		$route() {
			this.lowerCategory = '전체';
		},
	},
	computed: {
		initName() {
			return this.$route.params.UpperCategoryName;
		},
		upperCategory() {
			return upperCategoryId(this.$route.params.UpperCategoryName);
		},
	},
};
</script>

<style lang="scss" scoped>
h1 {
	display: inline;
}
</style>
