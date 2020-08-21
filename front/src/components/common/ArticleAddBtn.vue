<template>
	<button class="add-btn" @click="addArticle">글작성</button>
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
	@include common-btn();
	position: absolute;
	width: 70px;
	right: 0;
	top: -56px;
	@media screen and (max-width: 768px) {
		display: none;
	}
}
</style>
