<template>
	<section class="signup-container">
		<section class="signup-box">
			<header class="signup-header">
				<router-link :to="{ name: 'main' }">
					<img src="@/assets/black.png" alt="logo" class="switon" />
				</router-link>
				<router-link :to="{ name: 'login' }">
					<i class="icon ion-md-arrow-round-back signup-backbtn"></i>
				</router-link>
			</header>
			<h2>회원가입</h2>
			<SignupForm></SignupForm>
		</section>
		<div class="rel">
			<img class="signup-img" src="@/assets/login.png" alt="signupImage" />
			<div class="opacity"></div>
		</div>
	</section>
</template>

<script>
import SignupForm from '@/components/accounts/SignupForm.vue';
export default {
	components: {
		SignupForm,
	},
};
</script>

<style lang="scss">
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
	border: 1rem solid rgba(50, 49, 48, 0.2);
}
.signup-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 2rem;
}
.signup-container {
	display: grid;
	width: 100%;
	height: 100%;
	grid-template-rows: 100%;
	grid-template-columns: repeat(2, 50vw);
	grid-template-areas: 'box img';
	overflow: hidden;
}

.signup-box {
	grid-area: box;
	@include scale(width, 400px);
	height: 100%;
	margin: 3rem auto;
	.signup-logo {
		font-size: 50px;
		color: black;
	}
	.signup-backbtn {
		font-size: $font-bold;
		color: black;
	}
}
.signup-img {
	grid-area: img;
	width: 100%;
	height: 100%;
	object-fit: cover;
	box-sizing: content-box;
}
h2 {
	font-size: $font-bold;
	font-weight: bold;
	margin-bottom: 10px;
}

@media (max-width: 768px) {
	.signup-container {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: auto;
	}
	.signup-img {
		display: none;
	}
	.signup-box {
		margin: 2rem auto;
	}
}
</style>
