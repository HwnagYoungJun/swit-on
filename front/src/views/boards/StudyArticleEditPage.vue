<template>
	<section>
		<StudyArticleEditForm
			:id="id"
			:board_name="board_name"
			:article_id="article_id"
		></StudyArticleEditForm>
	</section>
</template>

<script>
import StudyArticleEditForm from '@/components/boards/StudyArticleEditForm.vue';
export default {
	props: {
		id: Number,
		board_name: String,
		article_id: Number,
	},
	components: {
		StudyArticleEditForm,
	},
};
</script>

<style></style>
