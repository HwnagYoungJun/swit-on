<template>
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
</template>

<script>
import bus from '@/utils/bus.js';
import 'tui-calendar/dist/tui-calendar.css';
import Calendar from '@toast-ui/vue-calendar/src/Calendar.vue';
import { baseAuth } from '@/api/index';
import 'tui-date-picker/dist/tui-date-picker.css';
import 'tui-time-picker/dist/tui-time-picker.css';

export default {
	components: {
		Calendar,
	},
	props: {
		userName: String,
	},
	created() {
		this.fetchData();
	},
	methods: {
		async fetchData() {
			try {
				const { data } = await baseAuth.get(
					`/accounts/${this.userName}/myschedule/`,
				);
				this.calendarList = data.reduce((acc, el) => {
					if (acc.findIndex(i => i.name === el.schedule.study_name) === -1) {
						acc.push({
							id: el.schedule.study_id,
							name: el.schedule.study_name,
						});
					}
					return acc;
				}, []);

				this.scheduleList = data.reduce((acc, el, idx) => {
					acc.push({
						id: idx,
						calendarId: el.schedule.study_id,
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
				bus.$emit('show:toast', `${error}`);
			}
		},
	},
	data() {
		return {
			calendarList: [],
			scheduleList: [],
			view: 'month',
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
			useCreationPopup: true,
			useDetailPopup: true,
		};
	},
};
</script>

<style>
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
</style>
