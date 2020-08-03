import store from '@/store/index';
import cookies from 'vue-cookies';

function setInterceptors(instance) {
	// Add a request interceptor
	instance.interceptors.request.use(
		function(config) {
			config.headers.Authorization =
				store.getters['getToken'] || cookies.get('auth-token');
			return config;
		},
		function(error) {
			return Promise.reject(error);
		},
	);

	// Add a response interceptor
	instance.interceptors.response.use(
		function(response) {
			return response;
		},
		function(error) {
			return Promise.reject(error);
		},
	);
}

export { setInterceptors };
