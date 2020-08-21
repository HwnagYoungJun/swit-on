<template>
	<section class="InputBox">
		<div v-if="title" class="input-box">
			<label :for="`input-box-${this.userInfo}`" :class="[name_class, color]">
				{{ title }}
			</label>
		</div>
		<input
			:type="type"
			:placeholder="text"
			:name="userInfo"
			:id="`input-box-${this.userInfo}`"
			:class="[input_class, color]"
			v-model="inputData"
			@input="inputChange(inputData)"
		/>
		<p v-if="message" v-html="message" :class="[message_class, color]"></p>
	</section>
</template>

<script>
import cookies from 'vue-cookies';

export default {
	data() {
		return {
			inputData: this.isSave ? cookies.get('id') : '',
			input_class: 'input-box-input',
			name_class: 'input-box-color',
			message_class: 'input-box-message',
		};
	},
	props: {
		isSave: Boolean,
		type: String,
		color: String,
		text: String,
		title: String,
		message: null,
		userInfo: String,
		inputChange: Function,
	},
};
</script>

<style lang="scss">
.InputBox {
	.input-box {
		background-color: #fff;
		margin-top: 1rem;
		margin-bottom: 5px;
		.input-box-color {
			font-size: $font-normal;
			font-weight: 400;
			letter-spacing: -0.2px;
			background-color: white;
		}
	}
	.input-box-input {
		@include scale(width, 400px);
		margin-bottom: 5px;
		padding: 1rem;
		border: 1px solid #666;
		border-radius: 3px;
		line-height: 1rem;
		font-size: $font-normal;
		text-decoration: none solid #111;
		color: #111;
	}
	.input-box-message {
		font-size: $font-light;
		display: flex;
		align-items: center;
		padding: 0;
	}
}
.green {
	border-color: #42b983 !important;
	color: #42b983;
	font-weight: bold;
	outline-color: #42b983;
}
.black {
	color: black;
}
.red {
	border-color: #fe0000 !important;
	color: #fe0000;
	font-weight: bold;
	outline-color: #fe0000;
}
</style>
