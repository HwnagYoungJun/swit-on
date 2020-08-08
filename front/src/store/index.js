import Vue from 'vue';
import Vuex from 'vuex';
import { loginUser, registerUser } from '@/api/auth';
import cookies from 'vue-cookies';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		email: cookies.isKey('email') ? cookies.get('email') : null,
		name: cookies.isKey('name') ? cookies.get('name') : null,
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
		setUserName(state, name) {
			state.name = name;
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
			console.log(responseData.user);
			commit('setUserName', responseData.user.name);
			commit('setToken', responseData.token);
			cookies.set('auth-token', responseData.token);
			cookies.set('name', responseData.user.name);
		},
		async SIGNUP({ dispatch }, userData) {
			const { data } = await registerUser(userData);
			dispatch('SETUP_USER', data);
			return data;
		},
		async LOGIN({ dispatch }, userData) {
			const { data } = await loginUser(userData);
			console.log(data.user.name);
			dispatch('SETUP_USER', data);
			return data;
		},
	},
	modules: {},
});
