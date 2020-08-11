<template>
	<v-autocomplete
		:items="items"
		v-model="item"
		:get-label="getLabel"
		:filter="filterObject"
		:component-item="template"
		item-text="name"
		@update-items="updateItems"
	>
		<!-- {{ items }} -->
	</v-autocomplete>
</template>

<script>
import ItemTemplate from './ItemTemplate.vue';
import { fetchStudies } from '@/api/studies';

export default {
	data() {
		return {
			item: '',
			items: [],
			template: ItemTemplate,
		};
	},
	methods: {
		getLabel(item) {
			return item.name;
		},
		async updateItems() {
			const { data } = await fetchStudies();
			console.log(data);
			this.items = data;
			console.log(this.items);
		},
		filterObject(item, queryText) {
			return (
				item.name.toLocaleLowerCase().indexOf(queryText.toLocaleLowerCase()) >
				-1
			);
		},
	},
	created() {
		this.updateItems();
	},
};
</script>
