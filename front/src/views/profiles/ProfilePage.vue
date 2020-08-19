<template>
	<section class="container">
		<ProfileForm :userName="userName" />
		<ProfileNavForm :userName="userName" />
		<router-view :userName="userName" />
	</section>
</template>

<script>
import ProfileForm from '@/views/profiles/children/ProfileForm.vue';
import ProfileNavForm from '@/views/profiles/children/ProfileNavForm.vue';

export default {
	props: {
		userName: String,
	},
	components: {
		ProfileForm,
		ProfileNavForm,
	},
	mounted() {
		document.title = `스윗온 ${this.userName} 프로필`;
	},
};
</script>

<style></style>
