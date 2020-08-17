<template>
	<div class="upperKing" @click="goToTop">
		<div class="upper-container">
			<div class="upper-wraper"></div>
			<i class="icon ion-md-arrow-round-up"></i>
		</div>
	</div>
</template>

<script>
export default {
	methods: {
		goToTop() {
			document.documentElement.scrollTop = 0;
		},
	},
};
</script>

<style lang="scss" scoped>
.upperKing {
	position: fixed;
	bottom: 3rem;
	right: 3rem;
	@media screen and (max-width: 768px) {
		display: none;
	}
	&:hover {
		cursor: pointer;
	}
	.upper-container {
		z-index: 999999997;
		border-radius: 50%;
		width: 40px;
		height: 40px;
		position: relative;
		background: #3b00c2;
		box-shadow: 2px 2px 5px 1px rgba(175, 175, 175, 0.43);
		i {
			position: absolute;
			top: 50%;
			left: 50%;
			color: #3b00c2;
			transform: translate(-50%, -50%);
			font-size: 20px;
			z-index: 999999999;
		}
		.upper-wraper {
			z-index: 999999998;
			border-radius: 50%;
			width: 70%;
			height: 70%;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.23);
			background: white;
		}
	}
}
</style>
