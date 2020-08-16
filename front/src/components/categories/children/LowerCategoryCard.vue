<template>
	<div @click="lowerCategory" class="category-box">
		<p class="categoryname">
			{{ fetchName }}
			<span :class="[isSelected ? 'category-seletecd' : '']"></span>
		</p>
	</div>
</template>

<script>
export default {
	data() {
		return {
			categoryName: this.$props.name,
		};
	},
	props: {
		name: String,
	},
	methods: {
		lowerCategory() {
			this.$emit('changeLower', this.categoryName);
		},
	},
	computed: {
		fetchName() {
			return `#${this.$props.name}`;
		},
		isSelected() {
			return this.name === this.$route.params.lowerCategoryName;
		},
	},
};
</script>

<style lang="scss" scoped>
.category-box {
	display: inline-block;
	margin-right: 1rem;
	border: 1px solid transparent;
	.category-seletecd {
		color: purple;
		width: 100%;
		height: 8px;
		position: absolute;
		bottom: -4px;
		left: 0;
		border-radius: 2px;
		background: $btn-purple;
		opacity: 0.5;
	}
	// .selected {
	// }
	:hover {
		cursor: pointer;
	}
	:hover span {
		color: purple;
		width: 100%;
		height: 8px;
		position: absolute;
		bottom: -4px;
		left: 0;
		border-radius: 2px;
		background: $btn-purple;
		opacity: 0.5;
	}

	.categoryname {
		position: relative;
	}
}
</style>
