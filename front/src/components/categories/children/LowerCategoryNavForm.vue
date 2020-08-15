<template>
	<div>
		<br />
		<span :key="name" v-for="name in names">
			<router-link :to="`/category/${upperCategoryName}/${name}`">
				<LowerCategoryCard @changeLower="changeLower" :name="name" />
			</router-link>
		</span>
	</div>
</template>

<script>
import bus from '@/utils/bus.js';
import LowerCategoryCard from '../children/LowerCategoryCard.vue';
import axios from 'axios';
export default {
	data() {
		return {
			baseURL: process.env.VUE_APP_API_URL,
			names: [],
		};
	},
	props: {
		upperCategory: Number,
		upperCategoryName: String,
	},
	components: {
		LowerCategoryCard,
	},
	methods: {
		async fetchLowerCategory(c) {
			try {
				this.names = [];
				const { data } = await axios.get(`${this.baseURL}category/up_low/${c}`);
				data.forEach(el => this.names.push(el.name));
			} catch (error) {
				bus.$emit('show:toast', `${error}`);
			}
		},
		changeLower(categoryName) {
			this.$emit('changeLower', categoryName);
		},
	},
	watch: {
		$route() {
			this.fetchLowerCategory(this.$props.upperCategory);
		},
	},
	created() {
		this.fetchLowerCategory(this.$props.upperCategory);
	},
};
</script>

<style></style>
