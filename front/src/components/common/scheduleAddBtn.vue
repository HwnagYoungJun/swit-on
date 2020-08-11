<template>
	<button class="add-btn" @click="addSchedule">일정 생성</button>
</template>

<script>
export default {
	methods: {
		addSchedule() {
			const studyId = this.$route.params.id;
			this.$router.push(`/study/${studyId}/schedule`);
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
