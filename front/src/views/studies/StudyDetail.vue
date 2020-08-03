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
			<div class="study-content">
				<p class="study-title">python 소모임</p>
				<p>모집: 20.07.02 - 20.07.31</p>
				<p>활동: 매주 화 목 9시-11시</p>
				<small>5/8명</small>
			</div>
			<div class="study-logo">
				<img src="@/assets/django.png" alt="" />
			</div>
		</div>
		<div v-if="isJoined" class="study-category">
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
		<div v-else class="study-sub-content">
			<p>python 소모임 소개</p>
			<p>
				Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum corrupti
				culpa ipsa et! Ab molestias veritatis numquam nihil quod iste. Labore
				placeat ullam, repellendus amet deserunt modi ipsam totam dolores.lorem
				Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsam eligendi
				sed esse accusantium quibusdam, molestiae error sequi, eius enim
				assumenda beatae ab fugiat. Quos rem eum alias. Ut, beatae quidem.
			</p>
			<button class="join-btn">가입하기</button>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			isJoined: false,
		};
	},
};
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
	margin-bottom: 30px;
	padding: 2%;
	color: rgb(107, 107, 107);
	box-shadow: 0 3px 6px rgb(214, 214, 214);
	border-radius: 4px;
	.study-logo {
		flex: 1.5;
		img {
			width: 100%;
		}
	}
	.study-content {
		flex: 2;
		margin: 0 30px;
		p {
			margin: 5px 0;
		}
		.study-title {
			margin-bottom: 10px;
			font-size: $font-bold;
		}
	}
}
.study-category {
	a {
		display: inline-block;
		margin: 10px;
		margin-bottom: 7px;
		color: rgb(44, 44, 44);
		text-decoration: none;
		position: relative;
	}
	a.router-link-exact-active span {
		width: 100%;
		height: 8px;
		position: absolute;
		bottom: -4px;
		left: 0;
		border-radius: 2px;
		background: $btn-purple;
		opacity: 0.5;
	}
	hr {
		margin: 0;
		border: none;
		border-bottom: 1px solid rgb(228, 228, 228);
	}
}
.study-sub-content {
	padding: 15px;
	position: relative;
	p:first-child {
		margin: 10px 0 30px;
		font-size: $font-bold;
	}
	.join-btn {
		display: inline-block;
		width: 150px;
		padding: 7px 0;
		border: 1px solid $main-color;
		border-radius: 30px;
		color: $main-color;
		background: none;
		position: absolute;
		top: 20px;
		right: 20px;
		&:hover {
			color: #fff;
			border: none;
			background: $btn-purple;
		}
		&:focus {
			outline: none;
		}
	}
}

@media screen and (max-width: 768px) {
	.study-logo {
		display: grid;
		place-items: center;
		flex: 0.5;
		img {
			width: 50px;
			height: 50px;
			border-radius: 50%;
		}
	}
}
</style>
