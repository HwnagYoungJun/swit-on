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
					<router-link
						v-if="bestMember[0].user_name !== undefined"
						:to="`/profile/${bestMember[0].user_name}`"
					>
						{{ bestMember[0].user_name }}
						<i class="icon ion-md-podium podium-icon"></i
						>{{ bestMember[0].score }}
					</router-link>
				</li>
				<li>
					<div class="badgesilver">
						<div class="rounded">
							<i class="icon ion-md-medal"></i>
						</div>
					</div>
					<router-link
						v-if="bestMember[1].user_name !== undefined"
						:to="`/profile/${bestMember[1].user_name}`"
					>
						{{ bestMember[1].user_name }}
						<i class="icon ion-md-podium podium-icon"></i
						>{{ bestMember[1].score }}
					</router-link>
				</li>
				<li>
					<div class="badgebronze">
						<div class="rounded">
							<i class="icon ion-md-medal"></i>
						</div>
					</div>
					<router-link
						v-if="bestMember[2].user_name !== undefined"
						:to="`/profile/${bestMember[2].user_name}`"
					>
						{{ bestMember[2].user_name }}
						<i class="icon ion-md-podium podium-icon"></i
						>{{ bestMember[2].score }}
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
					<router-link
						v-if="bestArticle[0].title !== undefined"
						:to="
							`/study/${studyId}/${bestArticle[0].board_name}/${bestArticle[0].id}`
						"
					>
						{{ trunk(bestArticle[0].title) }}
						<i class="icon ion-md-heart  heart-icon"></i
						>{{ bestArticle[0].likes }}
					</router-link>
				</li>
				<li>
					<div class="second-article">
						<div class="rounded">
							<i class="icon ion-md-heart"></i>
						</div>
					</div>
					<router-link
						v-if="bestArticle[1].title !== undefined"
						:to="
							`/study/${studyId}/${bestArticle[1].board_name}/${bestArticle[1].id}`
						"
					>
						{{ trunk(bestArticle[1].title) }}
						<i class="icon ion-md-heart heart-icon"></i
						>{{ bestArticle[1].likes }}
					</router-link>
				</li>
				<li>
					<div class="third-article">
						<div class="rounded">
							<i class="icon ion-md-heart"></i>
						</div>
					</div>
					<router-link
						v-if="bestArticle[2].title !== undefined"
						:to="
							`/study/${studyId}/${bestArticle[2].board_name}/${bestArticle[2].id}`
						"
					>
						{{ trunk(bestArticle[2].title) }}
						<i class="icon ion-md-heart heart-icon"></i>
						{{ bestArticle[2].likes }}
					</router-link>
				</li>
			</ul>
		</section>
	</aside>
</template>

<script>
export default {
	props: {
		studyId: Number,
		bestMember: Array,
		bestArticle: Array,
	},
	methods: {
		trunk(str) {
			if (str.length > 11) {
				return str.substr(0, 11) + '...';
			}
			return str;
		},
	},
};
</script>

<style lang="scss" scoped>
.ranking-wrap {
	height: 80vh;
	position: sticky;
	top: 50px;
}
.podium-icon {
	color: rgb(138, 138, 138);
	margin-left: 0.5rem;
	margin-right: 0.2rem;
}
.heart-icon {
	color: rgb(138, 138, 138);
	margin-left: 0.5rem;
	margin-right: 0.2rem;
}
.ion-md-create {
	color: rgb(138, 138, 138);
	margin-left: 0.5rem;
	margin-right: 0.2rem;
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
