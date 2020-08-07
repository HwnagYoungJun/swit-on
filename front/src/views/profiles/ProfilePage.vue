<template>
	<div class="container">
		<ProfileForm />
		<ProfileNavForm />
		<router-view />
		<hr />
	</div>
</template>

<script>
import ProfileForm from '@/views/profiles/children/ProfileForm.vue';
import ProfileNavForm from '@/views/profiles/children/ProfileNavForm.vue';

export default {
	components: {
		ProfileForm,
		ProfileNavForm,
	},
};
</script>

<style></style>
