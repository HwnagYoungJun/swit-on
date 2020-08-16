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
		id: cookies.isKey('userid') ? cookies.get('userid') : null,
	},
	getters: {
		isLogin(state) {
			return !!state.token;
		},
		getToken(state) {
			return state.token;
		},
		getName(state) {
			return state.name;
		},
		getUserId(state) {
			return state.id;
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
		setUserId(state, id) {
			state.id = id;
		},
		clearToken(state) {
			state.token = '';
		},
	},
	actions: {
		SETUP_USER({ commit }, responseData) {
			commit('setUserName', responseData.user.name);
			commit('setUserId', responseData.user.id);
			commit('setToken', responseData.token);
			cookies.set('auth-token', responseData.token);
			cookies.set('name', responseData.user.name);
			cookies.set('userid', responseData.user.id);
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
