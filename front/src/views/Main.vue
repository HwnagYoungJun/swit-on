<template>
	<section>
		<!-- <h1>메인페이지</h1> -->
		<nav>
			<CategoryNavForm></CategoryNavForm>
		</nav>
		<section>
			<flickity ref="flickity" :options="flickityOptions">
				<div class="carousel-cell">
					<img src="@/assets/react.png" alt="" />
				</div>
				<div class="carousel-cell">
					<img src="@/assets/spring.png" alt="" />
				</div>
				<div class="carousel-cell">
					<img src="@/assets/django.png" alt="" />
				</div>
				<div class="carousel-cell">
					<img src="@/assets/spring.png" alt="" />
				</div>
				<div class="carousel-cell">
					<img src="@/assets/react.png" alt="" />
				</div>
			</flickity>

			<!-- if you don't want to use the buttons Flickity provides -->
			<!-- <button @click="previous()">Custom Previous Button</button>
			<button @click="next()">Custom Next Button</button> -->
			<!-- <h2>메인 content</h2> -->
			<!-- <div id="carousel">
				<div class="carousel-wrap">
					<div class="carousel-slider">
						<div class="carousel-content">
							리액트 혼자하기 어렵죠!
						</div>
						<div class="carousel-img">
							<img src="@/assets/react.png" alt="" />
						</div>
					</div>
					<div class="carousel-slider">
						<div class="carousel-content">
							스프링 같이 해요!!
						</div>
						<div class="carousel-img">
							<img src="@/assets/spring.png" alt="" />
						</div>
					</div>
					<div class="carousel-slider">
						<div class="carousel-content">
							리액트 혼자하기 어렵죠!
						</div>
						<div class="carousel-img">
							<img src="@/assets/react.png" alt="" />
						</div>
					</div>
					<div class="carousel-slider">
						<div class="carousel-content">
							django! 재밌어요
						</div>
						<div class="carousel-img">
							<img src="@/assets/django.png" alt="" />
						</div>
					</div>
					<div class="carousel-slider">
						<div class="carousel-content">
							리액트 혼자하기 어렵죠!
						</div>
						<div class="carousel-img">
							<img src="@/assets/react.png" alt="" />
						</div>
					</div>
				</div>
			</div> -->
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
import Flickity from 'vue-flickity';
import CategoryNavForm from '@/components/categorys/CategoryNavForm.vue';
export default {
	components: {
		Flickity,
		CategoryNavForm,
	},
	data() {
		return {
			flickityOptions: {
				initialIndex: 3,
				prevNextButtons: false,
				pageDots: true,
				wrapAround: true,
				// resize: true,
				contain: true,
			},
		};
	},
	methods: {
		next() {
			this.$refs.flickity.next();
		},

		previous() {
			this.$refs.flickity.previous();
		},
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
/* carousel */
#carousel {
	overflow: hidden;
}
.carousel-wrap {
	position: relative;
	width: 500%;
	margin: 0;
	left: 0;
	animation: 20s carousel infinite;
}
.carousel-slider {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 20%;
	height: 230px;
	float: left;
	background: #000;
}
.carousel-img {
	width: 40%;
}
.carousel-img img {
	width: 100%;
}
.carousel-content {
	width: 200px;
	height: 150px;
	/* border: 1px solid #fff; */
	color: #fff;
	text-align: start;
}
@keyframes carousel {
	0% {
		left: 0;
	}
	20% {
		left: 0;
	}
	25% {
		left: -100%;
	}
	45% {
		left: -100%;
	}
	50% {
		left: -200%;
	}
	70% {
		left: -200%;
	}
	75% {
		left: -300%;
	}
	95% {
		left: -300%;
	}
	100% {
		left: -400%;
	}
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
	.carousel-slider {
		flex-direction: column-reverse;
		justify-content: center;
		height: 180px;
	}
	.carousel-content {
		width: 40%;
		height: 30%;
		margin-top: 20px;
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
	.carousel-slider {
		height: 150px;
	}
}
.flickity-enabled {
	position: relative;
}

.flickity-enabled:focus {
	outline: none;
}

.flickity-viewport {
	overflow: hidden;
	position: relative;
	height: 100%;
}

.flickity-slider {
	position: absolute;
	width: 100%;
	height: 100%;
}

/* draggable */

.flickity-enabled.is-draggable {
	-webkit-tap-highlight-color: transparent;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.flickity-enabled.is-draggable .flickity-viewport {
	cursor: move;
	cursor: -webkit-grab;
	cursor: grab;
}

.flickity-enabled.is-draggable .flickity-viewport.is-pointer-down {
	cursor: -webkit-grabbing;
	cursor: grabbing;
}

/* ---- flickity-button ---- */

.flickity-button {
	position: absolute;
	background: hsla(0, 0%, 100%, 0.75);
	border: none;
	color: #333;
}

.flickity-button:hover {
	background: white;
	cursor: pointer;
}

.flickity-button:focus {
	outline: none;
	box-shadow: 0 0 0 5px #19f;
}

.flickity-button:active {
	opacity: 0.6;
}

.flickity-button:disabled {
	opacity: 0.3;
	cursor: auto;
	/* prevent disabled button from capturing pointer up event. #716 */
	pointer-events: none;
}

.flickity-button-icon {
	fill: currentColor;
}

/* ---- previous/next buttons ---- */

.flickity-prev-next-button {
	top: 50%;
	width: 44px;
	height: 44px;
	border-radius: 50%;
	/* vertically center */
	transform: translateY(-50%);
}

.flickity-prev-next-button.previous {
	left: 10px;
}
.flickity-prev-next-button.next {
	right: 10px;
}
/* right to left */
.flickity-rtl .flickity-prev-next-button.previous {
	left: auto;
	right: 10px;
}
.flickity-rtl .flickity-prev-next-button.next {
	right: auto;
	left: 10px;
}

.flickity-prev-next-button .flickity-button-icon {
	position: absolute;
	left: 20%;
	top: 20%;
	width: 60%;
	height: 60%;
}

/* ---- page dots ---- */

.flickity-page-dots {
	position: absolute;
	width: 100%;
	bottom: -25px;
	padding: 0;
	margin: 0;
	list-style: none;
	text-align: center;
	line-height: 1;
}

.flickity-rtl .flickity-page-dots {
	direction: rtl;
}

.flickity-page-dots .dot {
	display: inline-block;
	width: 10px;
	height: 10px;
	margin: 0 8px;
	background: #333;
	border-radius: 50%;
	opacity: 0.25;
	cursor: pointer;
}

.flickity-page-dots .dot.is-selected {
	opacity: 1;
}

/* ---- 화면비율 조절한다고 추가한 부분 ---- */
.flickity-viewport {
	width: 100%;
	height: 300px;
}
.carousel-cell {
	width: 100%;
	height: 100%;
}
.carousel-cell img {
	width: 100%;
	height: 100%;
	border-radius: 3px;
	/* object-fit: contain; */
}
@media screen and (min-width: 641px) and (max-width: 768px) {
	.flickity-viewport {
		height: 200px;
	}
}
@media screen and (max-width: 640px) {
	.flickity-viewport {
		height: 160px;
	}
}
</style>
