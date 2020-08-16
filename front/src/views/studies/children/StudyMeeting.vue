<template>
	<form class="meetingform" @submit.prevent="createRoom">
		<section class="meeting-header">
			<h2>Meeting</h2>
			<div class="meeting-btnbox">
				<button @click.prevent="$router.go(-1)" class="meeting-btn-cancle">
					취소
				</button>
				<button class="hide-btn">참가</button>
				<button class="meeting-btn-submit" type="submit">참가</button>
			</div>
		</section>
		<section class="meeting-main">
			<input type="text" class="meeting-input" v-model="room" />
		</section>
	</form>
</template>
<script>
import { v4 as uuidv4 } from 'uuid';

export default {
	props: {
		id: Number,
	},
	data() {
		return {
			room: uuidv4(),
		};
	},
	methods: {
		createRoom() {
			this.$router.push(`/study/${this.id}/room/${this.room}`);
		},
	},
};
</script>
<style lang="scss">
.meetingform {
	width: 100%;
	height: 100%;
}
.meeting-main {
	padding: 1rem;
	border-radius: 4px;
	.meeting-input {
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
}
.meeting-btnbox {
	display: flex;
	align-items: center;
	position: relative;
}

.meeting-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1rem;
	.meeting-btn-cancle {
		@include form-btn('white');
		margin-right: 5px;
	}
	.meeting-btn-submit {
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
</style>
