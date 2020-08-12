<template>
	<header id="nav" :class="['container', isMainRoute ? 'nav-white' : '']">
		<div class="nav-search">
			<div class="nav-logo">
				<router-link class="switon-logo" :to="{ name: 'main' }">
					<img
						v-if="isMainRoute"
						src="@/assets/white.png"
						alt="logo"
						class="switon"/>
					<img v-else src="@/assets/color.png" alt="logo" class="switon"
				/></router-link>
			</div>
			<Search />
		</div>
		<nav class="nav-router">
			<template v-if="!isUserLogin">
				<router-link class="nav-router-item" :to="{ name: 'login' }"
					>로그인</router-link
				>
			</template>
			<template v-else>
				<router-link class="nav-router-item" :to="{ name: 'newsfeed' }"
					><span class="nav-router-full">뉴스피드</span
					><i class="icon ion-md-home nav-router-medium"></i
				></router-link>
				<router-link class="nav-router-item" :to="{ name: 'addstudy' }"
					><span class="nav-router-full">만들기</span
					><i class="icon ion-md-add nav-router-medium"></i
				></router-link>
				<a class="nav-router-item" href="javascript:;" @click="logoutUser"
					><span class="nav-router-full">로그아웃</span
					><i class="icon ion-md-power nav-router-medium"></i
				></a>
			</template>
			<router-link v-if="name" class="nav-router-item" :to="`/profile/${name}`"
				><img
					class="nav-router-img"
					:src="`${baseURL}${profileImg}`"
					alt="프로필"
			/></router-link>
		</nav>
	</header>
</template>

<script>
import { mapMutations, mapState } from 'vuex';
import { baseAuth } from '@/api/index';
import Search from '@/components/common/Search.vue';

export default {
	data() {
		return {
			userName: this.name ? this.name : null,
			profileImg: null,
		};
	},
	components: {
		Search,
	},
	computed: {
		...mapState(['name']),
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		isUserLogin() {
			return this.$store.getters.isLogin;
		},
		isMainRoute() {
			return this.$route.name === 'main';
		},
	},
	methods: {
		...mapMutations(['clearUserEmail', 'clearToken']),
		async fetchImg() {
			const { data } = await baseAuth.get('accounts/');
			this.profileImg = data.profile_image;
		},
		logoutUser() {
			this.clearUserEmail();
			this.clearToken();
			this.$cookies.remove('auth-token');
			this.$cookies.remove('name');
			this.$router.push({ name: 'main' });
		},
		onChangeSearch(val) {
			this.searchData = val;
		},
	},
	created() {
		this.fetchImg();
	},
};
</script>

<style lang="scss">
header {
	height: 70px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 3rem;
	background-color: #fff;
	@media screen and (max-width: 640px) {
		justify-content: flex-start;
	}
}
.list-block {
	display: block;
}
.list-none {
	display: none;
}
.nav-white {
	background: none;
	color: white;
	#search {
		display: none;
	}
	.nav-router-item {
		color: white;
	}
	i {
		color: white;
		font-size: 24px;
		font-weight: 600;
	}
}
.switon-logo {
	// padding-bottom: 0;
	display: flex;
	align-items: center;
}
.switon {
	@include scale(width, 50px);
}
.nav-search {
	position: relative;
	display: flex;
	align-items: center;
	.nav-logo {
		@include scale(margin-right, 32px);
	}
	.nav-search-icon {
		position: absolute;
		right: 0;
		font-size: $font-bold;
		color: -internal-light-dark(black, white);
		margin: 0.5rem;
	}
}
.nav-router {
	display: flex;
	height: 100%;
	align-items: center;
	font-weight: 600;
	.nav-router-item {
		margin-right: 0.5rem;
	}
	.nav-router-full {
		display: inline;
	}
	.nav-router-medium {
		display: none;
	}
	.nav-router-img {
		width: 2rem;
		height: 2rem;
		border-radius: 50%;
		object-fit: cover;
		border: 0.5px solid purple;
	}
	@media screen and (max-width: 768px) {
		.nav-router-full {
			display: none;
		}
		.nav-router-medium {
			display: inline;
		}
		i {
			font-size: 24px;
			font-weight: 600;
		}
	}
}
.a11y-hidden {
	overflow: hidden;
	position: absolute !important;
	top: -9999em;
	clip: rect(0, 0, 0, 0);
	width: 1px;
	height: 1px;
	margin: -1px;
}
@media screen and (max-width: 768px) {
	// .search input {
	// 	width: 150px;
	// 	padding-left: 8px;
	// }
}
</style>
