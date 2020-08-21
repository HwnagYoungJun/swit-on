export function upperCategoryId(name) {
	var categoryId = 0;
	var categoryArray = ['웹', '모바일', '프로그래밍언어', 'AI', 'CS', '언어'];
	switch (name) {
		case categoryArray[0]:
			categoryId = 1;
			break;
		case categoryArray[1]:
			categoryId = 2;
			break;
		case categoryArray[2]:
			categoryId = 3;
			break;
		case categoryArray[3]:
			categoryId = 4;
			break;
		case categoryArray[4]:
			categoryId = 5;
			break;
		case categoryArray[5]:
			categoryId = 6;
			break;
	}
	return categoryId;
}
export function lowerCategoryId(lowerCategoryString) {
	switch (lowerCategoryString) {
		case 'Django':
			return 1;
		case 'Spring':
			return 2;
		case 'Node.js':
			return 3;
		case 'Vue':
			return 4;
		case 'React':
			return 5;
		case 'html과CSS':
			return 6;
		case 'JS':
			return 7;
		case 'DB':
			return 8;
		case '안드로이드':
			return 10;
		case 'IOS':
			return 11;
		case '플러터':
			return 12;
		case '리액트네이티브':
			return 13;
		case '파이썬':
			return 15;
		case 'C':
			return 16;
		case 'C++':
			return 18;
		case 'Java':
			return 19;
		case '코틀린':
			return 20;
		case '스위프트':
			return 21;
		case 'R':
			return 22;
		case '머신러닝':
			return 24;
		case '딥러닝':
			return 25;
		case 'opencv':
			return 26;
		case '텐서플로':
			return 27;
		case '케라스':
			return 28;
		case '자료구조':
			return 30;
		case '알고리즘':
			return 31;
		case '네트워크':
			return 32;
		case '컴퓨터구조':
			return 33;
		case '소프트웨어공학':
			return 34;
		case '운영체제':
			return 35;
		case '영어':
			return 37;
		case '한국어':
			return 38;
		case '중국어':
			return 39;
		case '일본어':
			return 40;
	}
}
