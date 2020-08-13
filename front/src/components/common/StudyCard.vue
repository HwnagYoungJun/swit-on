<template>
	<div class="card">
		<div class="img-box">
			<img :src="`${BaseUrl}${studyData.logo}`" alt="logo_img" />
		</div>
		<div class="etc-box">
			<div>
				<h3>{{ studyData.name }}</h3>
			</div>
			<div>
				<span>{{ studyData.posPeople }} / {{ studyData.maxPeople }}명</span>
			</div>
			<div>
				<p>{{ studyData.week }}</p>
				<p>{{ studyData.startTime }} ~ {{ studyData.endTime }}</p>
				<p class="expireDay">{{ studyData.expireDay }} 까지</p>
			</div>
		</div>
	</div>
</template>

<script>
import { formatWeekday } from '@/utils/filters';
export default {
	data() {
		return {
			studyData: {
				// 난중에는 서버에서 받아올 데이터들
				name: this.study.name,
				startTime: this.$props.study.start_time,
				endTime: this.$props.study.end_time,
				week: this.weekArray,
				expireDay: this.$props.study.end_term,
				posPeople: this.$props.study.users_current,
				maxPeople: this.$props.study.users_limit,
				logo: this.$props.study.logo,
			},
		};
	},
	props: {
		study: Object,
	},
	methods: {
		async fetchStudyData() {},
	},
	computed: {
		weekArray() {
			return formatWeekday(this.$props.study.week);
		},
		BaseUrl() {
			return process.env.VUE_APP_API_URL;
		},
	},
	created() {
		this.fetchStudyData();
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
