<template>
	<form @submit.prevent="submitForm" enctype="multipart/form-data">
		<div>
			<label for="name">name : </label>
			<input v-model="studyData.name" id="name" type="text" />
		</div>
		<div>
			<label for="description">description : </label>
			<textarea
				v-model="studyData.description"
				id="description"
				type="text"
				row="5"
			/>
		</div>
		<div>
			<label for="users_limit">users_limit : </label>
			<input v-model="studyData.users_limit" id="users_limit" type="number" />
		</div>
		<div>
			<label for="start_time">start_time : </label>
			<input v-model="studyData.start_time" id="start_time" type="time" />
		</div>
		<div>
			<label for="end_time">end_time: </label>
			<input v-model="studyData.end_time" id="end_time" type="time" />
		</div>
		<div>
			<label for="lowercategory_id">lowercategory_id : </label>
			<input
				v-model="studyData.lowercategory_id"
				id="lowercategory_id"
				type="text"
			/>
		</div>
		<div>
			<label for="start_term">start_term : </label>
			<input v-model="studyData.start_term" id="start_term" type="date" />
		</div>
		<div>
			<label for="end_term">end_term : </label>
			<input v-model="studyData.end_term" id="end_term" type="date" />
		</div>
		<div>
			<label for="isPrivate">공개설정 : </label>
			<label for="isPrivate">공개 </label>
			<input
				v-model="studyData.isPrivate"
				id="isPrivate"
				type="radio"
				value="0"
			/>
			<label for="isPrivate">비공개 </label>
			<input
				v-model="studyData.isPrivate"
				id="isPrivate"
				type="radio"
				value="1"
			/>
		</div>
		<div>
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
		<div>
			{{ studyData.week }}
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
				<span>첨부</span>
			</button>
			<input
				ref="inputFile"
				type="file"
				class="input_file"
				title="첨부"
				@change="onChangeFile"
			/>
		</div>
		<button type="submit">
			만들기
		</button>
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
				isPrivate: false,
				lowercategory_id: null,
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
				await createStudy(this.studyData);
				console.log(this.studyData);
				this.$router.push({ name: 'studies' });
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

<style></style>
