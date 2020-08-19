<template>
	<section class="card-wrap">
		<article class="room-wrap">
			<form class="room-form-wrap" @submit.prevent="addRoom">
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
			<aside class="room-list-wrap">
				<RoomList
					@remove-room="fetchData"
					:Id="id"
					:isLeader="isLeader"
					:Rooms="Rooms"
				/>
			</aside>
		</article>
	</section>
</template>
<script>
import { v4 as uuidv4 } from 'uuid';
import bus from '@/utils/bus';
import RoomList from '@/components/common/RoomList.vue';
import { fetchRooms, createRoom } from '@/api/studies';
export default {
	props: {
		id: Number,
	},
	data() {
		return {
			room: uuidv4(),
			Rooms: [],
			isLeader: null,
		};
	},
	components: {
		RoomList,
	},
	methods: {
		async addRoom() {
			if (this.isLeader) {
				await createRoom(this.id, this.room);
			}
			this.$router.push(`/study/${this.id}/room/${this.room}`);
		},
		async fetchData() {
			try {
				const Id = this.id;
				const { data } = await fetchRooms(Id);
				this.Rooms = data.conferences;
				this.isLeader = data.leader;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	created() {
		this.fetchData();
	},
	watch: {
		$route: 'fetchData',
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
.card-wrap {
	height: 100%;
	display: flex;
	flex-wrap: wrap;
	position: relative;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
.room-wrap {
	display: flex;
	flex-wrap: wrap;
	width: 100%;
	height: 100%;
	.room-form-wrap {
		flex: 2;
		display: flex;
		flex-direction: column;
		margin-right: 100px;
		@media screen and (max-width: 992px) {
			margin-right: 0;
		}
	}
	.room-list-wrap {
		flex: 1;
		height: 100%;
		@media screen and (max-width: 992px) {
			display: none;
		}
	}
}
</style>
