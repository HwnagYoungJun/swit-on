<template>
	<div>
		<nav aria-label="Breadcrumb" class="breadcrumb">
			<ol>
				<li>
					<router-link
						:to="`/category/${study.uppercategory_name}`"
						tabindex="-1"
						>{{ study.uppercategory_name }}</router-link
					><span aria-hidden="true">></span>
				</li>
				<li>
					<router-link :to="`/study/${id}`" aria-current="page" tabindex="-1">{{
						study.name
					}}</router-link>
				</li>
			</ol>
		</nav>
		<div class="study-description">
			<div class="study-content">
				<p class="study-title">{{ study.name }}</p>
				<div class="leader-comment">
					<span>{{ leaderName }}</span>
					<p>
						우리 {{ study.name }}은 매주
						<span class="strong">{{ study.week | formatWeekday }}요일</span>
					</p>
					<span class="strong">{{ study.start_time }}</span> 부터
					<span class="strong">{{ study.end_time }}</span> 까지 활동합니다<i
						class="icon ion-md-quote"
					></i>
				</div>
				<p class="study-member-cnt">
					현재 {{ study.users_limit }}명 중
					<span class="strong">{{ study.users_current }}명</span>이 함께 하고
					있어요
				</p>
			</div>
			<div class="study-logo">
				<img :src="studyImg" alt="study-logo" />
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
			<router-link :to="{ name: 'qna' }">Q&A<span></span></router-link>
			<router-link :to="{ name: 'notice' }">공지<span></span></router-link>
			<router-link :to="{ name: 'meeting' }">회의<span></span></router-link>
			<div class="study-sub-content">
				<router-view :id="id" :isLeader="isLeader"></router-view>
			</div>
		</div>
		<div v-else class="study-sub-content">
			<p class="title">{{ study.name }} 소개</p>
			<div class="study-des-wrap">
				<div class="study-des">
					<p>
						{{ study.description }}
					</p>
					<p>
						<span class="strong">{{ study.start_term | formdate }}</span
						>부터 <span class="strong">{{ study.end_term | formdate }}</span
						>까지 스터디원을 모집합니다!
					</p>
					<p>
						<span class="strong">{{ study.week }}</span
						>일 동안 함께 해요 :)
					</p>
					<!-- <p class="is-private">{{ isPublicStudy }}</p>
					<p v-if="study.isPrivate">스터디원만</p>
					<p v-else></p> -->
				</div>
				<div class="study-members">
					<p class="diff-user">{{ diffUser }}자리가 비어있어요 :(</p>
					<ul>
						<li v-for="member in members" :key="member.id">
							<img
								v-if="member.profile_image"
								:src="`${baseURL}${member.profile_image}`"
								alt="profileImage"
								class="member-image"
							/>
							<img
								v-else
								src="@/assets/dd.png"
								alt="profileImage"
								class="member-image"
							/>
							{{ member.name }}
						</li>
					</ul>
				</div>
			</div>
			<button @click="studyJoin" class="join-btn">가입하기</button>
		</div>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import { JoinStudy, fetchStudy } from '@/api/studies';
export default {
	props: {
		id: Number,
	},
	data() {
		return {
			isJoined: false,
			isLeader: null,
			leaderName: '',
			members: null,
			study: {},
		};
	},
	methods: {
		async fetchData() {
			try {
				const studyId = this.id;
				const { data } = await fetchStudy(studyId);
				console.log(data);
				this.study = data.study;
				this.isJoined = data.isJoined;
				this.isLeader = data.isLeader;
				this.leaderName = data.leaderName;
				this.members = data.members;
			} catch (error) {
				console.log(error);
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async studyJoin() {
			try {
				const studyId = this.id;
				await JoinStudy(studyId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	computed: {
		studyImg() {
			if (this.study.logo) {
				return `${process.env.VUE_APP_API_URL}${this.study.logo}`;
			} else {
				return '@/assets/django.png';
			}
		},
		diffUser() {
			return this.study.users_limit - this.study.users_current;
		},
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		// isPublicStudy() {
		// 	let isPublic = '공개';
		// 	if (this.study.isPrivate) {
		// 		isPublic = '비공개';
		// 	}
		// 	return isPublic;
		// },
	},
	created() {
		this.fetchData();
	},
	watch: {
		$route: 'fetchData',
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
	height: 18rem;
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
		position: relative;
		p {
			margin: 5px 0;
		}
		.study-title {
			margin-bottom: 10px;
			font-size: $font-bold;
		}
		i {
			margin-left: 5px;
			// transform: translateY(-50px) rotate(10deg);
		}
		.study-member-cnt {
			position: absolute;
			right: 10px;
			bottom: 0;
		}
	}
	.strong {
		margin-right: 3px;
		color: $main-color;
		font-size: 18px;
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
	margin-bottom: 30px;
	padding: 15px;
	position: relative;
	.title {
		margin: 10px 0 30px;
		font-size: $font-bold;
	}
	.study-des-wrap {
		display: flex;
		.study-des {
			flex: 1.5;
			p {
				margin-bottom: 10px;
			}
			.strong {
				margin: 0 5px;
				color: $main-color;
				font-size: 20px;
			}
		}
		.study-members {
			flex: 1;
			.diff-user {
				margin-bottom: 8px;
			}
			li {
				display: flex;
				align-items: center;
				margin-bottom: 8px;
				.member-image {
					width: 30px;
					height: 30px;
					margin-right: 8px;
					border-radius: 50%;
				}
			}
			@media screen and (max-width: 768px) {
				display: none;
			}
		}
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
	}
}
</style>
