<template>
	<div class="rtc">
		<div class="video-list">
			<div
				v-for="item in videoList"
				v-bind:video="item"
				v-bind:key="item.id"
				class="video-item"
			>
				<video
					controls
					autoplay
					playsinline
					ref="videos"
					:height="cameraHeight"
					:muted="item.muted"
					:id="item.id"
				></video>
			</div>
		</div>
		<div class="main__right">
			<div class="main__header">
				<h6>Chat</h6>
			</div>
			<div class="main__chat_window">
				<ul class="messages"></ul>
			</div>
			<div class="main__message_container">
				<input
					@keyup.enter="pushMessage"
					id="chat_message"
					type="text"
					placeholder="Type message here..."
				/>
			</div>
		</div>
	</div>
</template>

<script>
import RTCMultiConnection from 'rtcmulticonnection';
require('adapterjs');
export default {
	name: 'vue-webrtc',
	data() {
		return {
			rtcmConnection: null,
			localVideo: null,
			videoList: [],
			canvas: null,
		};
	},
	props: {
		roomId: {
			type: String,
			default: 'public-room',
		},
		socketURL: {
			type: String,
			default: 'https://rtcmulticonnection.herokuapp.com:443/',
		},
		cameraHeight: {
			type: [Number, String],
			default: 160,
		},
		autoplay: {
			type: Boolean,
			default: true,
		},
		screenshotFormat: {
			type: String,
			default: 'image/jpeg',
		},
		enableAudio: {
			type: Boolean,
			default: true,
		},
		enableVideo: {
			type: Boolean,
			default: true,
		},
		enableLogs: {
			type: Boolean,
			default: false,
		},
		stunServer: {
			type: String,
			default: null,
		},
		turnServer: {
			type: String,
			default: null,
		},
	},
	watch: {},
	mounted() {
		var that = this;

		this.rtcmConnection = new RTCMultiConnection();
		this.rtcmConnection.socketURL = this.socketURL;
		this.rtcmConnection.autoCreateMediaElement = false;
		this.rtcmConnection.enableLogs = this.enableLogs;
		this.rtcmConnection.session = {
			audio: this.enableAudio,
			video: this.enableVideo,
			data: true,
		};
		this.rtcmConnection.sdpConstraints.mandatory = {
			OfferToReceiveAudio: this.enableAudio,
			OfferToReceiveVideo: this.enableVideo,
		};
		this.rtcmConnection.enableFileSharing = true; // by default, it is "false".

		this.rtcmConnection.socketMessageEvent = 'audio-video-file-chat-demo';
		// this.rtcmConnection.iceServers = [
		// 	{
		// 		urls: [
		// 			'stun:stun.l.google.com:19302',
		// 			'stun:stun1.l.google.com:19302',
		// 			'stun:stun2.l.google.com:19302',
		// 			'stun:stun.l.google.com:19302?transport=udp',
		// 		],
		// 	},
		// ];
		localStorage.setItem(this.rtcmConnection.socketMessageEvent, this.roomId);

		if (this.stunServer !== null || this.turnServer !== null) {
			this.rtcmConnection.iceServers = []; // clear all defaults
		}
		if (this.stunServer !== null) {
			this.rtcmConnection.iceServers.push({
				urls: this.stunServer,
			});
		}
		if (this.turnServer !== null) {
			var parse = this.turnServer.split('%');
			var username = parse[0].split('@')[0];
			var password = parse[0].split('@')[1];
			var turn = parse[1];
			this.rtcmConnection.iceServers.push({
				urls: turn,
				credential: password,
				username: username,
			});
		}
		this.rtcmConnection.onstream = function(stream) {
			let found = that.videoList.find(video => {
				return video.id === stream.streamid;
			});
			if (found === undefined) {
				let video = {
					id: stream.streamid,
					muted: stream.type === 'local',
				};

				that.videoList.push(video);

				if (stream.type === 'local') {
					that.localVideo = video;
				}
			}

			setTimeout(function() {
				for (var i = 0, len = that.$refs.videos.length; i < len; i++) {
					if (that.$refs.videos[i].id === stream.streamid) {
						that.$refs.videos[i].srcObject = stream.stream;
						break;
					}
				}
			}, 1000);

			that.$emit('joined-room', stream.streamid);
		};
		this.rtcmConnection.onstreamended = function(stream) {
			var newList = [];
			that.videoList.forEach(function(item) {
				if (item.id !== stream.streamid) {
					newList.push(item);
				}
			});
			that.videoList = newList;
			that.$emit('left-room', stream.streamid);
		};
	},
	methods: {
		join() {
			var that = this;
			this.rtcmConnection.openOrJoin(this.roomId, function(
				isRoomExist,
				roomid,
			) {
				if (isRoomExist === false && that.rtcmConnection.isInitiator === true) {
					that.$emit('opened-room', roomid);
				}
			});
		},
		leave() {
			this.rtcmConnection.attachStreams.forEach(function(localStream) {
				localStream.stop();
			});
			this.videoList = [];
		},
		capture() {
			return this.getCanvas().toDataURL(this.screenshotFormat);
		},
		getCanvas() {
			let video = this.getCurrentVideo();
			if (video !== null && !this.ctx) {
				let canvas = document.createElement('canvas');
				canvas.height = video.clientHeight;
				canvas.width = video.clientWidth;
				this.canvas = canvas;
				this.ctx = canvas.getContext('2d');
			}
			const { ctx, canvas } = this;
			ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
			return canvas;
		},
		getCurrentVideo() {
			if (this.localVideo === null) {
				return null;
			}
			for (var i = 0, len = this.$refs.videos.length; i < len; i++) {
				if (this.$refs.videos[i].id === this.localVideo.id)
					return this.$refs.videos[i];
			}
			return null;
		},
		addStreamStopListener(stream, callback) {
			var streamEndedEvent = 'ended';
			if ('oninactive' in stream) {
				streamEndedEvent = 'inactive';
			}
			stream.addEventListener(
				streamEndedEvent,
				function() {
					callback();
					callback = function() {};
				},
				false,
			);
		},
		onGettingSteam(stream) {
			this.rtcmConnection.addStream(stream);
			this.$emit('share-started', stream.streamid);

			this.addStreamStopListener(stream, function() {
				this.rtcmConnection.removeStream(stream.streamid);
				this.$emit('share-stopped', stream.streamid);
			});
		},
		getDisplayMediaError(error) {
			console.log('Media error: ' + JSON.stringify(error));
		},
		shareScreen() {
			if (navigator.getDisplayMedia || navigator.mediaDevices.getDisplayMedia) {
				if (navigator.mediaDevices.getDisplayMedia) {
					navigator.mediaDevices
						.getDisplayMedia({ video: true, audio: false })
						.then(stream => {
							this.onGettingSteam(stream);
						}, this.getDisplayMediaError)
						.catch(this.getDisplayMediaError);
				} else if (navigator.getDisplayMedia) {
					navigator
						.getDisplayMedia({ video: true })
						.then(stream => {
							this.onGettingSteam(stream);
						}, this.getDisplayMediaError)
						.catch(this.getDisplayMediaError);
				}
			}
		},
		appendDIV(event) {
			var chatContainer = document.querySelector('.messages');
			var div = document.createElement('div');
			div.innerHTML = event.data || event;
			chatContainer.insertBefore(div, chatContainer.firstChild);
			div.tabIndex = 0;
			div.focus();
			document.getElementById('chat_message').focus();
		},
		pushMessage(e) {
			if (e.keyCode != 13) return;

			// removing trailing/leading whitespace
			e.target.value = e.target.value.replace(/^\s+|\s+$/g, '');
			if (!e.target.value.length) return;

			this.rtcmConnection.send(e.target.value);
			this.appendDIV(e.target.value);
			e.target.value = '';
		},
	},
};
</script>
<style>
.rtc {
	width: 100%;
	height: 100%;
	display: flex;
	/* position: relative; */
}
.video-list {
	flex: 0.85;
	height: auto;
}

.main__right {
	flex: 0.15;
}

.video-list div {
	padding: 0px;
}

.video-item {
	display: inline-block;
}
.main__header {
	padding-top: 5px;
	color: #f5f5f5;
	text-align: center;
}
.main__chat_window {
	flex-grow: 1;
	overflow-y: auto;
}

.messages {
	color: white;
	list-style: none;
}

.main__message_container {
	padding: 22px 12px;
	display: flex;
}

.main__message_container input {
	flex-grow: 1;
	background-color: transparent;
	border: none;
	color: #f5f5f5;
}
</style>
