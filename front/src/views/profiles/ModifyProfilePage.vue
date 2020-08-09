<template>
	<form
		class="modify-container"
		autocomplete="off"
		@submit.prevent="modifyData"
	>
		<img class="img-box" id="img-box" :src="profileImgCom" alt="profile_img" />
		<div class="img-change">
			<input
				ref="inputFile"
				id="inputFile"
				type="file"
				@change="onChangeFile"
			/>
		</div>
		<h2>{{ email }}</h2>
		<div class="input-container">
			<div class="input-box">
				<span>이름</span>
				<input type="text" v-model="name" />
			</div>
			<div class="input-box">
				<span>소개</span>
				<textarea
					name=""
					id=""
					cols="10"
					rows="2"
					v-model="introduce"
				></textarea>
			</div>
		</div>
		<div class="hiddenMsg" v-if="!isVaildIntro">
			소개는 20자 이하입니다!
		</div>
		<div class="hiddenMsg" v-else></div>
		<button class="submit-btn" :disabled="!isVaildIntro">
			제출
		</button>
	</form>
</template>

<script>
import { baseAuth } from '@/api/index';
import cookies from 'vue-cookies';
export default {
	data() {
		return {
			swichFile: false,
			email: null,
			introduce: null,
			profileImg: null,
			name: this.$store.state.name
				? this.$store.state.name
				: cookies.get('name'),
		};
	},
	methods: {
		onChangeFile(e) {
			this.profileImg = e.target.value;
			var tempImg = this.$refs.inputFile.files[0];
			var reader = new FileReader();
			reader.readAsDataURL(tempImg);
			reader.onload = function() {
				document.querySelector('#img-box').src = reader.result;
			};
			this.swichFile = true;
		},
		async fetchData() {
			try {
				const { data } = await baseAuth.get(`accounts/${this.name}`);
				this.email = data.email;
				this.introduce = data.introduce;
				this.profileImg = data.profile_image;
			} catch (err) {
				console.log(err);
			}
		},
		async modifyData() {
			try {
				await baseAuth.put(`accounts/${this.name}`, {
					name: this.name,
					introduce: this.introduce,
					profile_image: this.profileImg,
				});
				console.log('변경성공');
				this.$router.push({ path: `profile/${this.name}` });
			} catch (err) {
				console.log(err);
			}
		},
	},
	created() {
		this.fetchData();
	},
	computed: {
		profileImgCom() {
			return this.swichFile
				? `${this.profileImg}`
				: `${this.baseURL}${this.profileImg}`;
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
.modify-container {
	border: 1px solid #ffe375;
	background: #fff6d2;
	padding: 1rem;
	border-radius: 2rem;
	width: 350px;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.img-box {
	width: 15rem;
	height: 15rem;
	border-radius: 50%;
	object-fit: cover;
}
.input-container {
	display: flex;
	flex-direction: column;
	justify-content: center;
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
		input {
			border: 1px solid black;
			width: 100%;
			height: 2rem;
		}
		textarea {
			width: 100%;
			height: 4rem;
			border: 1px solid black;
			resize: vertical;
		}
	}
}
.submit-btn {
	width: 10rem;
	height: 3rem;
	margin-top: 2rem;
	background: $btn-purple;
	color: white;
}
.hiddenMsg {
	height: 2rem;
}
</style>
