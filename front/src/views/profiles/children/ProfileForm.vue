<template>
	<div>
		<div class="profile-box">
			<div>
				<img
					class="img-box"
					src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.0-9/51195329_2188965328018448_6283346633593716736_n.jpg?_nc_cat=110&_nc_sid=85a577&_nc_ohc=oEFV7dFTDEwAX9IiKKx&_nc_ht=scontent-ssn1-1.xx&oh=91b2c49439a416691eccd803c50bd221&oe=5F4024D8"
					alt="profile_img"
				/>
			</div>
			<div class="info-box">
				<div class="name-box">
					<img
						class="icon-box"
						src="https://www.shareicon.net/data/128x128/2015/09/27/108202_game_512x512.png"
						alt="icon_img"
					/>
					<h2>{{ name }}</h2>
					<div class="modify-profile">프로필 수정하기</div>
				</div>
				<h4>
					{{ introduce }}
				</h4>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			// 추후 DB에서 오는 데이터
			name: 'HWANG YJ',
			introduce: '20자 이내의 단어만 쓸 수 있습니다!!',
		};
	},
};
</script>

<style lang="scss" scoped>
.profile-box {
	display: flex;
	margin-bottom: 1.5rem;
}
.info-box {
	display: flex;
	flex-direction: column;
	width: 100%;
	.modify-profile {
		border: 1px solid purple;
		border-radius: 0.3rem;
		margin-left: 2rem;
		padding: 0.5rem;
		display: flex;
		align-items: center;
	}
	.name-box {
		display: flex;
		margin: 0 auto;
	}

	h4 {
		margin-left: 30px;
		margin-top: 30px;
	}
}
.img-box {
	width: 15rem;
	border-radius: 50%;
	// overflow: hidden;
	object-fit: cover;
}
.icon-box {
	display: flex;
	width: 50px;
	height: 50px;
	overflow: hidden;
}
h2 {
	height: 30px;
	display: flex;
	align-items: center;
	border-bottom: 2px solid black;
}
</style>
