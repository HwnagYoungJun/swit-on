<template>
	<section>
		<!-- <h1>메인페이지</h1> -->
		<nav>
			<CategoryNavForm></CategoryNavForm>
		</nav>
		<section>
			<div class="popular">
				<h3>인기 소모임</h3>
				<div class="popular-wrap">
					<router-link :to="`/study/${1}`">
						<div class="popular-study">
							<div class="popular-study-logo">
								<img src="@/assets/color.png" alt="study" />
							</div>
							<hr class="popular-only-desktop" />
							<div class="popular-content">
								<p class="popular-content-name">vue.js 뿌수자</p>
								<div class="popular-content-description">
									<p>매주 월 수 9시-11시</p>
									<p>모집: ~ 20.07.20</p>
								</div>
							</div>
						</div>
					</router-link>
					<router-link :to="`/study/${1}`">
						<div class="popular-study">
							<div class="popular-study-logo">
								<img src="@/assets/color2.png" alt="study" />
							</div>
							<hr class="popular-only-desktop" />
							<div class="popular-content">
								<p class="popular-content-name">vue.js 뿌수자</p>
								<div class="popular-content-description">
									<p>매주 월 수 9시-11시</p>
									<p>모집: ~ 20.07.20</p>
								</div>
							</div>
						</div>
					</router-link>
					<router-link :to="`/study/${1}`">
						<div class="popular-study">
							<div class="popular-study-logo">
								<img src="@/assets/dd.png" alt="study" />
							</div>
							<hr class="popular-only-desktop" />
							<div class="popular-content">
								<p class="popular-content-name">vue.js 뿌수자</p>
								<div class="popular-content-description">
									<p>매주 월 수 9시-11시</p>
									<p>모집: ~ 20.07.20</p>
								</div>
							</div>
						</div>
					</router-link>
					<router-link :to="`/study/${1}`">
						<div class="popular-study">
							<div class="popular-study-logo">
								<img src="@/assets/black.png" alt="study" />
							</div>
							<hr class="popular-only-desktop" />
							<div class="popular-content">
								<p class="popular-content-name">vue.js 뿌수자</p>
								<div class="popular-content-description">
									<p>매주 월 수 9시-11시</p>
									<p>모집: ~ 20.07.20</p>
								</div>
							</div>
						</div>
					</router-link>
					<router-link :to="`/study/${1}`">
						<div class="popular-study">
							<div class="popular-study-logo">
								<img src="@/assets/black.png" alt="study" />
							</div>
							<hr class="popular-only-desktop" />
							<div class="popular-content">
								<p class="popular-content-name">vue.js 뿌수자</p>
								<div class="popular-content-description">
									<p>매주 월 수 9시-11시</p>
									<p>모집: ~ 20.07.20</p>
								</div>
							</div>
						</div>
					</router-link>
				</div>
			</div>
		</section>
	</section>
</template>

<script>
import CategoryNavForm from '@/components/categorys/CategoryNavForm.vue';
export default {
	components: {
		CategoryNavForm,
	},
};
</script>

<style>
.sub-nav {
	display: flex;
	flex-wrap: wrap;
	list-style-type: none;
	padding-left: 0;
	margin-bottom: 30px;
}
.sub-nav li {
	margin: 0 5px;
	padding: 7px 15px;
	border-radius: 20px;
	color: rgb(94, 94, 94);
	box-shadow: 2px 2px 6px rgba(165, 165, 165, 0.7);
}
.sub-nav li:hover {
	font-weight: bold;
	cursor: pointer;
}

/* popular-study */
.popular {
	margin-top: 50px;
}
.popular h3 {
	padding: 5px;
	border-left: 2.5px solid red;
	text-align: start;
	font-size: 0.9em;
	line-height: 0.8;
}
.popular-wrap {
	display: grid;
	width: 100%;
	height: 100%;
	grid-gap: 10px;
	grid-template-columns: repeat(12, 1fr);
}

.popular-wrap a {
	grid-column: span 3;
}
.popular-study {
	display: grid;
	place-items: center;
	border: 1px solid transparent;
	border-radius: 4px;
	box-shadow: 3px 3px 7px rgba(131, 131, 131, 0.5);
}
.popular-study:hover {
	transform: scale(1.02);
	cursor: pointer;
}
.popular-study-logo {
	width: 80%;
}
.popular-study-logo img {
	width: 90%;
}
.popular-study hr {
	width: 80%;
	height: 2px;
	border: none;
	background: linear-gradient(
		to right,
		rgba(255, 137, 2),
		rgba(238, 80, 70),
		rgba(233, 61, 96)
	);
}
.popular-content {
	padding: 5% 10%;
	/* text-align: start; */
}
.popular-content p {
	margin: 5px 0;
	font-size: 0.8em;
	color: rgb(94, 94, 94);
}
p.popular-content-name {
	margin-bottom: 15px;
	font-size: 1em;
	color: rgb(15, 15, 15);
}
@media screen and (max-width: 768px) {
	.sub-nav li {
		margin: 3px 5px;
		padding: 7px 13px;
		font-size: 0.7em;
	}
	.popular-wrap {
		display: flex;
		flex-direction: column;
	}
	.popular-study {
		display: flex;
		width: 100%;
		margin: 0;
		box-shadow: 2px 2px 6px rgba(167, 167, 167, 0.5);
	}
	.popular-study-logo {
		flex: 1;
		display: grid;
		place-items: center;
	}
	.popular-study-logo img {
		width: 50px;
		height: 50px;
		margin: 10px;
	}
	.popular-content {
		flex: 3;
		display: flex;
		justify-content: space-between;
	}
	.popular-only-desktop {
		display: none;
	}
}
@media screen and (max-width: 640px) {
	.popular-content-description {
		display: none;
	}
}
</style>
