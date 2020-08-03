<template>
	<div id="app">
		<AppHeader v-if="!isAccountsRoute"></AppHeader>
		<main :class="[!isAccountsRoute ? 'main-container' : '']">
			<router-view />
		</main>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
export default {
	components: {
		AppHeader,
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
@media screen and (max-width: 768px) {
	.main-container {
		width: 95%;
	}
}
</style>
