<template>
	<div id="app">
		<Main v-if="isMainRoute" />
		<section v-else class="main-wrap">
			<AppHeader v-if="!isAccountsRoute"></AppHeader>
			<main :class="[!isAccountsRoute ? 'main-container' : '']">
				<router-view />
			</main>
			<footer><Footer /></footer>
		</section>
		<ToastPopup></ToastPopup>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import Footer from '@/components/common/Footer.vue';
import Main from '@/views/Main.vue';
import ToastPopup from './components/common/ToastPopup.vue';

export default {
	components: {
		AppHeader,
		Footer,
		ToastPopup,
		Main,
	},
	computed: {
		isAccountsRoute() {
			return this.$route.name === 'signUp' || this.$route.name === 'login';
		},
		isMainRoute() {
			return this.$route.name === 'main';
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
