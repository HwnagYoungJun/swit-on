<template>
	<div class="login-container">
		<div class="login-box">
			<div class="login-header">
				<router-link :to="{ name: 'main' }">
					<img src="@/assets/black.png" alt="logo" class="switon" />
				</router-link>
			</div>
			<h2>로그인</h2>
			<LoginForm></LoginForm>
		</div>
		<img
			class="login-img"
			src="https://dimg.donga.com/wps/NEWS/IMAGE/2019/11/02/98183409.2.jpg"
			alt="loginImage"
		/>
	</div>
</template>

<script>
import LoginForm from '@/components/accounts/LoginForm.vue';
export default {
	components: {
		LoginForm,
	},
};
</script>

<style lang="scss" scoped>
.login-header {
	margin-bottom: 2rem;
}
.login-container {
	display: grid;
	width: 100vw;
	height: 100vh;
	grid-template-rows: minmax(100%, 1fr);
	grid-template-columns: repeat(2, 50vw);
	grid-template-areas: 'img box';
	overflow: hidden;
}
.login-box {
	grid-area: box;
	@include scale(width, 400px);
	height: 100%;
	margin: 3rem auto;
}
.login-img {
	grid-area: img;
	width: 100%;
	height: 100%;
	object-fit: cover;
}
h2 {
	font-weight: bold;
	font-size: $font-bold;
	margin-bottom: 10px;
}
@media (max-width: 768px) {
	.login-container {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: auto;
	}
	.login-img {
		display: none;
	}
	.login-box {
		margin: 1rem auto;
		padding-bottom: 1rem;
	}
}
</style>
