<template>
	<article>
		<p class="email-success">{{ msg }}</p>
	</article>
</template>

<script>
export default {
	data() {
		return {
			msg: '이메일을 보내드렸어요!',
		};
	},
};
</script>

<style lang="scss" scoped></style>
