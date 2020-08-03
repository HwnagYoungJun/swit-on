<template>
	<div>
		<h1>{{ msg }}</h1>
	</div>
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

<style scoped>
h1 {
	justify-content: left;
}
</style>
