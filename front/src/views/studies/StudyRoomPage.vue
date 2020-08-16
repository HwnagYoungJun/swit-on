<template>
	<section class="main">
		<div class="main__left">
			<article class="main__videos">
				<WebRTC
					ref="webrtc"
					width="100%"
					:roomId="room"
					:messages="messages"
					v-on:joined-room="logEvent"
					v-on:left-room="logEvent"
					v-on:opened-room="logEvent"
					v-on:share-started="logEvent"
					v-on:share-stopped="logEvent"
					v-on:sent-message="makeMessage"
					v-on:received-message="logEvent"
					v-on:clear-message="clearMessage"
					@error="onError"
				/>
			</article>
			<div class="main__controls">
				<div class="main__controls__block">
					<div
						@click="onJoin"
						class="main__controls__button main__video_button"
					>
						<span>참여</span>
					</div>
					<div @click="onShareScreen" class="main__controls__button">
						<span>화면공유</span>
					</div>
				</div>
				<div class="main__controls__block">
					<div @click="onLeave" class="main__controls__button">
						<span class="leave_meeting">떠나기</span>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import WebRTC from '@/components/common/WebRTC.vue';
export default {
	props: {
		id: Number,
		room: String,
	},
	components: { WebRTC },
	data() {
		return {
			img: null,
			roomId: this.room,
			messages: [],
		};
	},
	methods: {
		onJoin() {
			this.$refs.webrtc.join();
		},
		onLeave() {
			this.$refs.webrtc.leave();
			this.$router.push(`/study/${this.id}`);
		},
		onShareScreen() {
			this.img = this.$refs.webrtc.shareScreen();
		},
		onError(error, stream) {
			console.log('On Error Event', error, stream);
		},
		logEvent(event) {
			console.log('Event : ', event);
		},
		makeMessage(message) {
			this.messages.push({ message });
		},
		clearMessage() {
			this.messages = [];
		},
	},
	beforeDestroy() {
		this.$refs.webrtc.leave();
	},
};
</script>
<style lang="scss">
.main {
	width: 100%;
	height: 80vh;
	display: flex;
}

.main__left {
	box-shadow: 0 2px 6px 0 rgba(68, 67, 68, 0.4);
	flex: 1;
	display: flex;
	flex-direction: column;
}

.main__videos {
	border-top-left-radius: 4px;
	border-top-right-radius: 4px;
	flex: 1;
	background-color: black;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;
}

.main__controls {
	background-color: #1c1e20;
}

.main__controls {
	border-bottom-left-radius: 4px;
	border-bottom-right-radius: 4px;
	color: #d2d2d2;
	display: flex;
	justify-content: space-between;
	padding: 5px;
}

.main__controls__block {
	display: flex;
}

.main__controls__button {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding: 8px 10px;
	min-width: 80px;
	font-weight: 600;
	border: 2px solid #1c1e20;
	border-radius: 4px;
	cursor: pointer;
	&:hover {
		border: 2px solid rgb(229, 229, 229);
		color: black;
		background: white;
		border-color: black;
	}
}

.main__controls__button i {
	font-size: 24px;
}

.main__right {
	display: flex;
	flex-direction: column;
}

.leave_meeting {
	color: #eb534b;
}

.unmute,
.stop {
	color: #cc3b33;
}
.video-list {
	background: none !important;
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(160px, 1fr));
	grid-template-rows: 1fr;
	grid-auto-rows: 1fr;
}

video {
	float: left;
}
/* li가 하나일 때 */
video:first-child:nth-last-child(1) {
	width: 100%;
	height: 100%;
} /* li가 두개일 때 */
video:first-child:nth-last-child(2),
video:first-child:nth-last-child(2) ~ video {
	width: 50%;
	height: 50%;
} /* li가 세개일 때 */
video:first-child:nth-last-child(3),
video:first-child:nth-last-child(3) ~ video {
	width: 33.3333%;
	height: 33.3333%;
} /* li가 네개일 때*/
video:first-child:nth-last-child(4),
video:first-child:nth-last-child(4) ~ video {
	height: 50%;
}
video:after {
	content: ' ';
	display: block;
	clear: both;
}
</style>
