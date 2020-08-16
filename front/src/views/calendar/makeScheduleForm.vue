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
		<button @click="submitSchedule">test</button>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import { baseAuth } from '@/api/index';
import Swatches from 'vue-swatches';
import cookies from 'vue-cookies';
export default {
	props: {
		study_id: Number,
	},
	components: {
		Swatches,
	},
	data() {
		return {
			bgColor: '#000000',
			scheduleObject: null,
			title: null,
			date: null,
			startTime: null,
			endTime: null,
			userId: null,
		};
	},
	watch: {},
	methods: {
		async submitSchedule() {
			await this.makeSchedule();
			const el = this.scheduleObject;
			try {
				await baseAuth.post(`study/${this.study_id}/schedule/`, el);
				this.$router.push(`/study/${this.study_id}`);
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},

		async makeSchedule() {
			try {
				const { data } = await baseAuth(`accounts/${cookies.get('name')}`);
				// console.log(data);
				this.userId = data.id;
				this.pushSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		pushSchedule() {
			var tempDate = this.date.split('-').map(Number);
			let dateS = new Date(tempDate[0], tempDate[1] - 1, tempDate[2]);
			let dateE = new Date(tempDate[0], tempDate[1] - 1, tempDate[2]);
			let dateStart = dateS;
			let dateEnd = dateE;
			var tempStart = this.startTime.split(':').map(Number);
			var tempEnd = this.endTime.split(':').map(el => parseInt(el));
			// console.log(tempStart, tempEnd);
			dateStart.setHours(tempStart[0], tempStart[1]);
			let start = dateStart;
			dateEnd.setHours(tempEnd[0], tempEnd[1]);
			let end = dateEnd;
			// console.log(start, end);
			start = start.toISOString();
			end = end.toISOString();
			this.scheduleObject = {
				bg_color: this.bgColor,
				start: start,
				end: end,
				study_id: this.study_id,
				title: this.title,
				// color: this.bgColor === '#dde6e8' ? '#000000' : '#ffffff',
				// leader: '',
				user_id: this.userId,
			};
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
