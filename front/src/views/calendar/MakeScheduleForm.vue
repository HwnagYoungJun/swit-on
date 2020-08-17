<template>
	<form class="scheduleform" @submit.prevent="submitSchedule">
		<header class="scheduleform-header">
			<h2>스터디 일정 생성</h2>
			<div class="scheduleform-btnbox">
				<button @click.prevent="$router.go(-1)" class="scheduleform-btn-cancle">
					취소
				</button>
				<button class="hide-btn">생성</button>
				<button class="scheduleform-btn-submit" type="submit">생성</button>
			</div>
		</header>
		<section class="scheduleform-main">
			<div class="schedule-margin">
				<label class="head-label" for="title">일정이름</label>
				<input
					class="scheduleform-input"
					id="title"
					type="text"
					v-model="title"
				/>
			</div>
			<div class="schedule-margin">
				<label class="head-label" for="startend">시간</label>
				<input type="time" v-model="startTime" /> 부터
				<input type="time" v-model="endTime" /> 까지
			</div>
			<div class="schedule-margin">
				<label class="head-label" for="date">날짜</label>
				<input id="date" type="date" v-model="date" />
			</div>
			<div class="selectColor">
				<label class="head-label" for="color">색상</label>
				<swatches class="swatchesPC" v-model="bgColor" inline />
				<swatches class="swatchesMobile" v-model="bgColor" />
			</div>
		</section>
	</form>
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
			dateStart.setHours(tempStart[0], tempStart[1]);
			let start = dateStart;
			dateEnd.setHours(tempEnd[0], tempEnd[1]);
			let end = dateEnd;
			start = start.toISOString();
			end = end.toISOString();
			this.scheduleObject = {
				bg_color: this.bgColor,
				start: start,
				end: end,
				study_id: this.study_id,
				title: this.title,
				user_id: this.userId,
			};
		},
	},
};
</script>

<style lang="scss">
.scheduleform {
	width: 100%;
	height: 100%;
}
.scheduleform-main {
	box-shadow: 0 2px 6px 0 rgba(68, 67, 68, 0.4);
	padding: 1rem;
	border-radius: 4px;
	.scheduleform-input {
		width: 100%;
		padding: 10px;
		border: none;
		border-radius: 0;
		border-bottom: 1px solid black;
		&:focus {
			outline: none;
			border-bottom: 1px solid black;
		}
	}
	.head-label {
		display: block;
		font-weight: 600;
	}
}
.scheduleform-btnbox {
	display: flex;
	align-items: center;
	position: relative;
}

.scheduleform-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1rem;
	.scheduleform-btn-cancle {
		@include form-btn('white');
		margin-right: 5px;
	}
	.scheduleform-btn-submit {
		@include form-btn('purple');
		position: relative;
		right: 0;
	}
	.hide-btn {
		border: none;
		border-radius: 3px;
		height: 40px;
		padding: 0 1.125rem;
		font-size: 1rem;
		font-weight: 700;
		text-decoration: none solid #000;
		background: #000;
		color: #fff;
		position: absolute;
		right: 0;
	}
}
.schedule-margin {
	margin-bottom: 2rem;
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
	.vue-swatches__swatch {
		margin-bottom: 0;
	}
	.color-bottom {
		// margin-bottom: 4px;
	}
}
</style>
