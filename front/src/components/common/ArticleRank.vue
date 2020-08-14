<template>
	<div class="schedule">
		<span class="schedule-title">나의 일정</span>
		<ul></ul>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.schedule {
	margin: 50px 0;
	padding: 25px 0 15px;
	position: relative;
	color: rgb(138, 138, 138);
	.schedule-title {
		position: absolute;
		top: -5px;
		left: -15px;
		color: rgb(90, 90, 90);
		font-weight: bold;
		background: #fff;
	}
	li {
		display: flex;
		justify-content: space-between;
		margin: 10px 0;
		align-items: center;
		color: rgb(90, 90, 90);
		@media screen and (max-width: 370px) {
			display: inline;
			margin: 10px 20px;
		}
		span {
			margin-right: 10px;
		}
		button {
			@include common-btn();
			display: inline-block;
			width: 5rem;
			@media screen and (max-width: 370px) {
				width: 100%;
				margin: 8px 0;
			}
		}
		.active {
			color: #fff;
			background: $btn-purple;
		}
	}
}
</style>
