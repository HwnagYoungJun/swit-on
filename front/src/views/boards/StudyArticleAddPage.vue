<template>
	<section>
		<StudyArticleAddForm
			:id="id"
			:board_name="board_name"
		></StudyArticleAddForm>
	</section>
</template>

<script>
import StudyArticleAddForm from '@/components/boards/StudyArticleAddForm.vue';
export default {
	props: {
		id: Number,
		board_name: String,
	},
	components: {
		StudyArticleAddForm,
	},
};
</script>

<style></style>
