<template>
	<div class="page-not-found-wrap">
		<h1 class="a11y-hidden">Page Not Found</h1>
		<img src="@/assets/pageNotFound.png" alt="PageNotFound" />
		<p>찾을수 없는 페이지 입니다.</p>
		<p>요청하신 페이지가 사라졌거나, 잘못된 경로를 이용하셨어요 :(</p>
		<button class="hide-btn">SwitOn</button>
		<button class="show-btn" @click="$router.go(-1)">SwitOn</button>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.page-not-found-wrap {
	width: 100%;
	height: 100%;
	display: grid;
	place-items: center;
	padding-top: 100px;
	position: relative;
	img {
		margin-bottom: 25px;
	}
	p {
		margin: 5px 0;
	}
	.hide-btn {
		border: none;
		border-radius: 3px;
		height: 40px;
		padding: 0 1.125rem;
		font-size: 1rem;
		font-weight: 700;
		text-decoration: none solid #000;
		background: #000;
		color: #fff;
		position: absolute;
		bottom: 20px;
	}
	.show-btn {
		margin: 20px;
		@include form-btn('purple');
	}
}
</style>
