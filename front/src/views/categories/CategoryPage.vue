<template>
	<section>
		<CategoryNavForm :upperCategoryName="upperCategoryName" />
		<LowerCategoryNavForm
			v-if="upperCategoryName !== '추천'"
			@changeLower="changeLower"
			:upperCategory="upperCategory"
			:upperCategoryName="upperCategoryName"
			:lowerCategoryName="lowerCategoryName"
		/>
		<h1 v-else class="suggest-title">추천 페이지</h1>
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
	updated() {
		document.title = `스윗온 ${this.upperCategoryName} 카테고리`;
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
.suggest-title {
	font-size: $font-bold;
	margin: 0.6rem 0 1.5rem;
}
</style>
