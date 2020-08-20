<template>
	<section class="toast" :class="toastAnimationClass">
		해당 글을 삭제하시겠습니까 ?
		<section>
			<button class="toast-btn-white" @click="open = false">취소</button>
			<button class="toast-btn-purple" @click="showToast">삭제</button>
		</section>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
import { deleteArticle } from '@/api/articles';
export default {
	data() {
		return {
			open: false,
			message: '',
			articleInfo: null,
		};
	},
	computed: {
		toastAnimationClass() {
			return this.open ? null : 'none';
		},
	},
	methods: {
		openMethod(articleInfo) {
			this.articleInfo = articleInfo;
			this.open = true;
		},
		async showToast() {
			const { studyId, boardName, articleId } = this.articleInfo;
			await deleteArticle(studyId, boardName, articleId);
			this.open = false;
			this.$router.push(`/study/${studyId}/${boardName}`);
		},
	},
	created() {
		bus.$on('show:delete', this.openMethod);
	},
	beforeDestroy() {
		bus.$off('show:delete', this.openMethod);
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
	width: 400px;
	@media screen and (max-width: 640px) {
		width: 300px;
	}
	height: 8rem;
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
}
.toast.none {
	display: none;
}
.toast-btn-white {
	border: none;
	border-radius: 3px;
	padding: 0 1rem;
	font-size: 1rem;
	font-weight: 700;
	background: white;
	color: black;
	height: 2rem;
	margin-top: 1.5rem;
	margin-right: 0.5rem;
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
