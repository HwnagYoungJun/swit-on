<template>
	<div>
		<div class="profile-box">
			<img
				class="img-box"
				src="https://scontent-ssn1-1.xx.fbcdn.net/v/t1.0-9/51195329_2188965328018448_6283346633593716736_n.jpg?_nc_cat=110&_nc_sid=85a577&_nc_ohc=oEFV7dFTDEwAX9IiKKx&_nc_ht=scontent-ssn1-1.xx&oh=91b2c49439a416691eccd803c50bd221&oe=5F4024D8"
				alt="profile_img"
			/>
			<div class="info-box">
				<div class="name-box">
					<img
						class="icon-box"
						src="https://www.shareicon.net/data/128x128/2015/09/27/108202_game_512x512.png"
						alt="icon_img"
					/>
					<h2>{{ name }}</h2>
					<router-link :to="{ name: 'modifyprofile' }">
						<div class="modify-profile">프로필 수정하기</div>
					</router-link>
				</div>
				<div class="middle-box">
					<div class="middle-element">
						<p>{{ studying }}</p>
						<p>진행중</p>
					</div>
					<div class="middle-element">
						<p>{{ studyed }}</p>
						<p>완료함</p>
					</div>
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
			studying: 5,
			studyed: 6,
		};
	},
};
</script>

<style lang="scss" scoped>
.profile-box {
	display: flex;
	margin-bottom: 1.5rem;
	@media screen and (max-width: 1024px) {
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
}
.info-box {
	display: flex;
	flex-direction: column;
	width: 100%;
	.modify-profile {
		border: 1px solid purple;
		border-radius: 0.3rem;
		margin-left: 3rem;
		padding: 0.5rem;
	}
	.middle-box {
		display: flex;
		align-items: center;
		height: 40%;
		margin: 1.5rem 0;
		.middle-element {
			display: flex;
			flex-direction: column;
			align-items: center;
			margin: 0 3rem;
			@media screen and (max-width: 1024px) {
				margin: 1.5rem 2rem;
			}
		}
		p {
			font-size: $font-bold * 0.9;
		}
		@media screen and (max-width: 1024px) {
			height: auto;
			margin: 0 auto;
			padding: 0;
		}
	}
	.name-box {
		display: flex;
		margin: 0;
		align-items: center;
		@media screen and (max-width: 1024px) {
			margin: 0 auto;
		}
	}

	h4 {
		margin-left: 1rem;
		@media screen and (max-width: 1024px) {
			text-align: center;
			margin-bottom: 1.5rem;
		}
	}
}
.img-box {
	width: 15rem;
	height: 15rem;
	border-radius: 50%;
	object-fit: cover;
	margin-right: 4rem;
	@media screen and (max-width: 1024px) {
		margin-right: 0;
	}
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
