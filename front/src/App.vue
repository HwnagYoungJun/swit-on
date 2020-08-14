<template>
	<div id="app">
		<section v-if="isMainRoute" class="main-page">
			<AppHeader v-if="!isAccountsRoute"></AppHeader>
			<Search />
			<div v-if="studies" class="popular-wrap">
				<p class="popular-title">인기 소모임</p>
				<router-link
					:key="study.id"
					v-for="study in studies"
					:to="`/study/${study.id}`"
				>
					<!-- <div class="popular-item">
						<div class="popular-img">
							<img
								v-if="study.logo"
								:src="`${BaseUrl}${study.logo}`"
								alt="study-logo"
							/>
							<img v-else src="@/assets/django.png" alt="" />
							<p class="temp">
								{{ study.users_current }} / {{ study.users_limit }}
							</p>
						</div>
						<div class="popular-content">
							<p class="content-title">{{ study.name }}</p>
							<p class="content-week">
								<span class="content-day">{{
									study.week | formatWeekday
								}}</span>
							</p>
							<p class="content-time">
								{{ study.start_time }}-{{ study.end_time }}
							</p>
						</div>
					</div> -->
					<MainCard :study="study" colorPick="white" />
				</router-link>
			</div>
		</section>
		<section v-else class="main-wrap">
			<AppHeader v-if="!isAccountsRoute"></AppHeader>
			<main :class="[!isAccountsRoute ? 'main-container' : '']">
				<router-view />
			</main>
			<footer><Footer /></footer>
		</section>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import Footer from '@/components/common/Footer.vue';
import Search from '@/components/common/Search.vue';
import MainCard from '@/components/common/MainCard.vue';
import { fetchStudies } from '@/api/studies';

export default {
	components: {
		AppHeader,
		Footer,
		Search,
		MainCard,
	},
	data() {
		return {
			studies: null,
			isLoading: false,
		};
	},
	computed: {
		isAccountsRoute() {
			return this.$route.name === 'signUp' || this.$route.name === 'login';
		},
		isMainRoute() {
			return this.$route.name === 'main';
		},
	},
	methods: {
		async fetchData() {
			this.isLoading = true;
			const { data } = await fetchStudies();
			console.log(data);
			this.isLoading = false;
			this.studies = data.reverse().splice(0, 4);
		},
	},
	created() {
		if (this.isMainRoute) {
			this.fetchData();
		}
	},
	watch: {
		isMainRoute() {
			if (this.isMainRoute) {
				this.fetchData();
			}
		},
	},
};
</script>

<style lang="scss">
@import './assets/css/reset.css';
@import './assets/css/common.css';

.main-wrap {
	display: flex;
	min-height: 100vh;
	flex-direction: column;
}
.main-container {
	flex: 1;
	width: 70%;
	margin: 0 auto;
}
.main-page {
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
	min-height: 100vh;
	max-height: 100%;
	background: $btn-purple-opacity;
	.popular-wrap {
		width: 80%;
		margin: 0 auto;
		display: flex;
		justify-content: space-evenly;
		flex-wrap: wrap;
		position: relative;
		.popular-title {
			color: white;
			font-size: $font-normal;
			position: absolute;
			top: -20px;
			left: 40px;
		}
		.popular-item {
			display: grid;
			grid-template-columns: 15rem;
			grid-template-rows: 7rem 12rem;
			grid-template-areas:
				'text-part'
				'image-part';
			border: 1px solid transparent;
			position: relative;
			margin-top: 1rem;
			padding: 0.3rem 0.5rem 0.5rem;
			color: #454545;
			// background: rgba(255, 255, 255, 0.05);
			box-shadow: 3px 2px 6px rgba(37, 37, 37, 0.5);
			.popular-img {
				grid-area: image-part;
				overflow: hidden;
				cursor: pointer;
			}
			.popular-content {
				grid-area: text-part;
				color: #fff;
				.content-category {
					padding-bottom: 0.3rem;
					border-bottom: 1px solid #fff;
				}
				.content-title {
					font-size: $font-bold;
					font-weight: 600;
					padding-top: 0.3rem;
					padding-bottom: 0.3rem;
				}
				.content-week {
					// padding-top: 0.3rem;
					.content-day {
						font-weight: 600;
					}
				}
			}
			.temp {
				position: absolute;
				bottom: 1rem;
				right: 1rem;
				color: white;
			}
			img {
				width: 100%;
				height: 100%;
				object-fit: fill;
				transition: all 0.3s ease;
				&:hover {
					transform: scale(1.1);
				}
			}
			p {
				// margin-top: -3px;
			}
		}
	}
}
@media screen and (max-width: 768px) {
	.main-container {
		width: 95%;
	}
	.nav-router {
		width: 100%;
		height: 37px;
		justify-content: space-around;
		position: absolute;
		bottom: 0;
		right: 0;
		z-index: 888;
	}
}
</style>
