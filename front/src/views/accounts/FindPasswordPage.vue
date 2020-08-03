<template>
	<div class="container">
		<h1>비밀번호 찾기</h1>
		<h4>이메일 주소를 입력하면 비밀번호를 이메일로 보내드릴게요.</h4>
		<FindPasswordForm></FindPasswordForm>
	</div>
</template>

<script>
import FindPasswordForm from '@/components/accounts/FindPasswordForm.vue';
export default {
	name: 'FindPasswordPage',
	components: {
		FindPasswordForm,
	},
};
</script>

<style>
h1 {
	font-style: Arial;
}
h4 {
	font-style: Arial;
}
@media screen and (max-width: 768px) {
	h1 {
		text-align: left;
	}
	h4 {
		text-align: left;
	}
}
</style>
