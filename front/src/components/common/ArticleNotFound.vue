<template>
	<section class="article-not-found">
		<p>작성된 게시글이 없어요 :(</p>
	</section>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.article-not-found {
	width: 100%;
	height: 200px;
	display: grid;
	place-items: center;
	position: relative;
	p {
		color: rgb(100, 100, 100);
		font-weight: bold;
	}
}
</style>
