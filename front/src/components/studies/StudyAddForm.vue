<template>
	<form
		class="studyform"
		@submit.prevent="submitForm"
		enctype="multipart/form-data"
	>
		<div class="studyform-header">
			<h2>스터디 생성</h2>
			<div class="study-btnbox">
				<button @click.prevent="$router.go(-1)" class="studyform-btn-cancle">
					취소
				</button>
				<button class="hide-btn">작성</button>
				<button class="studyform-btn-submit" type="submit">작성</button>
			</div>
		</div>
		<section class="studyform-main">
			<div class="studyform-title">
				<label class="head-label" for="name">이름</label>
				<input
					class="studyform-input"
					v-model="studyData.name"
					id="name"
					type="text"
					required
				/>
			</div>
			<div class="studyform-content">
				<label class="head-label" for="description">소개</label>
				<textarea
					v-model="studyData.description"
					id="description"
					class="studyform-input"
					type="text"
					row="8"
					required
				/>
			</div>
			<div class="studyform-userlimit">
				<label class="head-label" for="users_limit">제한 인원</label>
				<input
					class="studyform-input"
					v-model="studyData.users_limit"
					id="users_limit"
					type="number"
					min="2"
					max="20"
					required
				/>
			</div>
			<div class="studyform-time">
				<label for="start_time" class="head-label">시간</label>
				<input
					v-model="studyData.start_time"
					id="start_time"
					type="time"
					required
				/>
				<label for="end_time">부터</label>
				<input
					v-model="studyData.end_time"
					id="end_time"
					type="time"
					required
				/>
				<label>까지</label>
			</div>
			<div>
				<label for="uppercategory_id" class="head-label">카테고리</label>
				<select
					v-model="studyData.uppercategory_id"
					id="uppercategory_id"
					required
				>
					<option value="1">웹</option>
					<option value="2">모바일</option>
					<option value="3">프로그래밍 언어</option>
					<option value="4">AI</option>
					<option value="5">CS</option>
					<option value="6">언어</option>
				</select>
			</div>
			<div class="studyform-term">
				<label for="start_term" class="head-label">일정</label>
				<input
					v-model="studyData.start_term"
					id="start_term"
					type="date"
					required
				/>
				<label for="end_term">부터</label>
				<input
					v-model="studyData.end_term"
					id="end_term"
					type="date"
					required
				/>
				<label>까지</label>
			</div>
			<div class="studyform-private">
				<label class="head-label" for="isPrivate">공개설정</label>
				<input
					v-model="studyData.isPrivate"
					id="isNotPrivate"
					type="radio"
					value="0"
				/>
				<label for="isNotPrivate">공개</label>
				<input
					v-model="studyData.isPrivate"
					id="isPrivate"
					type="radio"
					value="1"
				/>
				<label for="isPrivate">비공개</label>
			</div>
			<div class="studyform-week">
				<label class="head-label">요일</label>
				<input type="checkbox" id="Mon" value="64" v-model="weekData" />
				<label for="Mon">월</label>
				<input type="checkbox" id="Tue" value="32" v-model="weekData" />
				<label for="Tue">화</label>
				<input type="checkbox" id="Wed" value="16" v-model="weekData" />
				<label for="Wed">수</label>
				<input type="checkbox" id="Thu" value="8" v-model="weekData" />
				<label for="Thu">목</label>
				<input type="checkbox" id="Fri" value="4" v-model="weekData" />
				<label for="Fri">금</label>
				<input type="checkbox" id="Sat" value="2" v-model="weekData" />
				<label for="Sat">토</label>
				<input type="checkbox" id="Sun" value="1" v-model="weekData" />
				<label for="Sun">일</label>
			</div>
			<div class="upload-btn_wrap">
				<input
					v-model="fileRoute"
					type="text"
					class="upload_text"
					readonly="readonly"
					placeholder="첨부된 파일이 없습니다."
				/>
				<button type="button" title="첨부">
					<span>로고</span>
				</button>
				<input
					ref="inputFile"
					type="file"
					class="input_file"
					title="첨부"
					@change="onChangeFile"
				/>
			</div>
		</section>
	</form>
