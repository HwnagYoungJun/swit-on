<template>
	<section class="notification-wrap">
		<span class="nav-router-full" @click="isOpen = !isOpen">알림</span>
		<i
			@click="isOpen = !isOpen"
			class="icon ion-md-notifications nav-router-medium"
		></i>
		<section
			:class="[
				isOpen ? '' : 'messages-close',
				'messages-wrap',
				isMainRoute ? '' : '',
			]"
		>
			<ul v-if="notifications.length">
				<li :key="notification.id" v-for="notification in notifications">
					{{ notification }}
				</li>
			</ul>
			<div class="messages-not" v-else>
				알림이 없습니다 :(
			</div>
		</section>
	</section>
</template>

<script>
export default {
	data() {
		return {
			isOpen: false,
			notifications: [],
		};
	},
	computed: {
		isMainRoute() {
			return this.$route.name === 'main';
		},
	},
};
</script>

<style lang="scss">
.notification-wrap {
	display: inline-block;
	position: relative;
}
.messages-wrap {
	padding: 0.5rem;
	background: rgba(255, 255, 255, 1);
	color: #868e96;
	z-index: 1000;
	box-shadow: 0 2px 6px 0 rgba(68, 67, 68, 0.4);
	border-radius: 3px;
	width: 300px;
	position: absolute;
	top: 30px;
	right: 0;
	@media screen and (max-width: 768px) {
		// background: $btn-purple;
		top: -37px;
		right: 0;
		width: 200px;
	}
	.messages-not {
		display: flex;
		justify-content: center;
		align-items: center;
	}
}
.messages-close {
	display: none;
}
</style>
