<template>
	<div class="footer">
		<p class="copy"><span>copyright</span> &copy; SwitOn</p>
		<p class="team">김태인 성예희 최재훈 홍연주 황영준</p>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.footer {
	display: grid;
	place-items: center;
	// width: 70%;
	// margin: 0 auto;
	padding: 7px 0;
	// border-top: 1px solid gray;
	p {
		color: gray;
		font-size: $font-light;
	}
	.copy {
		span {
			font-weight: bold;
		}
	}
}
</style>
