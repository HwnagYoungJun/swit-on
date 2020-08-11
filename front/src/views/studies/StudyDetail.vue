<template>
	<div>
		<nav aria-label="Breadcrumb" class="breadcrumb">
			<ol>
				<li>
					<router-link :to="`/category/${study.uppercategory_name}`">{{
						study.uppercategory_name
					}}</router-link
					><span aria-hidden="true">></span>
				</li>
				<li>
					<router-link :to="`/study/${id}`" aria-current="page">{{
						study.name
					}}</router-link>
				</li>
			</ol>
		</nav>
		<div class="study-description">
			<div class="study-content">
				<p class="study-title">{{ study.name }}</p>
				<p>모집: {{ study.start_term }} - {{ study.end_term }}</p>
				<p>
					매주:
					{{ study.week | formatWeekday }}
					{{ study.start_time }}시-{{ study.end_time }}시
				</p>
				<small>{{ study.users_current }}/{{ study.users_limit }}명</small>
			</div>
			<div class="study-logo">
				<img v-if="study.logo" :src="`${BaseUrl}${study.logo}`" alt="" />
				<img v-else src="@/assets/django.png" alt="" />
			</div>
		</div>
		<div v-if="isJoined" class="study-category">
			<router-link :to="`/study/${study.id}`"
				>대시보드<span></span
			></router-link>
			<router-link :to="{ name: 'calendar' }">캘린더<span></span></router-link>
			<router-link :to="{ name: 'repository' }"
				>저장소<span></span
			></router-link>
			<router-link :to="{ name: 'notice' }">공지<span></span></router-link>
			<router-link :to="{ name: 'qna' }">Q&A<span></span></router-link>
			<hr />
			<div class="study-sub-content">
				<router-view :id="id"></router-view>
			</div>
		</div>
		<div v-else class="study-sub-content">
			<p class="title">{{ study.name }} 소개</p>
			<p>
				{{ study.description }}
			</p>
			<button @click="studyJoin" class="join-btn">가입하기</button>
		</div>
	</div>
</template>

<script>
import { JoinStudy, fetchStudy } from '@/api/studies';
export default {
	props: {
		id: Number,
	},
	data() {
		return {
			isJoined: false,
			study: {},
		};
	},
	methods: {
		async fetchData() {
			const studyId = this.id;
			const { data } = await fetchStudy(studyId);
			console.log(data);
			this.study = data.study;
			this.isJoined = data.isJoined;
		},
		async studyJoin() {
			const studyId = this.id;
			await JoinStudy(studyId);
			this.fetchData();
		},
	},
	computed: {
		BaseUrl() {
			return process.env.VUE_APP_API_URL;
		},
	},
	created() {
		this.fetchData();
	},
	watch: {
		id() {
			this.fetchData();
		},
	},
};
</script>

<style lang="scss">
.study-main {
	margin-bottom: 2rem;
}
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
	width: 100%;
	height: 14rem;
	display: grid;
	grid-template-areas: 'content logo';
	grid-template-rows: 100%;
	grid-template-columns: 60% 40%;
	margin-bottom: 30px;
	padding: 2%;
	color: rgb(107, 107, 107);
	box-shadow: 0 3px 6px rgb(214, 214, 214);
	border-radius: 4px;
	.study-logo {
		grid-area: logo;
		width: 100%;
		height: 100%;
		img {
			width: 100%;
			height: 100%;
			object-fit: fill;
		}
	}
	.study-content {
		grid-area: content;
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
// .study-description {
// 	display: flex;
// 	margin-bottom: 30px;
// 	padding: 2%;
// 	color: rgb(107, 107, 107);
// 	box-shadow: 0 3px 6px rgb(214, 214, 214);
// 	border-radius: 4px;
// 	.study-logo {
// 		flex: 1.5;
// 		img {
// 			width: 100%;
// 		}
// 	}
// 	.study-content {
// 		flex: 2;
// 		margin: 0 30px;
// 		p {
// 			margin: 5px 0;
// 		}
// 		.study-title {
// 			margin-bottom: 10px;
// 			font-size: $font-bold;
// 		}
// 	}
// }
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
	.title {
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
