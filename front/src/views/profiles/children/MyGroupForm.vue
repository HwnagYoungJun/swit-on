<template>
	<div class="container">
		<div class="temp">
			<div class="group-container">
				<GroupCard></GroupCard>
				<GroupCard></GroupCard>
				<GroupCard></GroupCard>
				<GroupCard></GroupCard>
				<GroupCard></GroupCard>
			</div>
		</div>
	</div>
</template>

<script>
import GroupCard from '@/components/group/GroupCard.vue';
export default {
	components: { GroupCard },
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
