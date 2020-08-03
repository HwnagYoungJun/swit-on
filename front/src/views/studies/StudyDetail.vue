<template>
	<div>
		<nav aria-label="Breadcrumb" class="breadcrumb">
			<ol>
				<li>
					<a href="#">프로그래밍 언어</a><span aria-hidden="true">></span>
				</li>
				<li><a href="#" aria-current="page">python 소모임</a></li>
			</ol>
		</nav>
		<div class="study-description">
			<div class="study-logo">
				<img src="@/assets/django.png" alt="" />
			</div>
			<div class="study-content">
				<p>python 소모임</p>
				<!-- <p>모집: 20.07.02-20.07.31</p>
				<p>활동: 매주 화 목 9시-11시</p>
				<small>5/8명</small> -->
			</div>
		</div>
		<div class="study-category">
			<router-link :to="`/study/${1}`">대시보드<span></span></router-link>
			<router-link :to="{ name: 'calendar' }">캘린더<span></span></router-link>
			<router-link :to="{ name: 'repository' }"
				>저장소<span></span
			></router-link>
			<router-link :to="{ name: 'notice' }">공지<span></span></router-link>
			<router-link :to="{ name: 'question' }">Q&A<span></span></router-link>
			<hr />
			<div class="study-sub-content">
				<router-view></router-view>
			</div>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.breadcrumb {
	ol {
		padding: 0;
	}
	li {
		display: inline;
	}
	a {
		color: rgb(136, 136, 136);
		font-size: $font-light;
		text-decoration: none;
	}
	span {
		margin: 5px;
	}
}
.study-description {
	display: flex;
	margin-bottom: 15px;
	padding: 2%;
	background: rgb(29, 29, 29);
	color: #fff;
}
.study-logo {
	flex: 1.5;
}
.study-logo img {
	width: 100%;
}
.study-content {
	flex: 2;
	margin: 0 30px;
}
.study-category a {
	display: inline-block;
	margin: 10px;
	margin-bottom: 7px;
	color: rgb(44, 44, 44);
	text-decoration: none;
	position: relative;
}
.study-category a.router-link-exact-active span {
	width: 100%;
	height: 8px;
	position: absolute;
	bottom: -4px;
	left: 0;
	border-radius: 2px;
	background: $btn-purple;
	opacity: 0.5;
}
.study-category hr {
	margin: 0;
	border: none;
	border-bottom: 1px solid rgb(228, 228, 228);
}
@media screen and (max-width: 768px) {
	.study-logo {
		display: grid;
		place-items: center;
		flex: 0.5;
	}
	.study-logo img {
		width: 50px;
		height: 50px;
		border-radius: 50%;
	}
}
</style>
