<template>
	<div class="card-wrap">
		<ArticleCard />
		<ArticleCard />
		<ArticleCard />
	</div>
</template>

<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
export default {
	components: {
		ArticleCard,
	},
};
</script>
<style lang="scss" scoped>
.card-wrap {
	display: flex;
	flex-wrap: wrap;
	@media screen and (max-width: 1500px) {
		justify-content: center;
	}
}
</style>
