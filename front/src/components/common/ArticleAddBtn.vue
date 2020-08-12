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
<<<<<<< HEAD
	top: -60px;
=======
	top: -50px;
	&:hover {
		color: $main-color;
		background: #fff;
	}
>>>>>>> 66165aea8a824b13a4772300b297e956b4816b41
}
</style>