</template>

<script>
import { createStudy } from '@/api/studies';
export default {
	data() {
		return {
			fileRoute: '',
			weekData: [],
			studyData: {
				name: null,
				description: null,
				week: null,
				isRecruit: 1,
				img: null,
				isPrivate: 0,
				lowercategory_id: 2,
				start_term: null,
				end_term: null,
				start_time: null,
				end_time: null,
				users_limit: null,
			},
		};
	},

	methods: {
		weekCalculator() {
			this.studyData.week = this.weekData.reduce(
				(acc, cur) => acc + parseInt(cur),
				0,
			);
		},
		async submitForm() {
			try {
				this.weekCalculator();
				this.studyData.users_limit = parseInt(this.studyData.users_limit);
				await createStudy(this.studyData);
				this.$router.push({ name: 'main' });
			} catch (e) {
				console.log(e);
			}
		},
		onChangeFile(e) {
			this.fileRoute = e.target.value;
			this.studyData.img = this.$refs.inputFile.files[0];
		},
	},
};
</script>

<style lang="scss">
.studyform {
	width: 70%;
	margin: 0 auto 3rem;
	height: 100%;
	@media screen and (max-width: 768px) {
		width: 95%;
	}
}

.studyform-header {
	display: flex;
	position: relative;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1rem;
	.studyform-btn-cancle {
		@include form-btn('white');
		margin-right: 5px;
	}
	.studyform-btn-submit {
		@include form-btn('purple');
		position: relative;
		right: 0;
	}
	.hide-btn {
		border: none;
		border-radius: 3px;
		height: 40px;
		padding: 0 1.125rem;
		font-size: 1rem;
		font-weight: 700;
		text-decoration: none solid #000;
		background: #000;
		color: #fff;
		position: absolute;
		right: 0;
	}
}
.studyform-main {
	box-shadow: 0 2px 6px 0 rgba(68, 67, 68, 0.4);
	padding: 1rem;
	// padding: 1rem 1rem 0;
	border-radius: 4px;
	div {
		margin-bottom: 0.75rem;
	}
	input[type='radio'] {
		margin-right: 0.15rem;
		margin-left: 0.75rem;
	}
	input[type='checkbox'] {
		margin-right: 0.15rem;
		margin-left: 0.5rem;
	}
	.studyform-input {
		width: 100%;
		padding: 10px;
		border: none;
		border-radius: 0;
		border-bottom: 1px solid black;
		&:focus {
			outline: none;
			border-bottom: 1px solid black;
		}
	}
	textarea {
		height: 10rem;
	}
}
.head-label {
	display: block;
	font-weight: 600;
}
input.upload_text {
	flex: 1;
	height: 2rem;
	// padding: 0 0.5rem 1rem;
	// border-top: 1px solid #bbb;
	margin-top: 1rem;
	// margin-bottom: 1rem;
}
div.upload-btn_wrap input.input_file {
	/*파일찾기 폼 투명하게*/
	position: absolute;
	top: 0;
	right: 0;
	@include scale(width, 75px);
	opacity: 0;
	filter: alpha(opacity=0);
	-ms-filter: 'alpha(opacity=0)';
	-moz-opacity: 0;
	margin-top: 0.6rem;
	&:hover {
		cursor: pointer;
	}
}
div.upload-btn_wrap {
	position: relative;
	display: flex;
	align-items: center;
	width: 100%; /*width, height 값은 button(찾아보기)값과 같아야함 */
	height: 2rem;
	border-top: 1px solid #bbb;
	padding-left: 3px;
	margin-top: 1rem;
}
div.upload-btn_wrap button {
	@include scale(width, 70px);
	height: 2rem;
	font-weight: bold;
	background: rgb(225, 225, 225);
	// border: 1px solid #333;
	border: none;
	border-radius: 3px;
	color: rgb(150, 149, 149);
	margin-top: 1rem;
}
</style>
