<template>
	<section class="popular-item">
		<div class="popular-img">
			<img :src="studyImg" :alt="`${study.name} 스터디 사진`" />
			<p class="popular-users-cnt">
				<span>{{ study.users_current }} / {{ study.users_limit }}</span>
			</p>
		</div>
		<section class="popular-content">
			<h3 class="content-title" tabindex="0">{{ study.name }}</h3>
			<p class="content-week">
				<time class="content-day" tabindex="0">{{
					study.week | formatWeekday
				}}</time>
			</p>
			<time class="content-time" tabindex="0"
				>{{ study.start_time }}-{{ study.end_time }}</time
			>
		</section>
	</section>
</template>

<script>
export default {
	props: {
		study: Object,
		colorPick: String,
	},
	computed: {
		studyImg() {
			if (this.study.logo) {
				return `${process.env.VUE_APP_API_URL}${this.study.logo}`;
			} else {
				return `${process.env.VUE_APP_API_URL}upload/noStudy.jpg`;
			}
		},
	},
};
</script>

<style lang="scss">
.popular-title {
	color: white;
	font-size: $font-normal;
	position: absolute;
	top: -20px;
	left: 40px;
}
.popular-item {
	display: grid;
	width: 100%;
	height: 100%;
	grid-template-columns: minmax(10rem, 1fr);
	grid-template-rows: minmax(3.5rem, 1fr) minmax(6rem, 2fr);
	grid-template-areas:
		'text-part'
		'image-part';
	border: 1px solid transparent;
	position: relative;
	margin-top: 1rem;
	padding: 0.3rem 0.5rem 0.5rem;
	color: #454545;
	box-shadow: 2px 2px 5px rgba(139, 139, 139, 0.5);
	.popular-img {
		grid-area: image-part;
		overflow: hidden;
		position: relative;
		cursor: pointer;
		.popular-users-cnt {
			width: 100%;
			height: 40px;
			display: flex;
			align-items: flex-end;
			justify-content: flex-end;
			position: absolute;
			bottom: 0;
			padding: 0 10px 3px 0;
			color: #fff;
			background: linear-gradient(
				to bottom,
				transparent,
				55%,
				rgba(0, 0, 0, 0.5)
			);
		}
	}
	.popular-content {
		grid-area: text-part;
		color: #495057;
		.content-category {
			padding-bottom: 0.3rem;
			border-bottom: 1px solid #fff;
		}
		.content-title {
			font-size: $font-bold * 0.85;
			padding-top: 0.3rem;
			padding-bottom: 0.3rem;
		}
		.content-week {
			.content-day {
				font-size: $font-light;
				color: rgba(77, 77, 77, 0.8);
			}
		}
	}
	.temp {
		position: absolute;
		bottom: 1rem;
		right: 1rem;
		color: white;
	}
	img {
		width: 100%;
		height: 100%;
		object-fit: fill;
		transition: all 0.3s ease;
		&:hover {
			transform: scale(1.1);
		}
	}
}
</style>
