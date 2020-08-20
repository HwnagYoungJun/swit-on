<template>
	<section v-if="loading">
		<Loading />
	</section>
	<section v-else class="dashboard-wrap">
		<ScheduleAddBtn v-if="isLeader" />
		<article class="card-wrap">
			<div v-if="!isArticles">
				<ArticleNotFound />
			</div>
			<div v-else>
				<router-link
					:key="article.id"
					v-for="article in qnaArticles"
					:to="{
						name: 'BoardArticleDetail',
						params: {
							id,
							board_name: 'qna',
							article_id: article.id,
						},
					}"
					><ArticleCard :article="article">
						<div slot="bread">
							<span>Q&A</span>
						</div>
					</ArticleCard>
				</router-link>
				<router-link
					:key="article.id"
					v-for="article in repositoryArticles"
					:to="{
						name: 'BoardArticleDetail',
						params: {
							id,
							board_name: 'repository',
							article_id: article.id,
						},
					}"
					><ArticleCard :article="article">
						<div slot="bread">
							<span>저장소</span>
						</div>
					</ArticleCard>
				</router-link>
				<router-link
					:key="article.id"
					v-for="article in noticeArticles"
					:to="{
						name: 'BoardArticleDetail',
						params: {
							id,
							board_name: 'notice',
							article_id: article.id,
						},
					}"
					><ArticleCard :article="article">
						<div slot="bread">
							<span>공지</span>
						</div>
					</ArticleCard>
				</router-link>
			</div>
		</article>
		<aside>
			<div class="schedule">
				<span class="schedule-title">나의 출석</span>
				<div class="attend-wrap">
					<div class="attend">
						<p>참여율</p>
						<div class="progress-bar">
							<span class="join-percent">
								{{ attend.participation * 100 }}%</span
							>
						</div>
					</div>
					<div class="attend">
						<p>출석률</p>
						<div class="progress-bar">
							<span class="attend-percent">
								{{ attend.attendance * 100 }}%</span
							>
						</div>
					</div>
				</div>
			</div>
			<div class="schedule">
				<span class="schedule-title">소모임 일정</span>
				<ul>
					<li :key="s.id" v-for="s in schedules">
						<div
							class="schedule-list"
							v-if="s.isAbled"
							@click="s.modal = !s.modal"
						>
							<div v-if="s.modal" class="schedule-modal">
								<p class="scheduleTitle">{{ s.title }}</p>
								<p>
									<span :key="member.id" v-for="member in s.joinMembers">
										"{{ member.name }}"
									</span>
								</p>
							</div>
							<span>
								<span>{{ s.startMonth }}.{{ s.startDate }}</span>
								<span>{{ s.startDay }}</span>
								<span
									>{{ s.startHours }}:{{ s.startMinutes }}-{{ s.endHours }}:{{
										s.endMinutes
									}}</span
								>
							</span>
							<div class="schedule-btnbox">
								<button
									v-if="s.isScheduleJoin"
									@click="removeSchedule(s.scheduleId)"
									class="unactive"
								>
									참여중
								</button>
								<button
									v-else
									@click="joinSchedule(s.scheduleId)"
									class="active"
								>
									참여
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="schedule schedule-bottom">
				<span class="schedule-title">나의 일정</span>
				<ul>
					<li :key="s.id" v-for="s in participantSchedules">
						<div
							class="schedule-list"
							v-if="s.isScheduleJoin"
							@click="s.modal = !s.modal"
						>
							<div v-if="s.modal" class="schedule-modal">
								<p class="scheduleTitle">{{ s.title }}</p>
								<p>
									<span :key="member.id" v-for="member in s.joinMembers">
										"{{ member.name }}"
									</span>
								</p>
							</div>
							<span>
								<span>{{ s.startMonth }}.{{ s.startDate }}</span>
								<span>{{ s.startDay }}</span>
								<span
									>{{ s.startHours }}:{{ s.startMinutes }}-{{ s.endHours }}:{{
										s.endMinutes
									}}</span
								>
							</span>
							<div class="schedule-btnbox">
								<button
									:disabled="!s.startTime || !s.checkIn"
									@click="checkIn(s.scheduleId)"
									class="active"
								>
									<span v-if="s.checkIn">입실</span>
									<span v-else>완료</span>
								</button>
								<button
									:disabled="!s.endTime || s.checkOut"
									@click="checkOut(s.scheduleId)"
									class="active"
								>
									<span v-if="s.checkOut">완료</span>
									<span v-else>퇴실</span>
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</aside>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
import ArticleCard from '@/components/common/ArticleCard.vue';
import { fetchArticles } from '@/api/articles';
import {
	fetchStudySchedule,
	createScheduleParticipate,
	deleteScheduleParticipate,
	checkInSchedule,
	checkOutSchedule,
	fetchAttendance,
} from '@/api/studies';
import ScheduleAddBtn from '@/components/common/ScheduleAddBtn.vue';
import ArticleNotFound from '@/components/common/ArticleNotFound.vue';
import Loading from '@/components/common/Loading.vue';
import { mapGetters } from 'vuex';
export default {
	props: {
		id: Number,
		isLeader: Boolean,
	},
	data() {
		return {
			schedules: [],
			participantSchedules: [],
			repositoryArticles: null,
			noticeArticles: null,
			qnaArticles: null,
			loading: false,
			attend: {},
		};
	},
	computed: {
		...mapGetters(['getName']),
		isArticles() {
			return this.repositoryArticles || this.noticeArticles || this.qnaArticles;
		},
	},
	components: {
		ArticleCard,
		ScheduleAddBtn,
		ArticleNotFound,
		Loading,
	},
	methods: {
		async fetchData() {
			try {
				const studyId = this.id;
				this.loading = true;
				const {
					data: { notice, repository, qna },
				} = await fetchArticles(studyId, 'dashboard');
				this.loading = false;
				this.repositoryArticles = repository.length ? repository : null;
				this.noticeArticles = notice.length ? notice : null;
				this.qnaArticles = qna.length ? qna : null;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async checkIn(scheduleId) {
			try {
				const studyId = this.id;
				await checkInSchedule(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async checkOut(scheduleId) {
			try {
				const studyId = this.id;
				await checkOutSchedule(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async joinSchedule(scheduleId) {
			try {
				const studyId = this.id;
				await createScheduleParticipate(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async removeSchedule(scheduleId) {
			try {
				const studyId = this.id;
				await deleteScheduleParticipate(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async fetchSchedule() {
			try {
				const { data } = await fetchStudySchedule(this.id);
				console.log(data);
				var days = ['일', '월', '화', '수', '목', '금', '토'];
				const userName = this.getName;
				let scheduleList = [];
				let participateList = [];
				data.forEach(el => {
					// ISO -> date 객체
					const nowTime = new Date();
					const start = new Date(Date.parse(el.start));
					const end = new Date(Date.parse(el.end));
					const startMonth = ('00' + (start.getMonth() + 1)).slice(-2);
					const startDate = ('00' + start.getDate()).slice(-2);
					const startDay = days[start.getDay()];
					const startHours = ('00' + start.getHours()).slice(-2);
					const startMinutes = ('00' + start.getMinutes()).slice(-2);
					const endHours = ('00' + end.getHours()).slice(-2);
					const endMinutes = ('00' + end.getMinutes()).slice(-2);
					const scheduleId = el.id;
					const isScheduleJoin = el.members.filter(
						member => member.name === userName,
					).length;
					const title = el.title;
					let joinMembers = el.members;
					const modal = false;
					const isAbled =
						new Date(el.start) - nowTime > 0 && !isScheduleJoin ? true : false;
					const ScheduleData = {
						startMonth,
						startDate,
						startDay,
						startHours,
						startMinutes,
						endHours,
						endMinutes,
						scheduleId,
						isScheduleJoin,
						isAbled,
						title,
						joinMembers,
						modal,
					};
					scheduleList.push(ScheduleData);
					ScheduleData.isScheduleJoin
						? participateList.push({
								...ScheduleData,
								startTime:
									new Date(el.start) - nowTime > 0 &&
									new Date(el.start) - nowTime < 600000
										? true
										: false,
								endTime:
									nowTime - new Date(el.end) > 0 &&
									nowTime - new Date(el.end) < 600000
										? true
										: false,
						  })
						: null;
				});
				this.schedules = [...scheduleList];
				this.participantSchedules = [...participateList];
				this.participantSchedules = this.participantSchedules
					.reverse()
					.slice(0, 3);
				this.schedules = this.schedules.reverse();
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async fetchAttendanceRate() {
			try {
				const studyId = this.id;
				const { data } = await fetchAttendance(studyId);
				this.attend = data;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	updated() {
		let joinPercent = document.querySelector('.join-percent');
		joinPercent.style.width = `${this.attend.participation * 100}%`;
		let attendPercent = document.querySelector('.attend-percent');
		attendPercent.style.width = `${this.attend.attendance * 100}%`;
	},
	created() {
		this.fetchData();
		this.fetchSchedule();
		this.fetchAttendanceRate();
	},
	watch: {
		$route: ['fetchData', 'fetchSchedule', 'fetchAttendanceRate'],
	},
};
</script>

<style lang="scss">
.dashboard-wrap {
	height: 100%;
	display: flex;
	position: relative;
	@media screen and (max-width: 1350px) {
		flex-direction: column-reverse;
	}
}
.card-wrap {
	flex: 2;
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 20px;
	@media screen and (max-width: 768px) {
		// row + wrap => 자동으로 길이 조절해줌
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
	}
}
aside {
	flex: 1.5;
}
.schedule {
	margin: 50px 0;
	@media screen and (max-width: 768px) {
		margin: 25px 0 25px;
	}
	padding: 25px 0 15px;
	position: relative;
	color: rgb(138, 138, 138);
	.schedule-title {
		position: absolute;
		top: -5px;
		left: -15px;
		color: rgb(90, 90, 90);
		font-weight: bold;
		background: #fff;
	}
	li {
		margin: 10px 0;
		color: rgb(90, 90, 90);
		.schedule-list {
			display: flex;
			justify-content: space-between;
			align-items: center;
			position: relative;
			.schedule-modal {
				position: absolute;
				background: rgba(255, 255, 255, 1);
				color: #868e96;
				z-index: 1000;
				box-shadow: 0 2px 6px 0 rgba(68, 67, 68, 0.4);
				border-radius: 3px;
				width: 300px;
				top: 2rem;
				left: 0;
				padding: 0.5rem;
				@media screen and (max-width: 768px) {
					top: 2.5rem;
					left: 0;
					width: 250px;
				}
				.scheduleTitle {
					font-size: $font-normal;
					font-weight: bold;
				}
			}
		}
		@media screen and (max-width: 370px) {
			display: inline;
			margin: 10px 20px;
		}
		span {
			margin-right: 10px;
		}
		.schedule-btnbox {
			button {
				@include common-btn();
				display: inline-block;
				width: 4rem;
				margin-right: 5px;
				@media screen and (max-width: 370px) {
					width: 100%;
					margin: 8px 0;
				}
				&:disabled {
					cursor: default;
					&:hover {
						transition: none;
					}
				}
			}
			.active {
				color: #fff;
				background: $btn-purple;
				span {
					margin: auto;
				}
			}
			.unactive {
				background: #fff;
				color: $btn-purple;
			}
		}
	}
	.attend-wrap {
		padding-top: 5px;
		.attend {
			p {
				color: rgb(90, 90, 90);
				margin-bottom: 4px;
			}
			.progress-bar {
				width: 100%;
				color: white;
				border-radius: 4px;
				background: rgb(238, 238, 238);
				.join-percent {
					display: inline-block;
					width: 0%;
					// width: 35%;
					// padding-right: 8px;
					text-align: left;
					border-radius: 4px;
					background: $btn-purple;
				}
				.attend-percent {
					display: inline-block;
					width: 0%;
					// width: 85%;
					// padding-right: 8px;
					text-align: left;
					border-radius: 4px;
					background: $btn-purple;
				}
			}
		}
	}
}
.schedule-bottom {
	border-bottom: none;
	@media screen and (max-width: 1350px) {
		padding-bottom: 50px;
		border-bottom: 1px solid #dbdbdb;
	}
}
</style>
