<template>
	<aside class="ranking-wrap">
		<section class="ranking">
			<span class="ranking-title">우리 랭킹</span>
			<ul>
				<li>
					<div class="badgegold">
						<div class="rounded">
							<i class="icon ion-md-medal"></i>
						</div>
					</div>
					<router-link :to="`/profile/${bestMember[0].user_name}`">
						{{ bestMember[0].user_name }}
					</router-link>
				</li>
				<li>
					<div class="badgesilver">
						<div class="rounded">
							<i class="icon ion-md-medal"></i>
						</div>
					</div>
					<router-link :to="`/profile/${bestMember[1].user_name}`">
						{{ bestMember[1].user_name }}
					</router-link>
				</li>
				<li>
					<div class="badgebronze">
						<div class="rounded">
							<i class="icon ion-md-medal"></i>
						</div>
					</div>
					<router-link :to="`/profile/${bestMember[2].user_name}`">
						{{ bestMember[2].user_name }}
					</router-link>
				</li>
			</ul>
		</section>
		<section class="ranking">
			<span class="ranking-title">인기 글</span>
			<ul>
				<li>
					<div class="first-article">
						<div class="rounded">
							<i class="icon ion-md-heart"></i>
						</div>
					</div>
					<p>게시글 제목</p>
				</li>
				<li>
					<div class="second-article">
						<div class="rounded">
							<i class="icon ion-md-heart"></i>
						</div>
					</div>
					<p>게시글 제목</p>
				</li>
				<li>
					<div class="third-article">
						<div class="rounded">
							<i class="icon ion-md-heart"></i>
						</div>
					</div>
					<p>게시글 제목</p>
				</li>
			</ul>
		</section>
	</aside>
</template>

<script>
export default {
	props: {
		bestMember: Array,
	},
};
</script>

<style lang="scss" scoped>
.ranking-wrap {
	height: 80vh;
	position: sticky;
	top: 50px;
}
.ranking {
	position: relative;
	margin: 10px 0 30px;
	padding: 20px 0;
	color: rgb(138, 138, 138);

	.ranking-title {
		position: absolute;
		top: -5px;
		left: -15px;
		color: rgb(90, 90, 90);
		font-weight: bold;
		background: #fff;
	}
	.badgegold {
		@include grade-badge('gold', 40px);
	}
	.badgesilver {
		@include grade-badge('silver', 40px);
	}
	.badgebronze {
		@include grade-badge('bronze', 40px);
	}
	.first-article {
		@include popular-article('crimson', 40px);
	}
	.second-article {
		@include popular-article('crimson2', 40px);
	}
	.third-article {
		@include popular-article('crimson3', 40px);
	}
	li {
		display: flex;
		align-items: center;
		margin: 10px 0;
		color: rgb(90, 90, 90);
		img {
			width: 30px;
			object-fit: cover;
		}
		span {
			margin-right: 10px;
		}
	}
}
</style>
