<template>
	<!-- <div class="container">
		<div class="row">
			<div class="col-md-12 my-3">
				<h2>Room</h2>
				<input v-model="roomId" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="">
					<WebRTC
						ref="webrtc"
						width="100%"
						:roomId="roomId"
						v-on:joined-room="logEvent"
						v-on:left-room="logEvent"
						v-on:opened-room="logEvent"
						v-on:share-started="logEvent"
						v-on:share-stopped="logEvent"
						@error="onError"
					/>
				</div>
				<div class="row">
					<div class="col-md-12 my-3">
						<button type="button" class="btn btn-primary" @click="onJoin">
							Join
						</button>
						<button type="button" class="btn btn-primary" @click="onLeave">
							Leave
						</button>
						<button type="button" class="btn btn-primary" @click="onCapture">
							Capture
						</button>
						<button
							type="button"
							class="btn btn-primary"
							@click="onShareScreen"
						>
							ScreenSharing
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>Captured Image</h2>
				<figure class="figure">
					<img :src="img" class="img-responsive" />
				</figure>
			</div>
		</div>
	</div> -->
	<div class="main">
		<div class="main__left">
			<div class="main__videos">
				<WebRTC
					ref="webrtc"
					width="100%"
					:roomId="roomId"
					v-on:joined-room="logEvent"
					v-on:left-room="logEvent"
					v-on:opened-room="logEvent"
					v-on:share-started="logEvent"
					v-on:share-stopped="logEvent"
					@error="onError"
				/>
			</div>
			<div class="main__controls">
				<div class="main__controls__block">
					<div
						@click="onJoin"
						class="main__controls__button main__video_button"
					>
						<i class="fas fa-video"></i>
						<span>Join</span>
					</div>
					<div @click="onShareScreen" class="main__controls__button">
						<i class="fas fa-shield-alt"></i>
						<span>ShareScreen</span>
					</div>
				</div>
				<div class="main__controls__block">
					<div @click="onLeave" class="main__controls__button">
						<span class="leave_meeting">Leave Meeting</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// import { WebRTC } from 'vue-webrtc';
import WebRTC from '@/components/common/WebRTC.vue';
export default {
	props: {
		room: String,
	},
	components: { WebRTC },
	data() {
		return {
			img: null,
			roomId: this.room,
		};
	},
	methods: {
		onCapture() {
			this.img = this.$refs.webrtc.capture();
		},
		onJoin() {
			this.$refs.webrtc.join();
		},
		onLeave() {
			this.$refs.webrtc.leave();
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
	},
	beforeDestroy() {
		this.onLeave();
	},
};
</script>
<style lang="scss">
.main {
	width: 100%;
	min-height: 80vh;
	display: flex;
}

.main__left {
	flex: 1;
	display: flex;
	flex-direction: column;
}

.main__videos {
	flex-grow: 1;
	background-color: black;
	display: flex;
	justify-content: center;
	align-items: center;
	// padding: 40px;
}

.main__controls {
	background-color: #1c1e20;
}

.main__right {
	background-color: #242324;
	border-left: 1px solid #3d3d42;
}

.main__controls {
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
	cursor: pointer;
}

.main__controls__button:hover {
	background-color: #343434;
	border-radius: 5px;
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
// .video-list {
// 	display: grid;
// 	justify-content: center;
// 	flex-wrap: wrap;

// }
// video {
// 	background: black;
// 	height: 300px;
// 	width: 400px;
// 	object-fit: cover;
// 	padding: 8px;
// }
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
