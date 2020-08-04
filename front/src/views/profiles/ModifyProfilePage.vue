<template>
	<form class="modify-container">
		<img
			class="img-box"
			src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.0-9/51195329_2188965328018448_6283346633593716736_n.jpg?_nc_cat=110&_nc_sid=85a577&_nc_ohc=oEFV7dFTDEwAX9IiKKx&_nc_ht=scontent-ssn1-1.xx&oh=91b2c49439a416691eccd803c50bd221&oe=5F4024D8"
			alt="profile_img"
		/>
		<h2>{{ email }}</h2>
		<div class="input-box">
			<label>이름</label>
			<input type="text" v-model="name" />
		</div>
		<div class="input-box">
			<span>소개</span>
			<textarea name="" id="" cols="10" rows="2" v-model="introduce"></textarea>
		</div>
		<button>
			제출
		</button>
	</form>
</template>

<script>
export default {
	data() {
		return {
			email: 'dudghkd1@naver.com',
			name: '가나다라마바사',
			introduce: '20자미만만쓰세요',
		};
	},
};
</script>

<style lang="scss" scoped>
.modify-container {
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
.input-box {
	display: flex;
	align-items: flex-start;

	input {
		border: 1px solid black;
	}
	textarea {
		border: 1px solid black;
		resize: vertical;
	}
}
</style>
