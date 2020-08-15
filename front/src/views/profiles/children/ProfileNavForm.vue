<template>
	<div>
		<div class="el-box">
			<router-link :to="{ name: 'myschedule' }" class="element"
				>{{ el.el1 }}<span></span
			></router-link>
			<router-link :to="{ name: 'mygroup' }" class="element"
				>{{ el.el2 }}<span></span
			></router-link>
			<router-link v-if="isMe" :to="{ name: 'mystorage' }" class="element"
				>{{ el.el3 }}<span></span
			></router-link>
			<router-link v-if="isMe" :to="{ name: 'myarticle' }" class="element"
				>{{ el.el4 }}<span></span
			></router-link>
		</div>
		<hr />
	</div>
</template>

<script>
export default {
	props: {
		userName: String,
	},
	data() {
		return {
			el: {
				el1: '일정',
				el2: '모임',
				el3: '저장소',
				el4: '글',
			},
			name: this.$cookies.get('name'),
		};
	},
	computed: {
		isMe() {
			return this.$cookies.get('name') === this.userName;
		},
	},
};
</script>

<style lang="scss" scoped>
hr {
	margin-bottom: 2rem;
	border: none;
	border-bottom: 1px solid rgb(228, 228, 228);
}
.el-box {
	display: flex;
	margin-top: 2rem;
	@media screen and (max-width: 1024px) {
		margin-top: 1rem;
	}
	.element {
		text-decoration: none;
		color: black;
		display: flex;
		margin-right: 1.25rem;
		margin-left: 0.7rem;
		border-bottom: 3px solid transparent;
		position: relative;
	}
	a.router-link-exact-active span {
		width: 100%;
		height: 8px;
		position: absolute;
		bottom: -4px;
		left: 0;
		border-radius: 2px;
		background: $btn-purple;
		opacity: 0.5;
	}
}
</style>
