<template>
	<div>
		<label for="title">일정이름</label>
		<input name="title" type="text" v-model="title" />
		<hr />
		<label for="startend">시작시간 끝시간</label>
		<input name="startend" type="time" v-model="startTime" />
		<input name="startend" type="time" v-model="endTime" />
		<hr />
		<label for="date">날짜</label>
		<input name="date" type="date" v-model="date" />
		<hr />
		<div class="selectColor">
			<swatches class="swatchesPC" v-model="bgColor" inline />
			<swatches class="swatchesMobile" v-model="bgColor" />
		</div>
		<h1 @click="makeSchedule">test</h1>
		{{ startTime }}, {{ endTime }}
	</div>
</template>

<script>
import Swatches from 'vue-swatches';
export default {
	components: {
		Swatches,
	},
	data() {
		return {
			bgColor: '#000000',
			scheduleArray: [],
			title: null,
			date: null,
			startTime: null,
			endTime: null,
			studyId: 1,
		};
	},
	watch: {},
	methods: {
		async makeSchedule() {
			await this.pushSchedule();
		},
		pushSchedule() {
			var tempDate = this.date.split('-').map(Number);
			let date = new Date(tempDate[0], tempDate[1], tempDate[2]);
			let dateStart = date;
			let dateEnd = date;
			var tempStart = this.startTime.split(':').map(Number);
			var tempEnd = this.endTime.split(':').map(el => parseInt(el));
			dateStart.setHours(tempStart[0], tempStart[1]);
			dateEnd.setHours(tempEnd[0], tempEnd[1]);
			let end = dateEnd;
			let start = dateStart;
			start = start.toISOString();
			end = end.toISOString();
			this.scheduleArray.push({
				calendarId: this.studyId,
				title: this.title,
				category: 'time',
				start: start,
				end: end,
				color: this.bgColor === '#dde6e8' ? '#000000' : '#ffffff',
				bgColor: this.bgColor,
				leader: '',
				participants: [],
			});
		},
	},
};
</script>

<style lang="scss">
input {
	border: 1px solid black;
}
.selectColor {
	.swatchesPC {
		@media screen and (max-width: 480px) {
			display: none;
		}
	}
	.swatchesMobile {
		display: none;
		@media screen and (max-width: 480px) {
			display: inline;
		}
	}
	.vue-swatches__wrapper {
		display: flex;
		flex-wrap: wrap;
		width: 100% !important;
	}
}
</style>
