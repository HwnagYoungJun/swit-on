<template>
	<aside class="meeting-wrap">
		<section class="meeting">
			<span class="meeting-title">회의 목록</span>
			<ul>
				<router-link
					v-for="Room in Rooms"
					:key="Room.id"
					:to="`/study/${Id}/room/${Room.content}`"
				>
					<li>
						{{ Room.content }}
						<i
							v-if="isLeader"
							@click="deleteRoom(Id, Room.id)"
							class="icon ion-md-close unlike"
						>
						</i>
					</li>
				</router-link>
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
		align-items: center;
		margin: 10px 0;
		color: rgb(90, 90, 90);
	}
}
</style>
