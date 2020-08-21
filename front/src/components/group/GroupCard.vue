<template>
	<div>
		<div v-if="!isEnd">
			<router-link :to="`/study/${study.id}`">
				<section class="group-box">
					<div class="img-box">
						<img :src="imgLink" :alt="`${study.name} 스터디 사진`" />
					</div>
					<div class="name-box">
						{{ study.name }}
					</div>
				</section>
			</router-link>
		</div>
		<div v-else>
			<section class="group-box">
				<div class="endStudy">
					<i
						class="icon ion-md-checkmark-circle-outline"
						aria-hidden="true"
					></i>
				</div>
				<div class="img-box">
					<img :src="imgLink" :alt="`${study.name} 스터디 사진`" />
					<div class="hoverData">
						<span class="img-text">
							참여율 {{ study.rate.participation * 100 }}%
						</span>
						<span class="img-text">
							출석률 {{ study.rate.attendance * 100 }}%
						</span>
					</div>
				</div>
				<div class="name-box">
					<span class="badge">
						<span class="rounded">
							<span class="icon ion-md-medal" aria-hidden="true"></span>
						</span>
					</span>
					{{ study.name }}
				</div>
			</section>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
	props: {
		study: Object,
		isEnd: Boolean,
	},
	computed: {
		baseUrl() {
			return process.env.VUE_APP_API_URL;
		},
		imgLink() {
			return this.study.logo === null
				? `${this.baseUrl}upload/noStudy.jpg`
				: `${this.baseUrl}${this.study.logo}`;
		},
	},
};
</script>

<style lang="scss">
.group-box {
	display: grid;
	grid-template-columns: 100%;
	grid-template-rows: 12rem 3rem;
	grid-template-areas:
		'image-part'
		'text-part';
	text-align: center;
	font-size: $font-bold * 0.8;
	.endStudy {
		background: rgba(0, 0, 0, 0.2);
		z-index: 10000;
		width: 100%;
		height: 100%;
		border-radius: 5px;
		grid-area: image-part;
		position: relative;
		i {
			position: absolute;
			top: -1.5rem;
			left: 0;
			color: #f03e3e;
			font-size: 40px;
		}
	}
	.img-box {
		border-radius: 5px;
		grid-area: image-part;
		overflow: hidden;
		position: relative;
		img {
			width: 100%;
			height: 100%;
			object-fit: fill;
			transform: scale(1);
			transition: 0.3s ease-in-out;
		}
		p {
			grid-area: text-part;
			text-decoration: none;
			color: rgb(70, 70, 70);
		}
		.hoverData {
			color: white;
			font-size: $font-bold * 0.85;
			z-index: 987654321;
			position: absolute;
			top: 50%;
			left: 50%;
			width: 100%;
			height: 100%;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			transform: translate(-50%, -50%);
		}
	}
	hr {
		border-bottom: 1px;
	}
	&:hover img {
		transform: scale(1.1);
		cursor: pointer;
	}
	.name-box {
		display: flex;
		justify-content: center;
		align-items: center;
		.badge {
			@include grade-badge('forEnd', 30px);
		}
	}
}
</style>
