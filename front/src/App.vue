<template>
	<div id="app">
		<section v-if="isMain" class="main-page">
			<input
				type="search"
				class="main-input"
				placeholder="소모임을 검색하세요"
			/>
			<div class="popular-wrap">
				<p class="popular-title">인기 소모임</p>
				<div class="popular-item" :key="num" v-for="num in [1, 2, 3, 4]">
					<div class="popular-img">
						<img src="@/assets/react.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<!-- <p class="content-category">
							web <i class="icon ion-md-arrow-dropright"></i> django
						</p> -->
						<p class="content-title">드장고 완벽 가이드</p>
						<p class="content-week">
							<span class="content-day"> 월</span
							><span class="content-day"> 수</span>
						</p>
						<p class="content-time">9:00-11:00</p>
					</div>
				</div>
				<!-- <div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/django.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p class="content-category">
							web <i class="icon ion-md-arrow-dropright"></i> django
						</p>
						<p class="content-title">django</p>
						<p class="content-week">월, 수 9시-11시</p>
					</div>
				</div>
				<div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/cloud.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p class="content-title">django</p>
						<p class="content-week">월, 수 9시-11시</p>
						<p>web>django</p>
					</div>
				</div>
				<div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/color.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p class="content-title">장고 부수기</p>
						<p class="content-week">월, 수 9시-11시</p>
						<p>web>django</p>
					</div>
				</div> -->
			</div>
		</section>
		<section v-else>
			<AppHeader v-if="!isAccountsRoute"></AppHeader>
			<main :class="[!isAccountsRoute ? 'main-container' : '']">
				<router-view />
			</main>
		</section>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';

export default {
	components: {
		AppHeader,
	},
	data() {
		return {
			isMain: true,
		};
	},
	computed: {
		isAccountsRoute() {
			return this.$route.name === 'signUp' || this.$route.name === 'login';
		},
	},
};
</script>

<style lang="scss">
@import './assets/css/reset.css';
@import './assets/css/common.css';

.main-container {
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
	.main-input {
		width: 30%;
		padding: 13px 25px;
		line-height: 2;
		border: none;
		border-radius: 4px;
		background: rgba(255, 255, 255, 0.5);
		box-shadow: 3px 3px 5px rgba(83, 83, 83, 0.35);
		&:focus {
			outline: none;
			background: rgba(255, 255, 255, 1);
		}
	}
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
}
</style>
