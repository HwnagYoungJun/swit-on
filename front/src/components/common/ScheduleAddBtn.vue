<template>
	<section>
		<button class="schedule-add-btn" @click="addSchedule">+ 일정</button>
		<button
			class="schedule-add-btn schedule-add-btn-mobile"
			@click="addSchedule"
		>
			일정추가
		</button>
	</section>
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
.schedule-add-btn {
	@include common-btn();
	position: absolute;
	width: 70px;
	right: 0;
	top: -55px;
}
.schedule-add-btn-mobile {
	display: none;
}
@media screen and (max-width: 768px) {
	.schedule-add-btn {
		position: static;
		width: 94%;
	}
	.schedule-add-btn-mobile {
		display: inline;
	}
}
</style>
