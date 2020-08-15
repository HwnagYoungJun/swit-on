<template>
	<form class="signup-form" autocomplete="off" @submit.prevent="submitForm">
		<InputBox
			type="text"
			userInfo="name"
			title="이름"
			:message="changeNameMessage"
			:color="changeNameColor"
			text="이름을 입력하세요"
			:inputChange="onChangeName"
		></InputBox>
		<InputBox
			type="email"
			userInfo="email"
			title="이메일"
			:message="changeEmailMessage"
			:color="changeEmailColor"
			text="이메일을 입력하세요"
			:inputChange="onChangeEmail"
		></InputBox>
		<InputBox
			type="password"
			userInfo="password"
			title="비밀번호"
			:message="changePasswordMessage"
			:color="changePasswordColor"
			text="비밀번호를 입력하세요"
			:inputChange="onChangePassword"
		></InputBox>
		<InputBox
			type="password"
			userInfo="password2"
			title="비밀번호 확인"
			:message="changePasswordConfirmMessage"
			:color="changePasswordConfirmColor"
			text="다시 비밀번호를 입력하세요"
			:inputChange="onChangePasswordConfirm"
		></InputBox>
		<div class="signup-btn">
			<button
				:disabled="isButtonDisabled"
				:class="[isButtonDisabled ? 'signup-btn-disabled' : '']"
				type="submit"
			>
				회원가입
			</button>
		</div>
	</form>
</template>

<script>
import bus from '@/utils/bus.js';
import InputBox from '@/components/common/InputBox.vue';
import { mapActions } from 'vuex';
import { validateEmail, validatePassword } from '@/utils/validation';
export default {
	components: {
		InputBox,
	},
	data() {
		return {
			signupData: {
				email: '',
				name: '',
				password: '',
				password2: '',
			},
		};
	},
	computed: {
		isValidEmail() {
			return validateEmail(this.signupData.email);
		},
		isValidPassword() {
			return validatePassword(this.signupData.password);
		},
		isButtonDisabled() {
			return (
				!this.isValidEmail ||
				!this.isValidPassword ||
				!(this.signupData.password === this.signupData.password2) ||
				!this.signupData.name
			);
		},
		changeNameColor() {
			let color =
				this.signupData.name.length === 0
					? 'black'
					: this.signupData.name.length > 1
					? 'green'
					: 'red';
			return color;
		},
		changeNameMessage() {
			let name =
				this.changeNameColor === 'black'
					? false
					: this.changeNameColor === 'green'
					? // '올바른 형식의 이름을 입력했습니다.'
					  false
					: '<i class="icon ion-md-close-circle"></i> 이름을 2글자 이상 적어주세요! ';
			return name;
		},
		changeEmailColor() {
			let color =
				this.signupData.email.length === 0
					? 'black'
					: this.isValidEmail
					? 'green'
					: 'red';
			return color;
		},
		changeEmailMessage() {
			let name =
				this.changeEmailColor === 'black'
					? false
					: this.changeEmailColor === 'green'
					? // '올바른 형식의 이메일을 입력했습니다.'
					  false
					: '<i class="icon ion-md-close-circle"></i> 이메일이 올바르지 않습니다.';
			return name;
		},
		changePasswordColor() {
			let color =
				this.signupData.password.length === 0
					? 'black'
					: this.isValidPassword
					? 'green'
					: 'red';
			return color;
		},
		changePasswordMessage() {
			let name =
				this.signupData.password.length === 0
					? false
					: this.isValidPassword
					? // '올바른 형식의 비밀번호를 입력했습니다.'
					  false
					: '<i class="icon ion-md-close-circle"></i> 비밀번호는 특수문자 포함 8-15자입니다.';
			return name;
		},
		changePasswordConfirmColor() {
			let color =
				this.signupData.password2.length === 0
					? 'black'
					: this.signupData.password === this.signupData.password2 &&
					  this.isValidPassword
					? 'green'
					: 'red';
			return color;
		},
		changePasswordConfirmMessage() {
			let name =
				this.signupData.password2.length === 0
					? false
					: this.signupData.password === this.signupData.password2
					? // '비밀번호가 일치합니다.'
					  false
					: '<i class="icon ion-md-close-circle"></i> 비밀번호가 일치하지 않습니다.';
			return name;
		},
	},
	methods: {
		...mapActions(['SIGNUP']),
		async submitForm() {
			try {
				console.log(this.signupData);
				// LOGIN 액션함수에 await를 걸지 않으면 로그인 처리되기 전에 라우터 이동이 진행됌
				await this.SIGNUP(this.signupData);
				this.$router.push({ name: 'main' });
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		onChangePassword(val) {
			this.signupData.password = val;
		},
		onChangePasswordConfirm(val) {
			this.signupData.password2 = val;
		},
		onChangeName(val) {
			this.signupData.name = val;
		},
		onChangeEmail(val) {
			this.signupData.email = val;
		},
	},
};
</script>

<style lang="scss" scoped>
.signup-btn {
	width: 100%;
	text-align: center;
	button {
		@include form-btn('black');
		@include scale(width, 400px);
		height: 3.5rem;
		margin-top: 1rem;
		font-size: $font-bold;
	}
	.signup-btn-disabled {
		background-color: grey;
		&:hover {
			background: grey;
		}
	}
}
.signup-form {
	display: flex;
	flex-direction: column;
	align-items: center;
}
</style>
