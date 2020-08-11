import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import VueCookies from 'vue-cookies';
import vuetify from '@/plugins/vuetify';
import Autocomplete from 'v-autocomplete';
import { formatDate, formatWeekday, truncate } from '@/utils/filters';

Vue.use(VueCookies);
Vue.use(Autocomplete);
Vue.filter('formatDate', formatDate);
Vue.filter('formatWeekday', formatWeekday);
Vue.filter('truncate', truncate);
Vue.config.productionTip = false;

new Vue({
	router,
	store,
	vuetify,
	render: h => h(App),
}).$mount('#app');
