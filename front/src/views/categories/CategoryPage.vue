<template>
	<section>
		<CategoryNavForm :upperCategoryName="upperCategoryName" />
		<LowerCategoryNavForm
			@changeLower="changeLower"
			:upperCategory="upperCategory"
			:upperCategoryName="upperCategoryName"
			:lowerCategoryName="lowerCategoryName"
		/>
		<router-view :upperCategoryName="upperCategoryName"></router-view>
	</section>
</template>

<script>
import CategoryNavForm from '@/components/categories/CategoryNavForm.vue';
import LowerCategoryNavForm from '@/components/categories/children/LowerCategoryNavForm.vue';
import { upperCategoryId } from '@/utils/category';
export default {
	components: {
		CategoryNavForm,
		LowerCategoryNavForm,
	},
	props: {
		upperCategoryName: String,
		lowerCategoryName: String,
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
	computed: {
		initName() {
			return this.upperCategoryName;
		},
		upperCategory() {
			return upperCategoryId(this.upperCategoryName);
		},
	},
};
</script>

<style lang="scss" scoped></style>
