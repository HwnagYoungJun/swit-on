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
	console.log(typeof n);
	if (n & 64) {
		res.push('월');
	}
	if (n & 32) {
		res.push('화');
	}
	if (n & 16) {
		res.push('수');
	}
	if (n & 8) {
		res.push('목');
	}
	if (n & 4) {
		res.push('금');
	}
	if (n & 2) {
		res.push('토');
	}
	if (n & 1) {
		res.push('일');
	}
	return res;
}
