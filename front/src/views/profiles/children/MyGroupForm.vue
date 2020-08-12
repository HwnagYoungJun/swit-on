<template>
	<div class="container">
		<div class="temp">
			<div class="group-container" :key="study.id" v-for="study in studies">
				<GroupCard :study="study" />
			</div>
		</div>
	</div>
</template>

<script>
import GroupCard from '@/components/group/GroupCard.vue';
import { fetchMyStudy } from '@/api/auth';
export default {
	components: { GroupCard },
	data() {
		return {
			studies: [],
		};
	},
	props: {
		userName: {
			type: String,
			required: true,
		},
	},
	methods: {
		async fetchMyStudy() {
			const { data } = await fetchMyStudy(this.userName);
			data.forEach(el => this.studies.push(el.study));
			console.log(this.studies);
		},
	},
	created() {
		this.fetchMyStudy();
	},
};
</script>

<style lang="scss" scoped>
.temp {
	display: flex;
	justify-content: center;
}
.group-container {
	display: grid;
	width: 100%;
	height: 100%;
	gap: 1.5rem;
	grid-template-columns: repeat(4, 1fr);
	grid-template-rows: 1fr;
}
@media screen and (max-width: 768px) {
	.group-container {
		grid-template-columns: repeat(3, 1fr);
	}
}
@media screen and (max-width: 320px) {
	.group-container {
		grid-template-columns: repeat(2, 1fr);
	}
}
</style>
