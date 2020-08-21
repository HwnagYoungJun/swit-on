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
		<div class="term-box">
			<span class="lookterm" @click="lookTerm">약관보기</span>
			<div><input type="checkbox" v-model="isCheck" />약관 동의</div>
		</div>
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
import { ValidEmail, ValidName } from '@/api/auth';
import { mapActions } from 'vuex';
import {
	validateEmail,
	validatePassword,
	validateName,
} from '@/utils/validation';
export default {
	components: {
		InputBox,
	},
	data() {
		return {
			isCheck: false,
			signupData: {
				email: '',
				name: '',
				password: '',
				password2: '',
			},
			isEmailValid: '',
			nameErrorMessage: '',
			emailErrorMessage: '',
			isNameValid: '',
			// termData: '제1조 (목적)이 약관은 SwitOn(이하 당사)가 제공하는 수업 연결 서비스를 이용함에 있어 당사와 이용자의 권리, 의무 및 책임사항을 규정합니다. 이를 통하여 당사와 이용자는 알아야 할 사항을 숙지, 상호 신뢰의 증진을 목적으로 합니다.',
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
				!this.signupData.name ||
				!this.isCheck
			);
		},
		changeNameColor() {
			let color =
				this.signupData.name.length === 0
					? 'black'
					: this.signupData.name.length > 1 &&
					  this.signupData.name.length < 7 &&
					  validateName(this.signupData.name)
					? 'green'
					: 'red';
			if (this.isNameValid === 'red') {
				color = 'red';
			} else if (this.isNameValid === 'green') {
				color = 'green';
			}
			return color;
		},
		changeNameMessage() {
			let name =
				this.changeNameColor === 'black'
					? false
					: this.changeNameColor === 'green'
					? // '올바른 형식의 이름을 입력했습니다.'
					  false
					: this.changeNameColor === 'red' && this.isNameValid === 'red'
					? '<i class="icon ion-md-close-circle" aria-hidden="true"></i> 이름이 중복되었습니다'
					: '<i class="icon ion-md-close-circle" aria-hidden="true"></i> 이름은 특수문자 제외 2-6자입니다.';
			return name;
		},
		changeEmailColor() {
			let color =
				this.signupData.email.length === 0
					? 'black'
					: this.isValidEmail
					? 'green'
					: 'red';
			if (this.isEmailValid === 'red') {
				color = 'red';
			} else if (this.isEmailValid === 'green') {
				color = 'green';
			}

			return color;
		},
		changeEmailMessage() {
			let name =
				this.changeEmailColor === 'black'
					? false
					: this.changeEmailColor === 'green'
					? // '올바른 형식의 이메일을 입력했습니다.'
					  false
					: this.changeEmailColor === 'red' && this.isEmailValid === 'red'
					? '<i class="icon ion-md-close-circle" aria-hidden="true"></i> 이메일이 중복되었습니다'
					: '<i class="icon ion-md-close-circle" aria-hidden="true"></i> 이메일을 형식을 유지해주세요! ';
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
					: '<i class="icon ion-md-close-circle" aria-hidden="true"></i> 비밀번호는 특수문자 포함 8-15자입니다.';
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
					: '<i class="icon ion-md-close-circle" aria-hidden="true"></i> 비밀번호가 일치하지 않습니다.';
			return name;
		},
	},
	methods: {
		...mapActions(['SIGNUP']),
		async submitForm() {
			try {
				// LOGIN 액션함수에 await를 걸지 않으면 로그인 처리되기 전에 라우터 이동이 진행됌
				await this.SIGNUP(this.signupData);
				this.$router.push({ name: 'main' });
			} catch (error) {
				bus.$emit('show:toast', error.response.data.msg);
			}
		},
		async checkName() {
			const { data } = await ValidName(this.signupData.name);
			if (data) {
				this.isNameValid = 'green';
				return;
			}
			this.isNameValid = 'red';
		},
		async checkEmail() {
			const { data } = await ValidEmail(this.signupData.email);
			if (data) {
				this.isEmailValid = 'green';
				return;
			}
			this.isEmailValid = 'red';
		},
		onChangePassword(val) {
			this.signupData.password = val;
		},
		onChangePasswordConfirm(val) {
			this.signupData.password2 = val;
		},
		onChangeName(val) {
			this.signupData.name = val;
			if (this.signupData.name.length > 1) {
				this.checkName();
			}
		},
		onChangeEmail(val) {
			this.signupData.email = val;
			this.checkEmail();
		},
		lookTerm() {
			bus.$emit('show:term');
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
.term-box {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 100%;
	input {
		margin-right: 0.33rem;
	}
}
.signup-form {
	display: flex;
	flex-direction: column;
	align-items: center;
	position: relative;
	.term-modal {
		background: pink;
		z-index: 1;
		position: absolute;
		top: 50%;
		left: 50%;
		padding: 0;
		transform: translate(-50%, -50%);
		width: 50%;
		height: 50%;
		.term-modal-box {
			width: 100%;
			height: 100%;
			position: relative;
			i {
				font-size: $font-bold;
				position: absolute;
				right: 0;
				top: 0;
				z-index: 2;
			}
		}
	}
}
.lookterm {
	&:hover {
		cursor: pointer;
	}
}
</style>
