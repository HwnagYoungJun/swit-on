<template>
	<section>
		<StudyAddForm></StudyAddForm>
	</section>
</template>

<script>
import StudyAddForm from '@/components/studies/StudyAddForm.vue';
export default {
	components: {
		StudyAddForm,
	},
	mounted() {
		document.title = '스윗온 스터디 생성';
	},
};
</script>

<style></style>
