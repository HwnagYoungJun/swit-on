<template>
	<form
		class="modify-container"
		autocomplete="off"
		@submit.prevent="modifyData"
		enctype="multipart/form-data"
	>
		<section class="modify-header">
			<h2>프로필 변경</h2>
			<div class="study-btnbox">
				<button @click.prevent="$router.go(-1)" class="studyform-btn-cancle">
					취소
				</button>
				<button :disabled="!isVaildIntro" class="hide-btn">작성</button>
				<button
					:disabled="!isVaildIntro"
					class="studyform-btn-submit"
					type="submit"
				>
					작성
				</button>
			</div>
		</section>
		<article class="modify-main">
			<div class="imgimgimg">
				<div class="img-container">
					<img
						class="img-box"
						id="img-box"
						:src="profileImgCom"
						:alt="`${pastName}의 프로필 사진`"
					/>
				</div>
			</div>

			<div class="upload-btn_wrap">
				<input
					ref="inputFile"
					class="upload_text"
					readonly="readonly"
					@change="onChangeFile"
				/>
				<div class="btn-wrap">
					<button
						type="button"
						@click="emetyImg"
						class="profile-update-deleteBtn"
					>
						<span>기본이미지</span>
					</button>
					<button type="button" class="profile-update-addBtn">
						<span>첨부</span>
					</button>
					<input
						ref="inputFile"
						type="file"
						accept="image/*"
						class="input_file"
						@change="onChangeFile"
					/>
				</div>
			</div>

			<div class="input-container">
				<div class="modify-input">
					<label class="head-label" for="name">이름</label>
					<input id="name" type="text" v-model="name" />
				</div>
				<div class="modify-input">
					<label for="intro" class="head-label">소개</label>
					<textarea
						name="intro"
						id="intro"
						rows="2"
						v-model="introduce"
					></textarea>
				</div>
			</div>
			<div class="hiddenMsg" v-if="!isVaildIntro">
				(※ 소개는 20자 이하입니다.)
			</div>
			<div class="hiddenMsg" v-else></div>
		</article>
	</form>
</template>

<script>
import bus from '@/utils/bus.js';
import { baseAuth } from '@/api/index';
import { mapMutations } from 'vuex';
export default {
	props: {
		userName: String,
	},
	data() {
		return {
			swichFile: false,
			email: null,
			introduce: null,
			profileImg: null,
			name: this.$store.state.name ? this.$store.state.name : this.userName,
			pastName: null,
		};
	},
	methods: {
		...mapMutations(['setUserName']),
		emetyImg() {
			this.profileImg = null;
		},
		onChangeFile() {
			this.profileImg = this.$refs.inputFile.files[0];
			var tempImg = this.$refs.inputFile.files[0];
			var reader = new FileReader();
			reader.readAsDataURL(tempImg);

			reader.onload = function() {
				document.querySelector('#img-box').src = reader.result;
				this.swichFile = reader.result;
			};
		},
		async fetchData() {
			try {
				const { data } = await baseAuth.get(`accounts/${this.name}`);
				this.email = data.email;
				this.introduce = data.introduce;
				this.name = data.name;
				this.profileImg = data.profile_image;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
		async modifyData() {
			try {
				const formdata = new FormData();
				formdata.append('name', this.name);
				formdata.append('introduce', this.introduce);
				formdata.append('img', this.profileImg);
				await baseAuth.put(`accounts/${this.pastName}`, formdata);
				this.setUserName(this.name);
				this.$cookies.set('name', this.name);
				this.$router.push(`profile/${this.name}`);
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	created() {
		this.pastName = this.name;
		this.fetchData();
	},
	watch: {
		$route() {
			this.pastName = this.name;
			this.fetchData();
		},
	},

	computed: {
		profileImgCom() {
			return this.swichFile !== false
				? this.profileImg === null
					? `${this.baseURL}upload/noProfile.png`
					: this.profileImg === undefined
					? `${this.baseURL}upload/noProfile.png`
					: this.swichFile
				: this.profileImg !== null
				? this.profileImg === undefined
					? `${this.baseURL}upload/noProfile.png`
					: `${this.baseURL}${this.profileImg}`
				: `${this.baseURL}upload/noProfile.png`;
		},
		baseURL() {
			return process.env.VUE_APP_API_URL;
		},
		isVaildIntro() {
			var lenIntro = 0;
			if (this.introduce !== null) {
				lenIntro = this.introduce.length;
			}
			var isVaild = lenIntro < 21 ? true : false;
			return isVaild;
		},
	},
};
</script>

<style lang="scss" scoped>
.modify-text {
	text-align: center;
	font-size: $font-bold;
}
.modify-container {
	width: 70%;
	margin: 0 auto 3rem;
	height: 100%;
	@media screen and (max-width: 768px) {
		width: 95%;
	}
}
.modify-header {
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
		:disabled {
			display: none;
		}
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
		:disabled {
			background-color: gray;
		}
	}
}
.modify-main {
	box-shadow: 0 2px 6px 0 rgba(68, 67, 68, 0.4);
	padding: 1rem;
	border-radius: 4px;
}
.imgimgimg {
	display: flex;
	justify-content: center;
}
.img-container {
	width: 15rem;
	height: 15rem;
	border-radius: 50%;
	border: 1px solid black;
}
.img-box {
	width: 15rem;
	height: 15rem;
	border-radius: 50%;
	object-fit: cover;
}
.input-container {
	width: 100%;
	.input-box {
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 1rem;

		span {
			padding-left: 0.5rem;
			margin-right: 1rem;
			margin-bottom: 0.5rem;
		}
	}
	.modify-input {
		margin-top: 0.5rem;
		input,
		textarea {
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
	}
}
.head-label {
	display: block;
	font-weight: 600;
}
.hiddenMsg {
	height: 1rem;
	padding-left: 0.5rem;
	color: red;
}
input.upload_text {
	flex: 1;
	height: 2rem;
	margin-top: 1rem;
}
div.upload-btn_wrap {
	@media screen and (max-width: 1024px) {
		display: flex;
		flex-direction: column;
		justify-content: center;
		margin-top: 0;
	}
}

div.upload-btn_wrap {
	position: relative;
	display: flex;
	align-items: center;
	width: 100%;
	height: 2rem;
	padding-left: 3px;
	margin-top: 1rem;
	padding-left: 1rem;
}
div.upload-btn_wrap button {
	@include scale(width, 70px);
	height: 2rem;
	font-weight: bold;
	background: rgb(225, 225, 225);
	border: none;
	border-radius: 3px;
	color: rgb(150, 149, 149);
	margin-top: 1rem;
}
.btn-wrap {
	width: 100%;
	min-height: 30px;
	position: relative;
	.profile-update-deleteBtn {
		position: absolute;
		top: -10px;
		@include scale(right, 90px);
		width: 90px;
		@media screen and (max-width: 640px) {
			width: 80px;
			margin-right: 10px;
		}
	}
	.profile-update-addBtn {
		position: absolute;
		top: -10px;
		right: 10px;
		width: 60px;
		@media screen and (max-width: 640px) {
			width: 40px;
			right: 5px;
		}
	}
	.input_file {
		position: absolute;
		top: 0;
		right: 10px;
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
}
</style>
