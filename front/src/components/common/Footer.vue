<template>
	<section class="footer">
		<p class="copy"><span>copyright</span> &copy; SwitOn</p>
		<p class="team">김태인 성예희 최재훈 홍연주 황영준</p>
	</section>
</template>

<script>
export default {};
</script>

<style lang="scss">
.footer {
	@media screen and (max-width: 768px) {
		margin-bottom: 37px;
	}
	display: grid;
	place-items: center;
	padding: 7px 0;
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
