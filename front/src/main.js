import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import VueCookies from 'vue-cookies';
import InfiniteLoading from 'vue-infinite-loading';
import {
	formatDate,
	formatWeekday,
	truncate,
	fileDownload,
} from '@/utils/filters';
// import WebRTC from 'vue-webrtc';

// Vue.use(WebRTC);

Vue.use(VueCookies);
Vue.use(InfiniteLoading);
Vue.filter('formatDate', formatDate);
Vue.filter('formatWeekday', formatWeekday);
Vue.filter('truncate', truncate);
Vue.filter('fileDownload', fileDownload);
Vue.config.productionTip = false;

new Vue({
	router,
	store,
	render: h => h(App),
}).$mount('#app');
