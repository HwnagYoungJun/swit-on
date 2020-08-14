<template>
	<div>
		<article class="badge orange">
			<div class="rounded"><i class="fab fa-html5"></i></div>
		</article>
		<article class="badge blue">
			<div class="rounded"><i class="fab fa-css3-alt"></i></div>
		</article>
	</div>
</template>

<script>
export default {};
</script>

<style>
.wrapper {
	width: 90%;
	max-width: 900px;
	margin: 3em auto;
	text-align: center;
}

/* Badge */
.badge {
	margin: 1.5em 3em;
	width: 6.4em;
	height: 6.4em;
	border-radius: 20%;
	display: inline-block;
	top: 0;
	transition: all 0.2s ease;
	box-shadow: 8px 10px 12px 3px rgba(0, 0, 0, 0.43);
}

.rounded {
	width: 70px;
	height: 70px;
	background: white;
	position: absolute;
	margin: 15px 16px auto;
	z-index: 10;
	border-radius: 30%;
	box-shadow: 8px 8px 8px 2px rgba(0, 0, 0, 0.23);
}

/* Colors */
.blue {
	background: dodgerblue;
	color: deepskyblue;
}

.orange {
	background: darkorange;
	color: orange;
}

.gold {
	background: gold;
	color: gold;
}

.red {
	background: red;
	color: red;
}

.purple {
	background: mediumpurple;
	color: slategray;
}

.green {
	background: yellowgreen;
	color: darkslategray;
}

.crimson {
	background: crimson;
	color: firebrick;
}

.steel {
	background: steelblue;
	color: gold;
}

.pink {
	background: hotpink;
	color: palevioletred;
}

.rebecca {
	background: rebeccapurple;
	color: rebeccapurple;
}

.gainsboro {
	background: gainsboro;
	color: orangered;
}
</style>
