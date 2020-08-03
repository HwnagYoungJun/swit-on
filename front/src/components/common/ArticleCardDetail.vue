<template>
	<div>
		<nav aria-label="Breadcrumb" class="breadcrumb">
			<ol>
				<li>
					<a href="#">프로그래밍 언어</a><span aria-hidden="true">></span>
				</li>
				<li>
					<router-link :to="{ name: 'dashboard' }">python 소모임</router-link
					><span aria-hidden="true">></span>
				</li>
				<li>
					<router-link :to="{ name: 'repository' }" aria-current="page"
						>repository</router-link
					>
				</li>
			</ol>
		</nav>
		<div class="card-detail-wrap">
			<div class="card-detail">
				<div class="card-detail-title">
					<img src="@/assets/color2.png" alt="" class="img" />
					<p>저장소 제목</p>
				</div>
				<div class="card-detail-content">
					<p>
						Lorem ipsum dolor, sit amet consectetur adipisicing elit. Non,
						asperiores facere excepturi mollitia earum quisquam cupiditate
						debitis perferendis maiores voluptatibus impedit repudiandae
						reiciendis incidunt ex. Consequuntur facere accusantium corporis
						dolorem? Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Enim aspernatur aperiam mollitia dicta animi. Quaerat nisi
						distinctio et neque ipsa eaque optio quam magni qui odit, est ipsum
						quibusdam corporis.
					</p>
					<img src="@/assets/django.png" alt="main-img" class="main-img" />
					<p>
						Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor non
						alias, nulla adipisci, saepe modi, expedita aspernatur nostrum
						dolores pariatur ex deserunt repellendus quod. Enim reiciendis
						blanditiis ad vel molestiae?
					</p>
					<div class="logo">
						<i class="icon ion-md-heart"></i>
						<span>좋아요 800개</span>
					</div>
					<div class="content-info">
						<a href="">User name</a><span>#python</span> <span>#django</span>
					</div>
					<input type="text" name="" id="" autofocus placeholder="댓글달기" />
				</div>
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
.card-detail-wrap {
	width: 100%;
	margin-bottom: 50px;
	padding: 15px 0;
	border-radius: 3px;
}
.card-detail-title {
	display: flex;
	margin: 10px;
	p {
		margin: 5px 15px;
		font-size: $font-bold;
	}
}
.img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}
.card-detail-content {
	padding: 10px;
	.main-img {
		width: 100%;
		margin: 30px 0;
	}
	.logo {
		display: flex;
		align-items: center;
		margin: 50px 0 10px;
		color: crimson;
		font-size: $font-bold;
		i {
			margin-right: 10px;
		}
		span {
			font-size: $font-light;
			color: #999999;
		}
	}
	.content-info {
		margin: 10px 0 15px;
		a {
			margin-right: 15px;
			font-weight: bold;
			color: #636363;
		}
		span {
			display: inline-block;
			width: 80px;
			margin: 0 3px;
			padding: 3px 0;
			border: 1px solid #6c23c0;
			border-radius: 20px;
			color: #6c23c0;
			font-size: $font-light;
			background: none;
			text-align: center;
		}
	}
	input {
		width: 100%;
		height: 45px;
		border: none;
		border-top: 1px solid #dbdbdb;
		color: #999999;
		font-size: $font-normal;
		&:focus {
			outline: none;
		}
	}
}
</style>
