<template>
	<div>
		<ul class="study-box">
			<li class="study-li">
				<StudyCard></StudyCard>
			</li>
			<li class="study-li">
				<StudyCard></StudyCard>
			</li>
			<li class="study-li">
				<StudyCard></StudyCard>
			</li>
			<li class="study-li">
				<StudyCard></StudyCard>
			</li>
		</ul>
	</div>
</template>

<script>
import StudyCard from '@/components/common/StudyCard.vue';
export default {
	components: {
		StudyCard,
	},
	data() {
		return {
			testData: null,
		};
	},
};
</script>

<style lang="scss" scoped>
.study-box {
	display: flex;
	justify-content: space-between;
	width: 100%;
	flex-wrap: wrap;
	.study-li {
		width: 20%;
		overflow: hidden;
	}
}
</style>
