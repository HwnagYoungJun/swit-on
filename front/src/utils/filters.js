export function formatDate(value) {
	const day = new Date(value);
	const dateString = day.toLocaleDateString('ko-KR', {
		month: 'long',
		day: 'numeric',
	});

	const timeString = day.toLocaleTimeString('ko-KR', {
		hour: 'numeric',
		minute: 'numeric',
	});
	return `${dateString} ${timeString}`;
}

export function truncate(string) {
	return string.substr(0, 100) + '...';
}

export function formatWeekday(n) {
	let days = '';
	if (n & 64) {
		days += '월,';
	}
	if (n & 32) {
		days += '화,';
	}
	if (n & 16) {
		days += '수,';
	}
	if (n & 8) {
		days += '목,';
	}
	if (n & 4) {
		days += '금,';
	}
	if (n & 2) {
		days += '토,';
	}
	if (n & 1) {
		days += '일,';
	}
	days = days.substr(0, days.length - 1);

	return days;
}

export function fileDownload(file) {
	return file.replace('upload/', '');
}
