<template>
	<section>
		<article class="profile-box">
			<div class="profile-wrap">
				<img
					v-if="profileImg"
					class="profile-logo"
					:src="`${baseURL}${profileImg}`"
					:alt="`${userName}의 프로필 사진`"
				/>
				<img
					v-else
					class="profile-logo"
					src="https://blog.tofte-it.dk/wp-content/uploads/2018/12/profile-picture.png"
					:alt="`${userName}의 프로필 대체 사진`"
				/>
			</div>
			<div class="info-box">
				<div class="name-box">
					<h2>{{ userName }}</h2>
					<router-link
						:to="{ name: 'modifyprofile', props: { userName: userName } }"
					>
						<div class="modify-profile" v-if="isMe">프로필 수정</div>
					</router-link>

					<a class="mobile-log-out" href="javascript:;" @click="logoutUser"
						><span>로그아웃</span></a
					>
				</div>
				<div class="intro-box">
					<p class="introduce">
						{{ introduce }}
					</p>
					<div class="medal-box">
						<div class="badgegold">
							<div class="rounded">
								<i class="icon ion-md-medal" aria-hidden="true"></i>
							</div>
						</div>
						<span>{{ medals.gold }}</span>
						<div class="badgesilver">
							<div class="rounded">
								<i class="icon ion-md-medal" aria-hidden="true"> </i>
							</div>
						</div>
						<span>{{ medals.silver }}</span>
						<div class="badgebronze">
							<div class="rounded">
								<i class="icon ion-md-medal" aria-hidden="true"> </i>
							</div>
						</div>
						<span>{{ medals.bronze }}</span>
					</div>
				</div>

				<div class="middle-box">
					<div class="middle-element">
						<p>진행스터디 {{ studing }}</p>
					</div>
					<div class="middle-element">
						<p>종료스터디 {{ endStudy }}</p>
					</div>
				</div>
			</div>
		</article>
		<div class="medal-box">
			<div class="badgegold">
				<div class="rounded">
					<i class="icon ion-md-medal" aria-hidden="true"></i>
				</div>
			</div>
			<span>{{ medals.gold }}</span>
			<div class="badgesilver">
				<div class="rounded">
					<i class="icon ion-md-medal" aria-hidden="true"> </i>
				</div>
			</div>
			<span>{{ medals.silver }}</span>
			<div class="badgebronze">
				<div class="rounded">
					<i class="icon ion-md-medal" aria-hidden="true"> </i>
				</div>
			</div>
			<span>{{ medals.bronze }}</span>
		</div>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
import { fetchProfile, fetchMyStudy } from '@/api/auth';
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
			studing: null,
			endStudy: null,
			medals: {
				gold: null,
				silver: null,
				bronze: null,
			},
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
				this.fetchStudy();
				const name = this.userName;
				const { data } = await fetchProfile(name);
				this.profile = data;
				this.introduce = data.introduce === 'null' ? '' : data.introduce;
				this.profileImg = data.profile_image;
				this.medals = data.medals;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
				this.$router.push('/404');
			}
		},
		async fetchStudy() {
			try {
				const name = this.userName;
				const { data } = await fetchMyStudy(name);
				this.studing = data.unfinishedStudy.length;
				this.endStudy = data.finishedStudy.length;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	computed: {
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		isMe() {
			return this.$cookies.get('name') === this.userName;
		},
	},
	created() {
		this.fetchData();
	},
	watch: {
		$route() {
			this.fetchData();
		},
	},
};
</script>

<style lang="scss" scoped>
.intro-box {
	display: flex;
	flex-direction: column;
	// justify-content: flex-start;
	align-content: center;
	margin-top: 1rem;
	@media screen and (max-width: 1024px) {
		justify-content: center;
	}
	.medal-box {
		display: flex;
		align-items: center;
		margin-left: 3rem;
		margin-top: 1rem;
		.badgegold {
			@include grade-badge('gold', 30px);
		}
		.badgesilver {
			@include grade-badge('silver', 30px);
		}
		.badgebronze {
			@include grade-badge('bronze', 30px);
		}
		span {
			margin-right: 0.5rem;
		}
		@media screen and (max-width: 1024px) {
			display: none;
		}
	}
}
.profile-box {
	display: flex;
	margin-bottom: 1rem;
	@media screen and (max-width: 1024px) {
		flex-direction: column;
		justify-content: center;
		align-items: center;
		margin-bottom: 0;
	}
}
.info-box {
	flex: 2;
	margin-top: 1rem;
	display: flex;
	flex-direction: column;
	justify-content: center;
	width: 100%;
	.introduce {
		font-weight: 400;
		margin-left: 3rem;
		font-size: $font-normal * 1.1;
		@media screen and (max-width: 1024px) {
			text-align: center;
			margin-left: 0;
		}
	}
	.modify-profile {
		@include common-btn();
		display: flex;
		justify-content: center;
		align-items: center;
		margin-left: 3rem;
		width: 6rem;
	}
	.middle-box {
		display: flex;
		align-items: center;
		height: 40%;
		margin: 1.5rem 0;
		margin-left: 3rem;
		font-size: $font-normal;
		.middle-element {
			display: flex;
			flex-direction: column;
			align-items: center;
			font-size: $font-normal * 1.2;
			margin-right: 2rem;
			@media screen and (max-width: 1024px) {
				margin: 1.5rem 1rem;
			}
		}
		@media screen and (max-width: 1024px) {
			height: auto;
			margin: 0 auto;
			padding: 0;
		}
	}
	.name-box {
		display: flex;
		margin-left: 3rem;
		align-items: center;
		@media screen and (max-width: 1024px) {
			margin: 0 auto;
		}
	}

	h4 {
		margin-left: 1rem;
		@media screen and (max-width: 1024px) {
			text-align: center;
		}
	}
}
.profile-wrap {
	width: 170px;
	height: 170px;
	margin-left: 3rem;
	margin-right: 7rem;
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
		margin: 0;
		margin-bottom: 1rem;
	}
	.profile-logo {
		width: 150px;
		height: 150px;
		border-radius: 50%;
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
.medal-box {
	display: none;
	@media screen and (max-width: 1024px) {
		display: flex;
		align-items: center;
		justify-content: center;
		.badgegold {
			@include grade-badge('gold', 30px);
		}
		.badgesilver {
			@include grade-badge('silver', 30px);
		}
		.badgebronze {
			@include grade-badge('bronze', 30px);
		}
		span {
			margin-right: 1rem;
		}
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
}
</style>
