<template>
	<section>
		<!-- <h1>메인페이지</h1> -->
		<nav>
			<CategoryNavForm></CategoryNavForm>
		</nav>
		<section class="study-section">
			<h2>인기 스터디</h2>
			<CategoryForm :categoryName="categoryName"></CategoryForm>
		</section>
	</section>
</template>

<script>
import CategoryNavForm from '@/components/categorys/CategoryNavForm.vue';
import CategoryForm from '@/components/categorys/CategoryForm.vue';

export default {
	components: {
		CategoryNavForm,
		CategoryForm,
	},
};
</script>

<style lang="scss" scoped>
.study-section {
	h2 {
		margin: 1rem 0;
		padding: 10px;
		border-bottom: 2px solid purple;
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
