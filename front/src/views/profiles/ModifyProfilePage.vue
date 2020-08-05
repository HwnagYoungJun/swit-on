<template>
	<form
		class="modify-container"
		autocomplete="off"
		@submit.prevent="modifyData"
	>
		<img
			class="img-box"
			src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.0-9/51195329_2188965328018448_6283346633593716736_n.jpg?_nc_cat=110&_nc_sid=85a577&_nc_ohc=oEFV7dFTDEwAX9IiKKx&_nc_ht=scontent-ssn1-1.xx&oh=91b2c49439a416691eccd803c50bd221&oe=5F4024D8"
			alt="profile_img"
		/>
		<div class="img-change">
			<input type="file" />
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
import axios from 'axios';

export default {
	data() {
		return {
			email: null,
			name: null,
			introduce: null,
		};
	},
	method: {
		// 태인이형님이 만들어 두신걸 알지만 제가 만듭니다 ㅎㅎ
		async fetchData() {
			try {
				const res = await axios.get('http://200.20.20.20/accounts/');
				this.email = res.email;
				this.name = res.name;
				this.introduce = res.introduce;
			} catch (err) {
				console.log(err.msg);
			}
		},
		async modifyData() {
			try {
				await axios.put('유알엘을 씁시다', {
					name: this.name,
					introduce: this.introduce,
				});
			} catch (err) {
				console.log(err);
			}
		},
	},
	computed: {
		isVaildIntro() {
			var lenIntro = 0;
			if (this.introduce !== null) {
				lenIntro = this.introduce.length;
			}
			var isVaild = lenIntro < 21 ? true : false;
			return isVaild;
		},
	},
	created() {
		this.fetchData();
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
