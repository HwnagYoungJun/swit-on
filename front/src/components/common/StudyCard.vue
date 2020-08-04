<template>
	<div class="card">
		<div class="img-box">
			<img src="@/assets/black.png/" alt="" />
		</div>
		<div>
			<h3>{{ studyData.title }}</h3>
		</div>
		<div>
			<router-link
				:to="{
					name: 'categorydetail',
					params: { UpperCategoryName: studyData.upperCategory },
				}"
				>{{ studyData.upperCategory }}</router-link
			>
			<span> > {{ studyData.lowerCategory }}</span>
		</div>
		<div>
			<p>{{ studyData.week }} {{ studyData.time }}</p>
			<p class="expireDay">{{ studyData.expireDay }} 까지</p>
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
				week: '수, 일',
				expireDay: '20.09.01',
				upperCategory: '프로그래밍 언어',
				lowerCategory: 'Python',
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
		margin-top: 1rem;
		overflow: hidden;

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
	p {
		font-size: $font-normal;
	}
	.expireDay {
		display: inline;
		padding: 0.2rem;
		border-radius: 5px;
		background: rgba(0, 0, 0, 0.1);
		color: #f45340;
		font-size: xx-small;
	}
}
</style>
