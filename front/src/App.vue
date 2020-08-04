<template>
	<div id="app">
		<section v-if="isMain" class="main-page">
			<input
				type="search"
				class="main-input"
				placeholder="소모임을 검색하세요"
			/>
			<p class="popular-title">인기 소모임</p>
			<div class="popular-wrap">
				<div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/django.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p>django</p>
						<p>월, 수 9시-11시</p>
						<p>web>django</p>
					</div>
				</div>
				<div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/react.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p>django</p>
						<p>월, 수 9시-11시</p>
						<p>web>django</p>
					</div>
				</div>
				<div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/color.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p>django</p>
						<p>월, 수 9시-11시</p>
						<p>web>django</p>
					</div>
				</div>
				<div class="popular-item">
					<div class="popular-img">
						<img src="@/assets/color.png" alt="study-logo" />
						<p class="temp">5/10</p>
					</div>
					<div class="popular-content">
						<p>django</p>
						<p>월, 수 9시-11시</p>
						<p>web>django</p>
					</div>
				</div>
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
		border-radius: 10px;
		background: rgba(255, 255, 255, 0.5);
		&:focus {
			outline: none;
			background: rgba(255, 255, 255, 1);
		}
	}
	.popular-wrap {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-evenly;
		align-content: space-between;
		position: relative;
		.popular-title {
			color: white;
			font-size: $font-light;
			position: absolute;
			top: -25px;
			left: 5px;
		}
		.popular-item {
			width: 230px;
			margin: 0 0.5rem;
			display: flex;
			flex-direction: column;
			color: white;
			border: 1px solid transparent;
			border-radius: 4px;
			position: relative;
			.popular-img {
				width: 100%;
				height: 100%;
			}
			.popular-content {
				background: rgba(255, 255, 255, 0.1);
			}
			.temp {
				position: absolute;
				bottom: 0;
				right: 0;
			}
			img {
				width: 100%;
				height: 100%;
				object-fit: fill;
				border-radius: 4px;
			}
			p {
				margin-top: -3px;
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
