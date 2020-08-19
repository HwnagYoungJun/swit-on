<template>
	<section class="container">
		<section v-if="studing.length === 0" class="study-not-found">
			<p>스터디가 없어요 :(</p>
		</section>
		<section class="temp">
			<article class="group-container">
				<div :key="study.id" v-for="study in studing">
					<GroupCard :study="study" :isEnd="false" />
				</div>
			</article>
		</section>
		<div class="space">
			<span>종료 스터디<span></span></span>
		</div>
		<section v-if="endStudy.length === 0" class="study-not-found">
			<p>스터디가 없어요 :(</p>
		</section>
		<section class="temp">
			<article class="group-container">
				<div :key="study.id" v-for="study in endStudy">
					<GroupCard :study="study" :isEnd="true" />
				</div>
			</article>
		</section>
	</section>
</template>

<script>
import bus from '@/utils/bus.js';
import GroupCard from '@/components/group/GroupCard.vue';
import { fetchMyStudy } from '@/api/auth';
export default {
	components: { GroupCard },
	data() {
		return {
			studing: [],
			endStudy: [],
		};
	},
	props: {
		userName: {
			type: String,
			required: true,
		},
	},
	methods: {
		async fetchMyStudy() {
			try {
				const name = this.userName;
				const { data } = await fetchMyStudy(name);
				this.studing = data.unfinishedStudy;
				this.endStudy = data.finishedStudy;
			} catch (error) {
				bus.$emit('show:toast', `${error.response.data.msg}`);
			}
		},
	},
	created() {
		this.fetchMyStudy();
	},
};
</script>

<style lang="scss" scoped>
.study-not-found {
	width: 100%;
	height: 3rem;
	display: grid;
	place-items: center;
	position: relative;
	p {
		color: rgb(100, 100, 100);
		font-weight: bold;
	}
}
.space {
	margin: 2rem;
	height: 4rem;
	span {
		font-size: $font-bold;
		position: relative;
		span {
			width: 100%;
			height: 8px;
			position: absolute;
			bottom: -4px;
			left: 0;
			border-radius: 2px;
			background: $btn-purple;
			opacity: 0.5;
		}
	}
}
.temp {
	display: flex;
	justify-content: center;
	width: 100%;
	height: 100%;
}
.group-container {
	display: grid;
	width: 100%;
	gap: 1.5rem;
	grid-template-columns: repeat(4, 1fr);
	grid-template-rows: 1fr;
}
@media screen and (max-width: 1024px) {
	.group-container {
		grid-template-columns: repeat(3, 1fr);
	}
}
@media screen and (max-width: 768px) {
	.group-container {
		grid-template-columns: repeat(2, 1fr);
	}
}
</style>
