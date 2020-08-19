import axios from 'axios';
import { setInterceptors } from './common/interceptors';

function create(url, options) {
	const instance = axios.create(Object.assign({ baseURL: url }, options));
	return instance;
}

function createWithAuth(url, options) {
	const instance = axios.create(Object.assign({ baseURL: url }, options));
	setInterceptors(instance);
	return instance;
}

export const auth = create(process.env.VUE_APP_API_URL);
export const study = createWithAuth(`${process.env.VUE_APP_API_URL}study/`);
export const baseAuth = createWithAuth(`${process.env.VUE_APP_API_URL}`);
export const alarm = createWithAuth(`${process.env.VUE_APP_API_URL}`);

export const articles = createWithAuth(
	`${process.env.VUE_APP_API_URL}articles/`,
);
export const boardArticles = createWithAuth(
	`${process.env.VUE_APP_API_URL}study/`,
);
