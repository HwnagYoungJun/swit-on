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

// function checkExtension(fileExt) {
// 	var _fileExt = fileExt;
// 	var _result;

// 	// 미리보기 가능한 확장자는 배열에 넣기
// 	var _arrExt = new Array('.bmp', '.gif', '.jpg', '.png', '.jpeg');

// 	// 배열 요소를 검사하여 체크
// 	for (var i = 0; i < _arrExt.length; i++) {
// 		if (_arrExt[i] == _fileExt) {
// 			_result = true;
// 			break;
// 		} else _result = false;
// 	}

// 	return _result;
// }

// export function FilenameCheck(filename) {
// 	var _fileLen = filename.length;

// 	var _lastDot = filename.lastIndexOf('.');

// 	// 확장자 명만 추출한 후 소문자로 변경
// 	var _fileExt = filename.substring(_lastDot, _fileLen).toLowerCase();

// 	return checkExtension(_fileExt);
// }
