import Vue from 'vue';
import Vuex from 'vuex';
import { loginUser, registerUser } from '@/api/auth';
import cookies from 'vue-cookies';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		email: cookies.isKey('email') ? cookies.get('email') : null,
		token: cookies.isKey('auth-token') ? cookies.get('auth-token') : null,
	},
	getters: {
		isLogin(state) {
			return !!state.token;
		},
		getToken(state) {
			return state.token;
		},
	},
	mutations: {
		setUserEmail(state, email) {
			state.email = email;
		},
		clearUserEmail(state) {
			state.email = '';
		},
		setToken(state, token) {
			state.token = token;
		},
		clearToken(state) {
			state.token = '';
		},
	},
	actions: {
		SETUP_USER({ commit }, responseData) {
			commit('setUserEmail', responseData.user.email);
			commit('setToken', responseData.token);
			cookies.set('auth-token', responseData.token);
			cookies.set('email', responseData.user.email);
		},
		async SIGNUP({ dispatch }, userData) {
			const { data } = await registerUser(userData);
			dispatch('SETUP_USER', data);
			return data;
		},
		async LOGIN({ dispatch }, userData) {
			const { data } = await loginUser(userData);
			console.log(data);
			dispatch('SETUP_USER', data);
			return data;
		},
	},
	modules: {},
});
