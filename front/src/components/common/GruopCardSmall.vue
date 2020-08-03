<template>
	<div class="group-box">
		<router-link :to="{ name: 'studydetail', params: { id: '1' } }">
			<div class="img-box">
				<img :src="imgLink" alt="imgLink" />
			</div>
		</router-link>
	</div>
</template>

<script>
export default {
	data() {
		return {
			// 여기에 에밋으로 내려줄꺼임
			studyName: 'Python 부셔버리기',
		};
	},
	computed: {
		imgLink() {
			return 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Python-logo-notext.svg/768px-Python-logo-notext.svg.png';
		},
	},
};
</script>

<style lang="scss" scoped>
.group-box {
	margin: 0;
	padding: 0;
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
