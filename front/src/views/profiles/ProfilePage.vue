<template>
	<div class="container">
		<ProfileForm />
		<MyPageNavForm />
		<router-view />
		<hr />
	</div>
</template>

<script>
import ProfileForm from '@/views/profiles/children/ProfileForm.vue';
import MyPageNavForm from '@/views/profiles/children/MyPageNavForm.vue';

export default {
	components: {
		ProfileForm,
		MyPageNavForm,
	},
};
</script>

<style></style>
