<template>
	<div>
		<div class="category-box">
			<div class="openCategory category-element">
				<router-link
					:to="{
						name: 'main',
					}"
					><img src="@/assets/black.png" alt="ham"
				/></router-link>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'main',
					}"
					>전체</router-link
				>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'categorydetail',
						params: { UpperCategoryName: categoryElements.el1 },
					}"
				>
					{{ categoryElements.el1 }}
				</router-link>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'categorydetail',
						params: { UpperCategoryName: categoryElements.el2 },
					}"
				>
					{{ categoryElements.el2 }}
				</router-link>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'categorydetail',
						params: { UpperCategoryName: categoryElements.el3 },
					}"
				>
					{{ categoryElements.el3 }}
				</router-link>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'categorydetail',
						params: { UpperCategoryName: categoryElements.el4 },
					}"
				>
					{{ categoryElements.el4 }}
				</router-link>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'categorydetail',
						params: { UpperCategoryName: categoryElements.el5 },
					}"
				>
					{{ categoryElements.el5 }}
				</router-link>
			</div>
			<div class="category-element">
				<router-link
					:to="{
						name: 'categorydetail',
						params: { UpperCategoryName: categoryElements.el6 },
					}"
				>
					{{ categoryElements.el6 }}
				</router-link>
			</div>
		</div>
		<hr />
	</div>
</template>

<script>
export default {
	data() {
		return {
			categoryElements: {
				el1: '웹',
				el2: '모바일',
				el3: 'AI',
				el4: 'CS',
				el5: '프로그래밍 언어',
				el6: '언어',
			},
		};
	},
};
</script>

<style lang="scss" scoped>
hr {
	width: 100vw;
	margin-left: calc(-50vw + 50%);
	border-top: 1px solid rgba(0, 0, 0, 0.1);
	border-bottom: 0px;
}
.category-box {
	display: flex;
	@media screen and (max-width: 768px) {
		overflow: auto;
		flex-wrap: wrap;
	}
	.openCategory {
		@media screen and (max-width: 768px) {
			display: none;
		}
	}
	.category-element {
		font-size: $font-normal;
		margin-right: 1.5rem;
		align-items: center;
		a {
			display: flex;
		}
		&:hover {
			font-weight: bold;
			cursor: pointer;
			letter-spacing: -0.1px;
		}
		img {
			width: 30px;
			height: 30px;
		}
	}
}
</style>
