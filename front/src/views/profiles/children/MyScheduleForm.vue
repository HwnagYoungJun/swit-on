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
import 'tui-calendar/dist/tui-calendar.css';
import Calendar from '@toast-ui/vue-calendar/src/Calendar.vue';

// If you use the default popups, use this.
import 'tui-date-picker/dist/tui-date-picker.css';
import 'tui-time-picker/dist/tui-time-picker.css';

export default {
	components: {
		Calendar,
	},
	data() {
		return {
			calendarList: [
				{
					id: '0',
					name: 'home',
				},
				{
					id: '1',
					name: 'office',
				},
			],
			scheduleList: [
				{
					id: '1',
					calendarId: '1',
					title: 'my schedule',
					category: 'time',
					dueDateClass: '',
					start: '2020-07-27T12:30:00+09:00',
					end: '2020-07-31T17:31:00+09:00',
					color: '#ffffff',
					bgColor: '#ff5583',
					dragBgColor: '#ff5583',
					borderColor: '#ff5583',
				},
				{
					id: '2',
					calendarId: '1',
					title: 'second schedule',
					category: 'time',
					dueDateClass: '',
					start: '2020-08-01T12:30:00+09:00',
					end: '2020-08-05T17:31:00+09:00',
					color: '#ffffff',
					bgColor: '#ff5583',
					dragBgColor: '#ff5583',
					borderColor: '#ff5583',
				},
			],
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
