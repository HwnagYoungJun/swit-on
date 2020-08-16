<template>
	<div>
		<CategoryNavForm :upperCategoryName="upperCategoryName" />
		<!-- <h1 @click="resetLowerCategory">{{ initName }}</h1> -->
		<LowerCategoryNavForm
			@changeLower="changeLower"
			:upperCategory="upperCategory"
			:upperCategoryName="upperCategoryName"
			:lowerCategoryName="lowerCategoryName"
		/>
		<router-view :upperCategoryName="upperCategoryName"></router-view>
	</div>
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

<style lang="scss" scoped>
h1 {
	display: inline;
}
</style>
