import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'main',
		component: () => import('@/views/Main.vue'),
	},
	{
		path: '/study',
		name: 'studies',
		component: () => import('@/views/studies/StudyPage.vue'),
	},
	{
		path: '/study/new',
		name: 'addstudy',
		component: () => import('@/views/studies/StudyAddPage.vue'),
		beforeEnter,
	},
	{
		path: '/study/:id/:board_name/new',
		name: 'newArticle',
		component: () => import('@/views/boards/StudyArticleAddPage.vue'),
		beforeEnter,
	},
	{
		path: '/study/:id',
		name: 'studydetail',
		component: () => import('@/views/studies/StudyDetail.vue'),
		children: [
			{
				path: '',
				name: 'dashboard',
				component: () => import('@/views/studies/children/StudyDashBoard.vue'),
			},
			{
				path: 'calendar',
				name: 'calendar',
				component: () => import('@/views/studies/children/StudyCalendar.vue'),
			},
			{
				path: 'repository',
				name: 'repository',
				component: () => import('@/views/studies/children/StudyRepository.vue'),
			},
			{
				path: 'notice',
				name: 'notice',
				component: () => import('@/views/studies/children/StudyNotice.vue'),
			},
			{
				path: 'question',
				name: 'question',
				component: () => import('@/views/studies/children/StudyQuestion.vue'),
			},
		],
	},
	{
		path: '/study/:id/repository/:id',
		name: 'repositoryDetail',
		component: () => import('@/components/common/ArticleCardDetail.vue'),
	},
	{
		path: '/login',
		name: 'login',
		component: () => import('@/views/accounts/LoginPage.vue'),
		beforeEnter(to, from, next) {
			store.getters['isLogin'] ? next({ path: '/' }) : next();
		},
	},
	{
		path: '/signup',
		name: 'signUp',
		component: () => import('@/views/accounts/SignUpPage.vue'),
		beforeEnter(to, from, next) {
			store.getters['isLogin'] ? next(false) : next();
		},
	},
	{
		path: '/findpassword',
		name: 'findpassword',
		component: () => import('@/views/accounts/FindPasswordPage.vue'),
		beforeEnter(to, from, next) {
			store.getters['isLogin'] ? next(false) : next();
		},
	},
	{
		path: '/sendemail',
		name: 'sendemail',
		component: () => import('@/views/accounts/SendPage.vue'),
		beforeEnter(to, from, next) {
			store.getters['isLogin'] ? next(false) : next();
		},
	},
	{
		path: '/mypage/',
		name: 'mypage',
		component: () => import('@/views/profiles/MyPagePage.vue'),
		children: [
			{
				path: 'myschedule',
				name: 'myschedule',
				component: () => import('@/views/profiles/children/MyScheduleForm.vue'),
			},
			{
				path: 'mygroup',
				name: 'mygroup',
				component: () => import('@/views/profiles/children/MyGroupForm.vue'),
			},
			{
				path: 'mystorage',
				name: 'mystorage',
				component: () => import('@/views/profiles/children/MyStorageForm.vue'),
			},
			{
				path: 'myarticle',
				name: 'myarticle',
				component: () => import('@/views/profiles/children/MyArticleForm.vue'),
			},
		],
	},
	{
		path: '/category/:UpperCategoryName',
		name: 'categorydetail',
		component: () => import('@/views/categorys/CategoryPage.vue'),
	},
	{
		path: '/newsfeed',
		name: 'newsfeed',
		component: () => import('@/views/NewsFeedPage.vue'),
	},
	{
		path: '*',
		redirect: '/404',
	},
	{
		path: '/404',
		component: () => import('@/views/NotFoundPage.vue'),
	},
];

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes,
});

function beforeEnter(to, from, next) {
	if (store.getters['isLogin']) {
		next();
	} else {
		// alert('sign in please');
		next('/login');
	}
}

export default router;
