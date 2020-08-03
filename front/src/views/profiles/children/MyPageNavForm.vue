<template>
	<div class="el-box">
		<router-link :to="{ name: 'myschedule' }" class="element">{{
			el.el1
		}}</router-link>
		<router-link :to="{ name: 'mygroup' }" class="element">{{
			el.el2
		}}</router-link>
		<router-link :to="{ name: 'mystorage' }" class="element">{{
			el.el3
		}}</router-link>
		<router-link :to="{ name: 'myarticle' }" class="element">{{
			el.el4
		}}</router-link>
		<hr />
	</div>
</template>

<script>
export default {
	data() {
		return {
			el: {
				el1: '나의 일정',
				el2: '나의 모임',
				el3: '나의 저장소',
				el4: '나의 글',
			},
		};
	},
};
</script>

<style lang="scss" scoped>
.el-box {
	display: flex;
	margin-bottom: 2rem;
	.element {
		text-decoration: none;
		color: black;
		display: flex;
		margin-right: 1.25rem;
		margin-left: 0.7rem;
		border-bottom: 3px solid transparent;
	}
	.element:focus {
		border-bottom: 3px solid green;
	}
	.element:hover {
		border-bottom: 2px solid black;
	}
}
</style>
