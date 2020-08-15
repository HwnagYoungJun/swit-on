<template>
	<div v-if="loading">
		<Loading />
	</div>
	<div v-else class="dashboard-wrap">
		<scheduleAddBtn v-if="isLeader" />

		<div class="card-wrap">
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
							board_name: 'repository',
							article_id: article.id,
						},
					}"
					><ArticleCard :article="article">
						<div slot="logo">
							<img src="@/assets/dd.png" alt="" />
						</div>
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
						<div slot="logo">
							<img src="@/assets/color.png" alt="" />
						</div>
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
						<div slot="logo">
							<img src="@/assets/dd.png" alt="" />
						</div>
						<div slot="bread">
							<span>공지</span>
						</div>
					</ArticleCard>
				</router-link>
			</div>
		</div>
		<aside>
			<div class="schedule">
				<span class="schedule-title">나의 출석</span>
				<div class="attend-wrap">
					<div class="attend">
						<p>참여율</p>
						<div class="progress-bar">
							<span class="join-percent">35%</span>
						</div>
					</div>
					<div class="attend">
						<p>출석률</p>
						<div class="progress-bar">
							<span class="attend-percent">85%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="schedule">
				<span class="schedule-title">소모임 일정</span>
				<ul>
					<li :key="s.id" v-for="s in schedules">
						<div class="schedule-list" v-if="s.isAbled">
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
			<div class="schedule">
				<span class="schedule-title">나의 일정</span>
				<ul>
					<li :key="s.id" v-for="s in participantSchedules">
						<div class="schedule-list" v-if="s.isScheduleJoin">
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
									:disabled="!s.startTime"
									@click="checkIn(s.scheduleId)"
									class="active"
								>
									입실
								</button>
								<button
									:disabled="!s.endTime"
									@click="checkOut(s.scheduleId)"
									class="active"
								>
									퇴실
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</aside>
	</div>
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
} from '@/api/studies';
import scheduleAddBtn from '@/components/common/scheduleAddBtn.vue';
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
		scheduleAddBtn,
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
				bus.$emit('show:toast', `${error}`);
			}
		},
		async checkIn(scheduleId) {
			try {
				const studyId = this.id;
				await checkInSchedule(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async checkOut(scheduleId) {
			try {
				const studyId = this.id;
				await checkOutSchedule(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async joinSchedule(scheduleId) {
			try {
				const studyId = this.id;
				await createScheduleParticipate(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async removeSchedule(scheduleId) {
			try {
				const studyId = this.id;
				await deleteScheduleParticipate(studyId, scheduleId);
				this.fetchSchedule();
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		async fetchSchedule() {
			try {
				const { data } = await fetchStudySchedule(this.id);
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
					// const endMonth = ('00' + (end.getMonth() + 1)).slice(-2);
					// const endDate = ('00' + end.getDate()).slice(-2);
					const endHours = ('00' + end.getHours()).slice(-2);
					const endMinutes = ('00' + end.getMinutes()).slice(-2);
					const scheduleId = el.id;
					const isScheduleJoin = el.members.filter(
						member => member.name === userName,
					).length;
					const isAbled =
						new Date(el.start) - nowTime > 0 && !isScheduleJoin ? true : false;
					const ScheduleData = {
						startMonth,
						startDate,
						startDay,
						startHours,
						startMinutes,
						// endMonth,
						// endDate,
						endHours,
						endMinutes,
						scheduleId,
						isScheduleJoin,
						isAbled,
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
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
	},
	created() {
		this.fetchData();
		this.fetchSchedule();
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
	display: flex;
	flex-direction: column;
	align-items: center;
	flex: 2;
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
				border-radius: 8px;
				background: rgb(238, 238, 238);
				.join-percent {
					display: inline-block;
					width: 35%;
					padding-right: 8px;
					text-align: right;
					border-radius: 5px 0 0 5px;
					background: $btn-purple;
				}
				.attend-percent {
					display: inline-block;
					width: 85%;
					padding-right: 8px;
					text-align: right;
					border-radius: 5px 0 0 5px;
					background: $btn-purple;
				}
			}
		}
	}
}
</style>
