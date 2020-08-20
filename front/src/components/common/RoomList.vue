<template>
	<aside class="meeting-wrap">
		<section class="meeting">
			<span class="meeting-title">회의 목록</span>
			<ul>
				<li class="meeting-list" :key="Room.id" v-for="(Room, index) in Rooms">
					<router-link :to="`/study/${Room.studyId}/room/${Room.roomCode}`">
						회의 {{ index + 1 }}
					</router-link>
					<i
						v-if="isLeader"
						@click="removeRoom(Id, Room.id)"
						class="icon ion-md-close unlike"
						aria-label="회의 끝내기 버튼"
					>
					</i>
				</li>
			</ul>
		</section>
	</aside>
</template>

<script>
import { deleteRoom } from '@/api/studies';

export default {
	props: {
		Id: Number,
		Rooms: Array,
		isLeader: Boolean,
	},
	computed: {},
	methods: {
		trunk(str) {
			if (str.length > 11) {
				return str.substr(0, 11) + '...';
			}
			return str;
		},
		async removeRoom(id, roomId) {
			await deleteRoom(id, roomId);
			this.$emit('remove-room');
		},
	},
};
</script>

<style lang="scss" scoped>
.meeting {
	position: relative;
	margin: 10px 0 30px;
	padding: 20px 0;
	color: rgb(138, 138, 138);
	.meeting-title {
		position: absolute;
		top: -5px;
		left: -15px;
		color: rgb(90, 90, 90);
		font-weight: bold;
		background: #fff;
	}
	li {
		display: flex;
		word-break: break-all;
		align-items: center;
		margin: 10px 0;
		color: rgb(90, 90, 90);
		a {
			margin-bottom: 10px;
		}
	}
	i {
		cursor: pointer;
	}
}
</style>
