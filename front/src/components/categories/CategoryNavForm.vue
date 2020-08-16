<template>
	<div>
		<div class="category-box">
			<!-- <div class="category-element">
				<router-link
					:to="{
						name: 'main',
					}"
					>전체</router-link
				>
			</div> -->
			<div :key="categoryElement" v-for="categoryElement in categoryElements">
				<div class="category-element">
					<router-link :to="`/category/${categoryElement}`">
						{{ categoryElement }}
					</router-link>
				</div>
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
				el5: '프로그래밍언어',
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
