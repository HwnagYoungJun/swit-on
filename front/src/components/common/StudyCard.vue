<template>
	<div class="card">
		<div class="img-box">
			<img src="@/assets/dd.png/" alt="" />
		</div>
		<div>
			<h4>{{ studyData.title }}</h4>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			studyData: {
				// 난중에는 서버에서 받아올 데이터들
				title: 'python정복',
			},
		};
	},
};
</script>

<style lang="scss" scoped>
.card {
	display: flex;
	flex-direction: column;
	width: 100%;
	margin-bottom: 1rem;
	position: relative;

	.img-box {
		overflow: hidden;
		border: 1px solid red;

		img {
			width: 100%;
			transform: scale(1);
			transition: 0.3s ease-in-out;
		}
		&:hover img {
			transform: scale(1.1);
			cursor: pointer;
		}
	}
}
</style>
