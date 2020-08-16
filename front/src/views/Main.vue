<template>
	<section class="main-page">
		<AppHeader></AppHeader>
		<Search />
		<div v-if="studies && isUserLogin" class="popular-wrap">
			<p class="popular-title">인기 소모임<span></span></p>
			<router-link
				class="popular-item-link"
				:key="study.id"
				v-for="study in studies"
				:to="`/study/${study.id}`"
			>
				<MainCard :study="study" colorPick="white" />
			</router-link>
		</div>
		<div v-else class="main-page-description">
			<div class="main-description-title">
				<h1 class="animate__animated animate__zoomOut animate__delay-1s">
					Study with Online
				</h1>
				<h1 class="animate__animated animate__zoomIn animate__delay-2s">
					SwitOn
				</h1>
				<p>온라인에서 함께하는 스터디</p>
			</div>
			<p>
				출석 버튼으로, 나도
				<span>switch on</span><i class="icon ion-md-bulb high-light"></i>
			</p>
			<p>
				정보 공유<i class="icon ion-md-information exclamation"></i> 일정 관리<i
					class="icon ion-md-information exclamation"
				></i>
				이력 관리<i class="icon ion-md-information exclamation"></i>
			</p>
			<p>스윗온과 함께 해요 :)</p>
		</div>
	</section>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import Search from '@/components/common/Search.vue';
import MainCard from '@/components/common/MainCard.vue';
import { fetchStudies } from '@/api/studies';
import { mapGetters } from 'vuex';

export default {
	components: {
		Search,
		MainCard,
		AppHeader,
	},
	data() {
		return {
			studies: null,
			isLoading: false,
		};
	},
	computed: {
		...mapGetters(['isLogin']),
		isUserLogin() {
			return this.isLogin;
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
		this.fetchData();
	},
};
</script>

<style lang="scss">
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
			position: absolute;
			top: -25px;
			left: 10px;
			color: white;
			font-weight: bold;
			span {
				width: 100%;
				height: 8px;
				position: absolute;
				bottom: -4px;
				left: 0;
				border-radius: 2px;
				background: $btn-purple;
				opacity: 0.5;
			}
			@media screen and (max-width: 484px) {
				top: -15px;
				left: 50px;
			}
			@media screen and (max-width: 350px) {
				top: -15px;
				left: 20px;
			}
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
		}
		@media screen and (max-width: 1024px) and (min-width: 950px) {
			width: 70%;
		}
	}
	.main-page-description {
		width: 100%;
		min-height: 50vh;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		color: #fff;
		.main-description-title {
			display: grid;
			place-items: center;
			margin-bottom: 20px;
			h1 {
				font-size: 40px;
			}
			// .hide-show {
			// 	animation: change-title 0.5s;
			// 	-webkit-animation: change-title 0.5s;
			// 	animation-delay: 1s;
			// 	animation-fill-mode: forwards;
			// }
			// @keyframes change-title {
			// 	from {
			// 	}
			// 	to {
			// 		height: 0;
			// 	}
			// }
			// @-webkit-keyframes change-title {
			// 	from {
			// 	}
			// 	to {
			// 		height: 0;
			// 	}
			// }
		}
		.exclamation {
			margin: 0 10px 0 5px;
			font-size: 1.5rem;
			// transform: translateY(-15px) rotate(180deg);
		}
		.high-light {
			margin: 0 5px;
			font-size: 1.5rem;
			animation: change-color 0.5s;
			-webkit-animation: change-color 0.5s;
			animation-delay: 3s;
			animation-fill-mode: forwards;
		}
		@keyframes change-color {
			from {
				color: #fff;
			}
			to {
				color: rgb(255, 217, 0);
			}
		}
		@-webkit-keyframes change-color {
			from {
				color: #fff;
			}
			to {
				color: rgb(255, 217, 0);
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
		position: fixed;
		bottom: 0;
		right: 0;
		z-index: 888;
	}
}
</style>
