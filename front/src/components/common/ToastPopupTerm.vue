<template>
	<section class="toast" :class="toastAnimationClass">
		<section class="title">약관</section>
		<section class="body">
			제1조 (목적)이 약관은 SwitOn(이하 당사)가 제공하는 스터디 시스템을
			이용함에 있어 당사와 이용자의 권리, 의무 및 책임사항을 규정합니다. 이를
			통하여 당사와 이용자는 알아야 할 사항을 숙지, 상호 신뢰의 증진을 목적으로
			합니다.
		</section>
		<section>
			<button class="toast-btn-purple" @click="showToast">확인</button>
		</section>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
export default {
	data() {
		return {
			open: false,
		};
	},
	computed: {
		toastAnimationClass() {
			return this.open ? null : 'none';
		},
	},
	methods: {
		openMethod() {
			this.open = true;
		},
		showToast() {
			this.open = false;
		},
	},
	created() {
		bus.$on('show:term', this.openMethod);
	},
	beforeDestroy() {
		bus.$off('show:term', this.openMethod);
	},
	watch: {
		$route() {
			this.open = false;
		},
	},
};
</script>

<style lang="scss" scoped>
.toast {
	position: fixed;
	width: 600px;
	padding: 5%;
	@media screen and (max-width: 640px) {
		width: 400px;
	}
	@media screen and (max-width: 400px) {
		width: 275px;
	}
	height: 20em;
	background-color: #22252e;
	border-radius: 4px;
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.2);
	color: white;
	top: 50%;
	left: 50%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	transform: translate(-50%, -50%);
	.title {
		margin-bottom: 1rem;
		font-size: $font-bold;
	}
}
.toast.none {
	display: none;
}
.toast-btn-purple {
	border: none;
	border-radius: 3px;
	padding: 0 1rem;
	font-size: 1rem;
	font-weight: 700;
	background: $btn-purple;
	color: white;
	height: 2rem;
	margin-top: 1.5rem;
	margin-left: 0.5rem;
}
</style>
