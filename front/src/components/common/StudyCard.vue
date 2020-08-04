<template>
	<div class="card">
		<div class="img-box">
			<img src="@/assets/cloud.png/" alt="" />
		</div>
		<div class="etc-box">
			<div>
				<h3>{{ studyData.title }}</h3>
			</div>
			<div>
				<span>{{ studyData.posPeople }} / {{ studyData.maxPeople }}명</span>
			</div>
			<div>
				<p>{{ studyData.week }}</p>
				<p>{{ studyData.time }}</p>
				<p class="expireDay">{{ studyData.expireDay }} 까지</p>
			</div>
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
				time: '8:00 ~ 9:00',
				week: '매주 수, 일',
				expireDay: '20.09.01',
				posPeople: 5,
				maxPeople: 10,
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

	.img-box {
		overflow: hidden;
		width: 100%;
		padding-bottom: 100%;
		position: relative;
		img {
			position: absolute;
			width: 100%;
			height: 100%;
			margin: 0;
			transform: scale(1);
			transition: 0.3s ease-in-out;
		}
		&:hover img {
			transform: scale(1.1);
			cursor: pointer;
		}
	}
	.etc-box {
		margin-top: 1rem;
		padding: 0 1rem;
		p {
			font-size: $font-normal;
		}
		.expireDay {
			display: inline;
			padding: 0.2rem;
			border-radius: 5px;
			background: rgba(0, 0, 0, 0.025);
			color: rgb(199, 27, 199);
			font-size: $font-light;
		}
	}
}
</style>
