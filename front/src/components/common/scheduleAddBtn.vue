<template>
	<button class="add-btn" @click="addSchedule">+ 일정</button>
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
	@include common-btn();
	position: absolute;
	width: 70px;
	right: 0;
	top: -60px;
}
</style>
