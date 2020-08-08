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
			<label for="search" class="a11y-hidden">search: </label>
			<input
				@keypress.enter="
					check();
					reset();
				"
				v-model="searchData"
				type="search"
				id="search"
				placeholder="소모임을 검색하세요"
			/>
			<i class="icon ion-md-search nav-search-icon"></i>
		</div>
		<nav class="nav-router">
			<template v-if="!isUserLogin">
				<router-link class="nav-router-item" :to="{ name: 'login' }"
					>로그인</router-link
				>
			</template>
			<template v-else>
				<router-link class="nav-router-item" :to="{ name: 'addstudy' }"
					>만들기</router-link
				>
				<router-link class="nav-router-item" :to="{ name: 'newsfeed' }"
					>뉴스피드</router-link
				>
				<a class="nav-router-item" href="javascript:;" @click="logoutUser"
					>로그아웃</a
				>
			</template>
			<router-link v-if="name" class="nav-router-item" :to="`/profile/${name}`"
				><img class="nav-router-img" src="@/assets/logo.png" alt="프로필"
			/></router-link>
			<button @click="test">test</button>
		</nav>
	</header>
</template>

<script>
import axios from 'axios';
import { mapMutations, mapState } from 'vuex';
export default {
	data() {
		return {
			searchData: '',
			baseURL: process.env.VUE_APP_API_URL,
			userName: this.name ? this.name : null,
		};
	},
	computed: {
		...mapState(['name']),
		isUserLogin() {
			return this.$store.getters.isLogin;
		},
		isMainRoute() {
			return this.$route.name === 'main';
		},
	},
	methods: {
		...mapMutations(['clearUserEmail', 'clearToken']),
		async test() {
			const res = await axios.get(`${this.baseURL}accounts/login`);
			console.log(res);
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
.nav-white {
	background: none;
	color: white;
	.switon {
		// display: none;
	}
	#search {
		display: none;
	}
	i {
		display: none;
	}
	.nav-router-item {
		color: white;
	}
}
.switon-logo {
	padding-bottom: 0;
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
	#search {
		@include scale(width, 400px);
		padding: 0.8rem 2rem 0.8rem 0.75rem;
		font-size: $font-light;
		border: 1px solid #dbdbdb;
		color: -internal-light-dark(black, white);
		box-shadow: rgba(0, 0, 0, 0.04) 0px 0px 1px 0px,
			rgba(41, 42, 43, 0.16) 0px 1px 3px 0px;
		border-radius: 3px;
		transition: all 0.35s ease;
		&:focus {
			outline: none;
			background: rgb(248, 248, 249);
			border: none;
			@include scale(width, 420px);
			box-shadow: none;
		}
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
	.nav-router-img {
		width: 2rem;
		border-radius: 50%;
		object-fit: cover;
	}
	@media screen and (max-width: 640px) {
		display: none;
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
