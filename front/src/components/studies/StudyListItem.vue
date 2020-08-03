<template>
	<li>
		<div class="study-title">
			{{ studyItem.name }}
		</div>
		<div class="study-content">
			{{ studyItem.description }}
		</div>
		<div class="study-time">
			{{ studyItem.created_at }}
		</div>
		<!-- <button @click="updateStudy">modified</button> -->
		<button @click="deleteStudyItem">delete</button>
	</li>
</template>

<script>
import { deleteStudy } from '@/api/studies';
export default {
	props: {
		studyItem: {
			type: Object,
			// 값이 넘어오는지 체크
			required: true,
		},
	},
	methods: {
		async deleteStudyItem() {
			if (confirm('You want to delete it?')) {
				await deleteStudy(this.studyItem.id);
				this.$emit('refresh');
				console.log(`delete ${this.studyItem.id}`);
			}
		},
	},
};
</script>

<style></style>
