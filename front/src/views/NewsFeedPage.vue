<template>
	<div class="container">
		<main class="newsFeed-container">
			<section v-if="articles" class="article-box">
				<router-link
					v-for="article in articles"
					:key="article.id"
					:to="{
						name: 'BoardArticleDetail',
						params: {
							id: article.study.id,
							board_name: article.board_name,
							article_id: article.id,
						},
					}"
				>
					<ArticleCard :article="article" />
				</router-link>
			</section>
			<aside class="side-box">
				<div class="group-box">
					<span>
						나의스터디
					</span>
					<div class="group-nav">
						<router-link :to="{ name: 'mygroup' }" class="moreGroup"
							>더보기</router-link
						>
					</div>
					<div class="group-body">
						<GruopCardSmall></GruopCardSmall>
					</div>
				</div>
				<div class="schedule-box">
					<span>오늘 일정</span>
					<div class="calendar-box">
						<calendar
							:calendars="calendarList"
							:schedules="scheduleList"
							:view="view"
							:taskView="taskView"
							:scheduleView="scheduleView"
							:theme="theme"
							:week="week"
							:month="month"
							:timezones="timezones"
							:disableDblClick="disableDblClick"
							:isReadOnly="isReadOnly"
							:template="template"
							:useCreationPopup="useCreationPopup"
							:useDetailPopup="useDetailPopup"
						/>
					</div>
				</div>
			</aside>
		</main>
		<InfiniteLoading
			@infinite="infiniteHandler"
			spinner="waveDots"
		></InfiniteLoading>
	</div>
</template>
<script>
import ArticleCard from '@/components/common/ArticleCard.vue';
import GruopCardSmall from '@/components/common/GruopCardSmall.vue';
// import { fetchFeeds } from '@/api/articles';
import { baseAuth } from '@/api/index';
// import { fetchStudy } from '@/api/studies';
import 'tui-calendar/dist/tui-calendar.css';
import Calendar from '@toast-ui/vue-calendar/src/Calendar.vue';
import 'tui-date-picker/dist/tui-date-picker.css';
import 'tui-time-picker/dist/tui-time-picker.css';
import cookies from 'vue-cookies';
import InfiniteLoading from 'vue-infinite-loading';
export default {
	components: {
		InfiniteLoading,
		ArticleCard,
		GruopCardSmall,
		Calendar,
	},

	data() {
		return {
			limit: 0,
			userName: cookies.get('name') ? cookies.get('name') : null,
			articles: null,
			calendarList: [],
			scheduleList: [],
			view: 'day',
			taskView: false,
			scheduleView: ['time'],
			theme: {
				'month.dayname.height': '30px',
				'month.dayname.borderLeft': '1px solid #ff0000',
				'month.dayname.textAlign': 'center',
				'month.schedule.borderRadius': '2px',
				'month.schedule.height': '24px',
			},
			week: {
				narrowWeekend: true,
				showTimezoneCollapseButton: true,
				timezonesCollapsed: false,
			},
			month: {
				visibleWeeksCount: 2,
			},
			timezones: [
				{
					timezoneOffset: 540,
					displayLabel: 'GMT+09:00',
					tooltip: 'Seoul',
				},
			],
			disableDblClick: true,
			isReadOnly: false,
			template: {
				milestone: function(schedule) {
					return `<span style="color:red;">${schedule.title}</span>`;
				},
				milestoneTitle: function() {
					return 'MILESTONE';
				},
			},
			useCreationPopup: true,
			useDetailPopup: true,
		};
	},
	methods: {
		infiniteHandler() {
			console.log('!!');
			// const { data } = await fetchFeeds();
			// setTimeout(() => {
			// 	if (data.length) {
			// 		this.articles = [...this.articles, data];
			// 		$state.loaded();
			// 		this.limit += 5;
			// 		if (this.articles.length / 5 === 0) {
			// 			$state.complete();
			// 		}
			// 	} else {
			// 		$state.complete();
			// 	}
			// }, 500);
		},

		async fetchScheduleData() {
			const { data } = await baseAuth.get(
				`/accounts/${this.userName}/myschedule/`,
			);
			this.calendarList = data.reduce((acc, el) => {
				acc.push({
					id: el.schedule.study_id,
					name: el.schedule.study_name,
				});
				return acc;
			}, []);

			this.scheduleList = data.reduce((acc, el, idx) => {
				acc.push({
					id: idx,
					calendarId: this.calendarList.findIndex(
						i => i.id === el.schedule.study_id,
					),
					title: el.schedule.title,
					category: 'time',
					dueDateClass: '',
					start: el.schedule.start,
					end: el.schedule.end,
					color: el.schedule.bg_color === '#dde6e8' ? '#000000' : '#ffffff',
					bgColor: el.schedule.bg_color,
					dragBgColor: el.schedule.bg_color,
					borderColor: el.schedule.bg_color,
				});
				return acc;
			}, []);
		},
	},
	created() {
		this.fetchScheduleData();
	},
};
</script>

<style lang="scss" scoped>
// Calendar
.tui-full-calendar-timegrid-container {
	height: 300px !important;
}
.tui-full-calendar-layout,
.tui-full-calendar-month {
	height: 300px !important;
	min-height: 300px !important;
}
.tui-full-calendar-month-dayname {
	border-top: none !important;
}

.newsFeed-container {
	display: grid;
	width: 100%;
	height: 100%;
	grid-template-rows: 100%;
	grid-template-columns: repeat(3, 33.33%);
	grid-template-areas: 'article article aside';
	overflow: hidden;
	@media screen and (max-width: 768px) {
		grid-template-columns: 1fr;
		grid-template-areas: 'article';
	}
	.article-box {
		grid-area: article;
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		overflow: hidden;
		@media screen and (max-width: 1150px) {
			flex-direction: row;
			flex-wrap: wrap;
			align-items: flex-start;
		}
	}
	.side-box {
		grid-area: aside;
		width: 100%;
		.schedule-box {
			width: 90%;
			margin: 5%;
			position: relative;
			padding-top: 2rem;
			span {
				padding: 0 15px;
				position: absolute;
				top: -10px;
				background: #fff;
			}
			.calendar-box {
			}
		}
		.group-box {
			// display: flex;
			width: 90%;
			margin: 5%;
			position: relative;
			span {
				padding: 0 15px;
				position: absolute;
				top: -10px;
				background: #fff;
			}
			.group-nav {
				width: 100%;
				height: $font-normal;
				position: relative;

				.moreGroup {
					position: absolute;
					right: 10px;
					text-decoration: none;
					color: black;
				}
			}
			.group-body {
				display: flex;
			}
		}
		@media screen and (max-width: 768px) {
			display: none;
		}
	}
}
</style>
