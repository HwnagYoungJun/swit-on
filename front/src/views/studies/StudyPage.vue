<template>
	<div>
		<h1>Studies</h1>
		<div v-if="isLoading">
			Loading....
		</div>
		<ul v-else>
			<StudyListItem
				v-for="studyItem in studyItems"
				:studyItem="studyItem"
				:key="studyItem.id"
			></StudyListItem>
		</ul>
		<router-link :to="{ name: 'addstudy' }">+</router-link>
	</div>
</template>

<script>
import { fetchStudies } from '@/api/studies';
import StudyListItem from '@/components/studies/StudyListItem.vue';
export default {
	data() {
		return {
			studyItems: [],
			isLoading: false,
		};
	},
	components: {
		StudyListItem,
	},
	methods: {
		async fetchData() {
			this.isLoading = true;
			const { data } = await fetchStudies();
			this.isLoading = false;
			this.studyItems = data;
			console.log(this.studyItems);
		},
	},
	created() {
		this.fetchData();
	},
};
</script>

<style></style>
