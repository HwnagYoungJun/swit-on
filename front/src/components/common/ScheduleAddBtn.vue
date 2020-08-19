<template>
	<section>
		<button class="schedule-add-btn" @click="addSchedule">
			<i class="icon ion-md-add" aria-hidden="true"></i> 일정
		</button>
		<button class="schedule-add-btn-mobile" @click="addSchedule">
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
	@include common-btn();
	position: absolute;
	width: 100%;
	top: 0px;
	display: none;
}
@media screen and (max-width: 768px) {
	.schedule-add-btn {
		display: none;
	}
	.schedule-add-btn-mobile {
		display: inline;
	}
}
</style>
