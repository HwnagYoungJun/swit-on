<template>
	<button class="add-btn" @click="addArticle">새 글 작성</button>
</template>

<script>
export default {
	props: {
		boardName: String,
	},
	methods: {
		addArticle() {
			const studyId = this.$route.params.id;
			this.$router.push(`/study/${studyId}/${this.boardName}/new`);
		},
	},
};
</script>

<style lang="scss">
.add-btn {
	width: 100px;
	height: 30px;
	border: 1px solid $main-color;
	border-radius: 4px;
	color: #fff;
	background: $btn-purple;
	position: absolute;
	right: 0;
	top: -50px;
	&:focus {
		outline: none;
	}
	&:hover {
		color: $main-color;
		background: #fff;
	}
}
</style>
