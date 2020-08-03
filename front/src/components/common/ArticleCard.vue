<template>
	<div class="card">
		<div class="card-info">
			<p class="card-info-title">파이썬 어려워</p>
			<div>
				<span class="study-info">
					<img src="@/assets/color.png" alt="" />
					<span>python 소모임</span>
				</span>
				<span class="user-info">
					<img src="@/assets/dd.png" alt="" />
					<span>kim</span>
				</span>
				<p>3시간 전</p>
			</div>
		</div>
		<div class="card-content">
			<p>
				Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus
				repellendus aliquid esse sapiente harum excepturi ab assumenda amet
				veniam? Eum repudiandae cupiditate reiciendis ipsum architecto iusto
				repellat voluptate, alias qui? lor
			</p>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.card {
	width: 505px;
	margin: 10px 5px 50px;
	padding: 15px;
	border-bottom: 1px solid rgb(228, 228, 228);
	.card-info {
		margin-bottom: 30px;
		font-size: $font-light;
		position: relative;
		.card-info-title {
			margin-bottom: 8px;
			font-size: $font-bold;
		}
		div {
			color: rgb(90, 90, 90);
			.study-info {
				display: flex;
				// flex-direction: column;
				// justify-content: center;
				align-items: center;
				img {
					margin-right: 5px;
					width: 20px;
				}
			}
			.user-info {
				display: flex;
				flex-direction: column;
				align-items: center;
				position: absolute;
				top: 10px;
				right: 10px;
				img {
					width: 50px;
				}
			}
			p {
				margin-top: 10px;
			}
		}
	}
}
</style>
