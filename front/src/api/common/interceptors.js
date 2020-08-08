import store from '@/store/index';
import cookies from 'vue-cookies';

function setInterceptors(instance) {
	// Add a request interceptor
	instance.interceptors.request.use(
		function(config) {
			config.headers.Authentication =
				store.getters['getToken'] || cookies.get('auth-token')
					? `Bearer ${store.getters['getToken'] || cookies.get('auth-token')}`
					: null;
			console.log(config);
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
