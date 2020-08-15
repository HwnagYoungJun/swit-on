<template>
	<div>
		<div class="profile-box">
			<div class="profile-wrap">
				<img
					v-if="profileImg"
					class="profile-logo"
					:src="`${baseURL}${profileImg}`"
					alt="profile_img"
				/>
				<img v-else class="profile-logo" src="@/assets/color.png" alt="" />
			</div>
			<div class="info-box">
				<div class="name-box">
					<img
						class="icon-box"
						src="https://www.shareicon.net/data/128x128/2015/09/27/108202_game_512x512.png"
						alt="icon_img"
					/>
					<h2>{{ userName }}</h2>
					<router-link
						:to="{ name: 'modifyprofile', props: { userName: userName } }"
					>
						<div class="modify-profile">프로필 수정하기</div>
					</router-link>
					<a class="mobile-log-out" href="javascript:;" @click="logoutUser"
						><span>로그아웃</span></a
					>
				</div>
				<div class="middle-box">
					<div class="middle-element">
						<p>{{ studying }}</p>
						<p>진행중</p>
					</div>
					<div class="middle-element">
						<p>{{ studyed }}</p>
						<p>완료함</p>
					</div>
				</div>
				<h4>
					{{ introduce }}
				</h4>
			</div>
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import { fetchProfile } from '@/api/auth';
import { mapMutations } from 'vuex';

export default {
	props: {
		userName: String,
	},
	data() {
		return {
			profile: null,
			introduce: null,
			profileImg: null,
			studying: 5,
			studyed: 6,
		};
	},
	methods: {
		...mapMutations(['clearUserEmail', 'clearToken']),
		logoutUser() {
			this.clearUserEmail();
			this.clearToken();
			this.$cookies.remove('auth-token');
			this.$cookies.remove('name');
			this.$router.push({ name: 'main' });
		},
		async fetchData() {
			try {
				const name = this.userName;
				const { data } = await fetchProfile(name);
				this.profile = data;
				this.introduce = data.introduce === 'null' ? '' : data.introduce;
				this.profileImg = data.profile_image;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
	},
	created() {
		this.fetchData();
	},
};
</script>

<style lang="scss" scoped>
.profile-box {
	display: flex;
	margin-bottom: 1.5rem;
	@media screen and (max-width: 1024px) {
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
}
.info-box {
	flex: 2;
	display: flex;
	flex-direction: column;
	width: 100%;
	.modify-profile {
		@include common-btn();
		display: flex;
		justify-content: center;
		align-items: center;
		margin-left: 3rem;
		width: 8rem;
	}
	.middle-box {
		display: flex;
		align-items: center;
		height: 40%;
		margin: 1.5rem 0;
		.middle-element {
			display: flex;
			flex-direction: column;
			align-items: center;
			margin: 0 3rem;
			@media screen and (max-width: 1024px) {
				margin: 1.5rem 2rem;
			}
		}
		p {
			font-size: $font-bold * 0.9;
		}
		@media screen and (max-width: 1024px) {
			height: auto;
			margin: 0 auto;
			padding: 0;
		}
	}
	.name-box {
		display: flex;
		margin: 0;
		align-items: center;
		@media screen and (max-width: 1024px) {
			margin: 0 auto;
		}
	}

	h4 {
		margin-left: 1rem;
		@media screen and (max-width: 1024px) {
			text-align: center;
			margin-bottom: 1.5rem;
		}
	}
}
.profile-wrap {
	width: 170px;
	height: 170px;
	margin-right: 100px;
	position: relative;
	border: 4px solid transparent;
	border-radius: 50%;
	background: #fff;
	background-clip: padding-box;
	display: grid;
	place-items: center;
	&::after {
		width: 170px;
		height: 170px;
		position: absolute;
		top: -4px;
		bottom: -4px;
		left: -4px;
		right: -4px;
		background: linear-gradient(235deg, #bc69d3 8%, #6c23c0 75%, #43009b);
		content: '';
		z-index: -1;
		border-radius: 50%;
	}
	@media screen and (max-width: 1024px) {
		margin-right: 0;
	}
	.profile-logo {
		width: 150px;
		height: 150px;
		border-radius: 50%;
		// object-fit: cover;
	}
}
.mobile-log-out {
	display: none;
	justify-content: center;
	align-items: center;
	margin-left: 1rem;
	width: 5rem;
	@include common-btn();
	@media screen and (max-width: 768px) {
		display: flex;
	}
}
.icon-box {
	display: flex;
	width: 50px;
	height: 50px;
	overflow: hidden;
}
h2 {
	height: 30px;
	display: flex;
	align-items: center;
	border-bottom: 2px solid black;
}
</style>
