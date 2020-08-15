<template>
	<router-link :to="`/study/${study.id}`">
		<div class="group-box">
			<div class="img-box">
				<img :src="imgLink" alt="imgLink" />
			</div>
			<p class="groupName">{{ study.name }}</p>
		</div>
	</router-link>
</template>

<script>
export default {
	data() {
		return {};
	},
	props: {
		study: Object,
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
	grid-template-rows: 12rem 4rem;
	grid-template-areas:
		'image-part'
		'text-part';
	text-align: center;
	font-size: $font-bold;

	.img-box {
		// overflow: hidden;
		border-radius: 5px;
		grid-area: image-part;
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
	}
	hr {
		border-bottom: 1px;
	}
	&:hover img {
		transform: scale(1.1);
		cursor: pointer;
	}
}
</style>
