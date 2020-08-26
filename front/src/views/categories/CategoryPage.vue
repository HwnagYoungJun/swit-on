<template>
	<section>
		<CategoryNavForm :upperCategoryName="upperCategoryName" />
		<LowerCategoryNavForm
			v-if="upperCategoryName !== '인기'"
			@changeLower="changeLower"
			:upperCategory="upperCategory"
			:upperCategoryName="upperCategoryName"
			:lowerCategoryName="lowerCategoryName"
		/>
		<h1 v-else class="suggest-title" tabindex="0">
			<p>{{ getName }}님,</p>
			<p class="suggest-title-comment">
				최근 인기 소모임 입니다:)
			</p>
		</h1>
		<router-view :upperCategoryName="upperCategoryName"></router-view>
	</section>
</template>

<script>
import CategoryNavForm from '@/components/categories/CategoryNavForm.vue';
import LowerCategoryNavForm from '@/components/categories/children/LowerCategoryNavForm.vue';
import { upperCategoryId } from '@/utils/category';
import { mapGetters } from 'vuex';

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
		...mapGetters(['getName']),
	},
};
</script>

<style lang="scss" scoped>
.suggest-title {
	margin: 2.5rem 0 0;
	color: $main-color;
	font-weight: bold;
	font-size: $font-light * 1.2;
	.suggest-title-comment {
		font-weight: normal;
	}
}
</style>
