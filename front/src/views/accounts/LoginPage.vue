<template>
	<section class="login-container">
		<section class="login-box">
			<header class="login-header">
				<router-link :to="{ name: 'main' }">
					<img src="@/assets/black.png" alt="switon-logo" class="switon" />
				</router-link>
			</header>
			<h2>로그인</h2>
			<LoginForm></LoginForm>
		</section>
		<div class="rel">
			<img
				class="login-img"
				src="@/assets/login.png"
				alt="회원가입 디자인 사진"
			/>
			<div class="opacity"></div>
		</div>
	</section>
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
.rel {
	position: relative;
}
.opacity {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	box-sizing: border-box;
	border: 1.5rem solid rgba(50, 49, 48, 0.3);
}
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
