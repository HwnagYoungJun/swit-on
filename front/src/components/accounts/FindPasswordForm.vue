<template>
	<form
		class="findpassword-form"
		autocomplete="off"
		@submit.prevent="sendEmail"
	>
		<label>Email</label>
		<input type="email" name="toEmail" v-model="findPasswordData.email" />
		<div class="findpassword-btn">
			<button v-if="isVaildEmail" class="buttonOn" type="submit">
				비밀번호 찾기
			</button>
			<button v-else :disabled="!isVaildEmail" class="buttonOff" type="submit">
				비밀번호 찾기
			</button>
		</div>
	</form>
</template>

<script>
import bus from '@/utils/bus.js';
import { validateEmail } from '@/utils/validation';
import emailjs from 'emailjs-com';

export default {
	data() {
		return {
			findPasswordData: {
				email: '',
			},
			sendEmailData: {
				SERVICE_ID: 'gmail',
				TEMPLATE_ID: process.env.VUE_APP_TEMPLATE_ID,
				USER_ID: process.env.VUE_APP_USER_ID,
			},
			template_params: {
				name: 'name_value',
			},
		};
	},
	computed: {
		isVaildEmail() {
			return validateEmail(this.findPasswordData.email);
		},
	},
	methods: {
		test(e) {
			console.log(e.target);
		},
		async sendEmail(e) {
			try {
				await emailjs.sendForm(
					this.sendEmailData.SERVICE_ID,
					this.sendEmailData.TEMPLATE_ID,
					e.target,
					this.sendEmailData.USER_ID,
				);
				this.$router.push({ name: 'sendemail' });
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		inputEmailChange(val) {
			this.findPasswordData.email = val;
		},
	},
};
</script>
<style lang="scss" scoped>
.findpassword-form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.findpassword-btn {
	width: 100%;
	text-align: center;
	button {
		border-radius: 4px;
		width: 400px;
		height: 62px;
		margin: 20px 0px;
		padding: 20px 24px 20px 24px;
		font-size: 22px;
		font-weight: 700;
		line-height: 22px;
		text-decoration: none solid #000;
		color: white;
	}
	.buttonOn {
		background-color: black;
	}
	.buttonOff {
		background-color: gray;
	}
}
</style>
