<template>
	<div class="group-box">
		<router-link :to="`/study/${study.id}`">
			<div class="img-box">
				<img :src="imgLink" alt="imgLink" />
			</div>
			<p class="groupName">{{ study.name }}</p>
		</router-link>
	</div>
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
				? `@/assets/logo.png`
				: `${this.baseUrl}${this.study.logo}`;
		},
	},
};
</script>

<style lang="scss" scoped>
.group-box {
	.img-box {
		overflow: hidden;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	img {
		width: 100%;
		height: 100%;
		transform: scale(1);
		transition: 0.3s ease-in-out;
	}
	hr {
		border-bottom: 1px;
	}
	p {
		text-decoration: none;
		color: black;
		text-align: center;
		font-size: $font-normal;
	}
	&:hover img {
		transform: scale(1.1);
		cursor: pointer;
	}
}
</style>
