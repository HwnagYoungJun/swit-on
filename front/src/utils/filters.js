export function formatDate(value) {
	const day = new Date(value);
	const dateString = day.toLocaleDateString('ko-KR', {
		// year: 'numeric',
		month: 'long',
		day: 'numeric',
	});

	const timeString = day.toLocaleTimeString('ko-KR', {
		hour: 'numeric',
		minutes: 'numeric',
	});
	return `${dateString} ${timeString}`;
}

export function truncate(string) {
	return string.substr(0, 100) + '...';
}

export function formatWeekday(n) {
	let res = [];
	let days = '';
	if (n & 64) {
		res.push('월');
		days += '월 ';
	}
	if (n & 32) {
		res.push('화');
		days += '화 ';
	}
	if (n & 16) {
		res.push('수');
		days += '수 ';
	}
	if (n & 8) {
		res.push('목');
		days += '목 ';
	}
	if (n & 4) {
		res.push('금');
		days += '금 ';
	}
	if (n & 2) {
		res.push('토');
		days += '토 ';
	}
	if (n & 1) {
		res.push('일');
		days += '일 ';
	}

	return days;
}

export function fileDownload(file) {
	return file.replace('upload/', '');
}
