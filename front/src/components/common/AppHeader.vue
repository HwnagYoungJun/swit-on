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
			<div
				class="auto-complete"
				@keyup.down="selectStudy('down')"
				@keyup.up="selectStudy('up')"
			>
				<input
					autocomplete="off"
					v-model="searchData"
					v-on:input="fetchAutoComplete"
					type="search"
					id="search"
					placeholder="소모임을 검색하세요"
				/>
				<div v-if="searchedData" class="searched-datas">
					<ul>
						<li
							v-for="data in searchedData"
							:key="data.id"
							@click="moveStudy(data.id)"
							@keyup.enter="moveStudy(data.id)"
						>
							{{ data.name }}
						</li>
					</ul>
				</div>
			</div>
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
import { searchStudy } from '@/api/studies.js';
export default {
	data() {
		return {
			searchData: '',
			searchedData: [],
			userName: this.name ? this.name : null,
			profileImg: null,
		};
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
		async fetchAutoComplete(e) {
			try {
				if (!e.target.value) {
					return (this.searchedData = []);
				}
				const { data } = await searchStudy(e.target.value);
				this.searchedData = data;
			} catch (error) {
				console.log(error);
			}
		},
		clearSearchedData() {
			this.searchData = '';
			this.searchedData = [];
		},
		moveStudy(id) {
			this.clearSearchedData();
			this.$router.push(`/study/${id}`);
		},
		selectStudy(key) {
			console.log(key);
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
	#search {
		width: 400px;
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
			width: 420px;
			box-shadow: none;
		}
		@media screen and (max-width: 768px) {
			width: 200px;
			&:focus {
				width: 220px;
			}
		}
	}
	.nav-search-icon {
		position: absolute;
		right: 0;
		font-size: $font-bold;
		color: -internal-light-dark(black, white);
		margin: 0.5rem;
	}
	.auto-complete {
		position: relative;
		.searched-datas {
			width: 420px;
			position: absolute;
			top: 60px;
			background: #fff;
			box-shadow: 0 3px 7px rgb(214, 214, 214);
			z-index: 999;
			li {
				padding: 10px 10px;
				&:hover {
					background: #f2f2f2;
					cursor: pointer;
				}
			}
			@media screen and (max-width: 768px) {
				width: 220px;
			}
		}
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
