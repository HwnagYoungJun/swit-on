<template>
	<section class="container">
		<UpperBtn></UpperBtn>
		<main class="newsFeed-container">
			<section v-if="newsFeedData.articles" class="article-box">
				<router-link
					class="feed-link"
					v-for="article in newsFeedData.articles"
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
					<ArticleFeed :article="article" />
				</router-link>
			</section>
			<aside class="side-box">
				<div class="schedule-box">
					<p>오늘 일정</p>
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
	</section>
</template>
<script>
//Feeds
import ArticleFeed from '@/components/common/ArticleFeed.vue';
import { fetchFeeds } from '@/api/articles';
import { baseAuth } from '@/api/index';
// Calendar
import Calendar from '@toast-ui/vue-calendar/src/Calendar.vue';
import 'tui-calendar/dist/tui-calendar.css';
import 'tui-date-picker/dist/tui-date-picker.css';
import 'tui-time-picker/dist/tui-time-picker.css';

import cookies from 'vue-cookies';
import bus from '@/utils/bus';
import UpperBtn from '@/components/common/UpperBtn.vue';
export default {
	components: {
		ArticleFeed,
		Calendar,
		UpperBtn,
	},

	data() {
		return {
			loading: false,
			newsFeedData: {
				limit: 0,
				articles: [],
				windowTop: 0,
			},
			userName: cookies.get('name') ? cookies.get('name') : null,
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
			isReadOnly: true,
			template: {
				milestone: function(schedule) {
					return `<span style="color:red;">${schedule.title}</span>`;
				},
				milestoneTitle: function() {
					return 'MILESTONE';
				},
			},
			useCreationPopup: false,
			useDetailPopup: true,
		};
	},
	methods: {
		async infiniteLoading() {
			try {
				const { data } = await fetchFeeds(this.newsFeedData.limit);
				this.newsFeedData.limit += 5;
				if (data.length) {
					this.newsFeedData.articles = [...this.newsFeedData.articles, ...data];
				}
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async fetchFeedData() {
			try {
				this.loading = true;
				const { data } = await fetchFeeds(this.newsFeedData.limit);
				this.loading = false;
				this.newsFeedData.limit += 5;
				this.newsFeedData.articles = data;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async fetchScheduleData() {
			try {
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
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	computed: {
		isInfinite() {
			return (
				document.querySelector('body').scrollHeight >
				this.newsFeedData.windowTop + screen.height
			);
		},
	},
	watch: {
		isInfinite: function() {
			if (!this.isInfinite) {
				if (!this.windowTop == 0) {
					return;
				}
				this.infiniteLoading();
			}
		},
	},
	created() {
		this.fetchFeedData();
		this.fetchScheduleData();
		window.addEventListener('scroll', () => {
			this.newsFeedData.windowTop = window.scrollY;
		});
	},
	mounted() {
		document.title = '스윗온 뉴스피드';
	},
};
</script>

<style lang="scss">
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
	grid-template-columns: 66.6% 33.3%;
	grid-template-areas: 'article aside';
	overflow: hidden;
	@media screen and (max-width: 768px) {
		grid-template-columns: 1fr;
		grid-template-areas: 'article';
	}
	.article-box {
		grid-area: article;
		display: flex;
		width: 100%;
		flex-direction: column;
		align-items: flex-start;
		overflow: hidden;
		.feed-link {
			width: 100%;
		}
	}
	.side-box {
		grid-area: aside;
		width: 100%;
		.schedule-box {
			padding-left: 5%;
			width: 90%;
			margin: 5%;
			padding-top: 2rem;
			p {
				font-weight: bold;
				margin-bottom: 1rem;
			}
			.calendar-box {
				padding-left: 1rem;
				.tui-full-calendar-dayname-container {
					border-top: none !important;
					border-bottom: none !important;
				}
				.tui-full-calendar-timegrid-left {
					width: 2.4rem;
				}
				.tui-full-calendar-timegrid-right {
					margin-left: 2.4rem;
				}
			}
		}
		@media screen and (max-width: 768px) {
			display: none;
		}
		.tui-full-calendar-dayname-container {
			border-top: none !important;
			border-bottom: none !important;
		}
	}
}
</style>
