<template>
	<section>
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
		<section class="study-description">
			<div class="study-content">
				<h3 class="study-title" id="studyTitle">
					{{ study.name }}
				</h3>
				<div class="leader-comment">
					<span>{{ leaderName }}</span>
					<p>우리 {{ study.name }}은</p>
					<p>
						매주
						<span class="strong">{{ study.week | formatWeekday }}요일</span>
					</p>
					<time class="strong">{{ study.start_time }}</time> 부터
					<time class="strong">{{ study.end_time }}</time> 까지 활동합니다<i
						class="icon ion-md-quote"
						aria-hidden="true"
					></i>
				</div>
				<p class="study-member-cnt">
					현재 {{ study.users_limit }}명 중
					<span class="strong">{{ study.users_current }}명</span>이 함께 하고
					있어요
				</p>
			</div>
			<div class="study-logo">
				<img :src="studyImg" :alt="`${study.name} 스터디 사진`" />
			</div>
		</section>
		<section v-if="isJoined" class="study-category">
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
		</section>
		<section v-else class="study-sub-content">
			<h3 class="title">모임 소개</h3>
			<article class="study-des-wrap">
				<div class="study-des">
					<p>
						{{ study.description }}
					</p>
					<p>
						<span class="strong">{{ study.start_term | formatDate }}</span
						>부터 <span class="strong">{{ study.end_term | formatDate }}</span
						>까지 스터디원을 모집합니다!
					</p>
					<p>
						<span class="strong">{{ study.name }}</span
						>과 함께 해요 :)
					</p>
				</div>
				<div class="study-members">
					<p class="diff-user">{{ diffUser }}자리가 비어있어요 :(</p>
					<ul>
						<li v-for="member in members" :key="member.id">
							<router-link class="member-box" :to="`/profile/${member.name}`">
								<img
									v-if="member.profile_image"
									:src="`${baseURL}${member.profile_image}`"
									:alt="`${member.name}의 프로필 사진`"
									class="member-image"
								/>
								<img
									v-else
									:src="`${baseURL}upload/noProfile.png`"
									:alt="`${member.name}의 프로필 대체 사진`"
									class="member-image"
								/>
								{{ member.name }}
							</router-link>
						</li>
					</ul>
				</div>
			</article>
			<button @click="studyJoin" class="join-btn">가입하기</button>
		</section>
	</section>
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
				this.study = data.study;
				this.isJoined = data.isJoined;
				this.isLeader = data.isLeader;
				this.leaderName = data.leaderName;
				this.members = data.members;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
				if (error.response.status === 404) {
					this.$router.push('/404');
				}
			}
		},
		async studyJoin() {
			try {
				const studyId = this.id;
				await JoinStudy(studyId);
				this.fetchData();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
				if (error.response.status === 401) {
					this.$router.push('/login');
				}
			}
		},
	},
	computed: {
		studyImg() {
			if (this.study.logo) {
				return `${process.env.VUE_APP_API_URL}${this.study.logo}`;
			} else {
				return `${process.env.VUE_APP_API_URL}upload/noStudy.jpg`;
			}
		},
		diffUser() {
			return this.study.users_limit - this.study.users_current;
		},
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
	},
	created() {
		this.fetchData();
	},
	updated() {
		document.title = `스윗온 ${this.study.name} 스터디`;
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
	@include scale(font-size, '16');
	@media screen and (max-width: 768px) {
		grid-template-columns: repeat(1, 1fr);
	}
	.study-logo {
		grid-area: logo;
		width: 100%;
		height: 100%;
		img {
			width: 100%;
			height: 100%;
			object-fit: fill;
		}
		@media screen and (max-width: 768px) {
			display: none;
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
			font-weight: normal;
		}
		i {
			margin-left: 5px;
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
		@media screen and (max-width: 768px) {
			font-size: 15.5px;
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
	margin-bottom: 30px;
	padding: 15px;
	position: relative;
	.title {
		margin: 10px 0 30px;
		font-size: $font-bold;
		font-weight: normal;
	}
	.study-des-wrap {
		display: flex;
		.study-des {
			flex: 1.5;
			p {
				margin-bottom: 10px;
				font-size: $font-bold * 0.8;
			}
			.strong {
				margin: 0 5px;
				color: $main-color;
				font-size: 20px;
			}
		}
		.study-members {
			flex: 1;
			margin-left: 3rem;
			.diff-user {
				margin-bottom: 8px;
			}
			li {
				.member-box {
					display: flex;
					align-items: center;
					margin-bottom: 8px;
				}
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
