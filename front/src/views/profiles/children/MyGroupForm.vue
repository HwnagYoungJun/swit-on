<template>
	<div class="container">
		<div class="temp">
			<div class="group-container">
				<div :key="study.id" v-for="study in studies">
					<GroupCard :study="study" />
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
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
			try {
				const { data } = await fetchMyStudy(this.userName);
				data.forEach(el => this.studies.push(el.study));
				console.log(this.studies);
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
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
	width: 100%;
	height: 100%;
}
.group-container {
	display: grid;
	width: 100%;
	gap: 1.5rem;
	grid-template-columns: repeat(4, 1fr);
	grid-template-rows: 1fr;
}
@media screen and (max-width: 1024px) {
	.group-container {
		grid-template-columns: repeat(3, 1fr);
	}
}
@media screen and (max-width: 768px) {
	.group-container {
		grid-template-columns: repeat(2, 1fr);
	}
}
</style>
