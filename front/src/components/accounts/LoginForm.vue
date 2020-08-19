<template>
	<form class="login-form" autocomplete="off" @submit.prevent="submitForm">
		<InputBox
			type="email"
			userInfo="email"
			title="이메일"
			:class="!isButtonabled ? 'login-btn-disabled' : ''"
			text="이메일을 입력하세요"
			:isSave="isCookie ? true : false"
			:inputChange="onChangeEmail"
		></InputBox>
		<InputBox
			type="password"
			userInfo="password"
			title="비밀번호"
			text="비밀번호를 입력하세요"
			:inputChange="onChangePassword"
		></InputBox>
		<div class="idMaintain">
			<input v-model="checked" type="checkbox" id="idMaintain" />
			<label class="idMaintainLabel" for="idMaintain">Email 저장</label>
		</div>

		<div class="sf-box">
			<router-link :to="{ name: 'signUp' }" class="signup">
				회원가입
			</router-link>
			<router-link :to="{ name: 'findpassword' }" class="findPassword">
				비밀번호를 잊으셨나요?
			</router-link>
		</div>
		<div class="login-btn">
			<button
				:disabled="!isButtonabled"
				:class="!isButtonabled ? 'login-btn-disabled' : ''"
				type="submit"
			>
				로그인
			</button>
		</div>
		<hr />
	</form>
</template>

<script>
import bus from '@/utils/bus.js';
import InputBox from '@/components/common/InputBox.vue';
import { mapActions, mapGetters, mapState } from 'vuex';
import { validateEmail } from '@/utils/validation';
import cookies from 'vue-cookies';

export default {
	components: {
		InputBox,
	},
	data() {
		return {
			...mapGetters(['isLogin']),
			...mapState(['email']),
			checked: cookies.isKey('id') ? true : false,
			loginData: {
				email: cookies.isKey('id') ? cookies.get('id') : null,
				password: null,
			},
		};
	},
	computed: {
		btnAble() {
			return !!this.loginData.password && this.isValidEmail;
		},
		isValidEmail() {
			return validateEmail(this.loginData.email);
		},
		isPassword() {
			return this.loginData.password ? true : false;
		},
		isButtonabled() {
			return this.isValidEmail && this.isPassword;
		},
		isCookie() {
			return cookies.isKey('id') ? true : false;
		},
		apiKey() {
			return process.env.VUE_APP_KAKAO_KEY;
		},
	},
	methods: {
		...mapActions(['LOGIN']),
		async submitForm() {
			try {
				// LOGIN 액션함수에 await를 걸지 않으면 로그인 처리되기 전에 라우터 이동이 진행됌
				await this.LOGIN(this.loginData);
				this.testCookie();
				this.$router.push({ name: 'main' });
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		onSuccess(data) {
			console.log(data);
			console.log('success');
		},
		onFailure(data) {
			console.log(data);
			console.log('failure');
		},

		setCookie(name, vaule, days) {
			var exDate = new Date();
			exDate.setDate(exDate.getDate() + days);
			var cookieValue =
				escape(vaule) +
				(days == null ? '' : '; expires=' + exDate.toUTCString());
			document.cookie = name + '=' + cookieValue;
		},

		getCookie(name) {
			var x, y;
			var val = document.cookie.split(';');
			for (var i = 0; i < val.length; i++) {
				x = val[i].substr(0, val[i].indexOf('='));
				y = val[i].substr(val[i].indexOf('=') + 1);
				x = x.replace(/^\s+|\s+$/g, '');
				if (x == name) {
					return unescape(y);
				}
			}
		},

		testCookie() {
			if (this.checked) {
				this.setCookie('id', this.loginData.email, 14);
			} else {
				this.setCookie('id', this.loginData.email, 0);
			}
		},
		onChangePassword(val) {
			this.loginData.password = val;
		},
		onChangeEmail(val) {
			this.loginData.email = val;
		},
	},
};
</script>

<style lang="scss" scoped>
.login-btn {
	width: 100%;
	text-align: center;
	button {
		@include form-btn('black');
		@include scale(width, 400px);
		height: 3.5rem;
		margin-top: 1rem;
		font-size: $font-bold;
	}
	.login-btn-disabled {
		background-color: grey;
		&:hover {
			background: grey;
		}
	}
}
.login-form {
	display: flex;
	flex-direction: column;
	align-items: center;
}
.idMaintain {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	margin: 2% auto;
	@include scale(width, 400px);
	font-size: 1rem;
	color: gray;
}
.sf-box {
	display: flex;
	justify-content: space-between;
	@include scale(width, 400px);
	margin: 2% auto 0;
	padding: 0;
}
.signup {
	text-decoration: none;
	font-size: 1rem;
	color: $btn-purple;
	margin-top: 0;
	margin-bottom: 0;
}
.findPassword {
	text-decoration: none;
	font-size: 1rem;
	color: $btn-purple;
	margin-top: 0;
	margin-bottom: 0;
}
@media (max-width: 640px) {
	.sf-box {
		display: flex;
		justify-content: space-between;
		margin: 2% auto 0;
		padding: 0;
	}
	.idMaintain {
		display: flex;
		justify-content: right;
		margin: 2% auto;
		font-size: $font-normal;
		color: gray;
	}
}
</style>
