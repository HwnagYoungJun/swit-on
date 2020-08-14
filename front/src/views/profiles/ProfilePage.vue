<template>
	<div class="container">
		<ProfileForm :userName="userName" />
		<ProfileNavForm :userName="userName" />
		<router-view :userName="userName" />
	</div>
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
};
</script>

<style></style>
