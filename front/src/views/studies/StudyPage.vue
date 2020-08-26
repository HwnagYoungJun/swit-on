<template>
	<section>
		<CategoryNavForm :upperCategoryName="upperCategoryName" />
		<LowerCategoryNavForm
			@changeLower="changeLower"
			:upperCategory="upperCategory"
			:upperCategoryName="upperCategoryName"
			:lowerCategoryName="lowerCategoryName"
		/>
		<p class="searched-info" tabindex="0">
			"{{ name }}"에 대한 검색 결과입니다
		</p>

		<div v-if="searchedStudyDatas.length" class="search-only-study-wrap">
			<router-link
				:key="study.id"
				v-for="study in searchedStudyDatas"
				:to="`/study/${study.id}`"
				tabindex="-1"
			>
				<MainCard :study="study" colorPick="black" />
			</router-link>
		</div>
		<div v-else class="searched-not-found">
			<p>"{{ name }}" 스터디가 존재하지 않아요 :(</p>
		</div>
		<router-view :upperCategoryName="upperCategoryName"></router-view>
	</section>
</template>

<script>
import CategoryNavForm from '@/components/categories/CategoryNavForm.vue';
import LowerCategoryNavForm from '@/components/categories/children/LowerCategoryNavForm.vue';
import { upperCategoryId } from '@/utils/category';
import { searchOnlyStudy } from '@/api/studies.js';
import MainCard from '@/components/common/MainCard.vue';
import bus from '@/utils/bus.js';

export default {
	data() {
		return {
			lowerCategory: '전체',
			searchedStudyDatas: [],
		};
	},
	components: {
		CategoryNavForm,
		LowerCategoryNavForm,
		MainCard,
	},
	props: {
		upperCategoryName: String,
		lowerCategoryName: String,
		name: String,
	},
	methods: {
		changeLower(categoryName) {
			this.lowerCategory = categoryName;
		},
		resetLowerCategory() {
			this.lowerCategory = '전체';
		},
		async fetchSearchedOnlyStudy() {
			const name = this.name;
			try {
				if (!name) {
					this.searchedStudyDatas = [];
					return;
				}
				const { data } = await searchOnlyStudy(name);
				this.searchedStudyDatas = data;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	created() {
		this.fetchSearchedOnlyStudy();
	},
	watch: {
		$route: 'fetchSearchedOnlyStudy',
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
.searched-info {
	font-size: $font-light;
	color: $main-color;
}
.search-only-study-wrap {
	width: 100%;
	height: 100%;
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	grid-auto-rows: 20rem;
	grid-gap: 1rem;
	@media screen and (max-width: 1024px) {
		grid-template-columns: repeat(3, 1fr);
		grid-auto-rows: 19rem;
	}
	@media screen and (max-width: 768px) {
		grid-template-columns: repeat(2, 1fr);
		grid-auto-rows: 21rem;
	}
	@media screen and (max-width: 400px) {
		grid-template-columns: repeat(2, 1fr);
		grid-auto-rows: 18.5rem;
	}
}
.searched-not-found {
	width: 100%;
	height: 200px;
	display: grid;
	place-items: center;
	position: relative;
	p {
		color: rgb(100, 100, 100);
		font-weight: bold;
	}
}
</style>
