import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store';
Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'main',
		component: () => import('@/views/Main.vue'),
		beforeEnter(to, from, next) {
			store.getters['isLogin'] ? next('/category/추천') : next();
		},
	},
	{
		path: '/study/new',
		name: 'addstudy',
		component: () => import('@/views/studies/StudyAddPage.vue'),
		beforeEnter,
	},
	{
		path: '/study/search/:studyname',
		name: 'searchedstudy',
		props: route => ({
			name: String(route.params.studyname),
		}),
		component: () => import('@/views/studies/StudyPage.vue'),
		beforeEnter,
	},
	{
		path: '/study/:id',
		name: 'studydetail',
		props: route => ({
			id: Number(route.params.id),
		}),
		component: () => import('@/views/studies/StudyDetail.vue'),
		children: [
			{
				path: '',
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
				path: 'qna',
				name: 'qna',
				component: () => import('@/views/studies/children/StudyQuestion.vue'),
			},
			{
				path: 'meeting',
				name: 'meeting',
				component: () => import('@/views/studies/children/StudyMeeting.vue'),
			},
		],
	},
	{
		path: '/study/:id/:board_name/new',
		name: 'newArticle',
		component: () => import('@/views/boards/StudyArticleAddPage.vue'),
		props: route => ({
			id: Number(route.params.id),
			board_name: String(route.params.board_name),
		}),
		beforeEnter,
	},
	{
		path: '/study/:id/room/:room',
		name: 'room',
		props: route => ({
			id: Number(route.params.id),
			room: String(route.params.room),
		}),
		component: () => import('@/views/studies/StudyRoomPage.vue'),
		beforeEnter,
	},
	{
		path: '/study/:study_id/schedule',
		name: 'makeschedule',
		props: route => ({
			study_id: Number(route.params.study_id),
		}),
		component: () => import('@/views/calendar/MakeScheduleForm.vue'),
	},
	{
		path: '/study/:id/:board_name/:article_id/edit',
		name: 'editArticle',
		component: () => import('@/views/boards/StudyArticleEditPage.vue'),
		props: route => ({
			id: Number(route.params.id),
			board_name: String(route.params.board_name),
			article_id: Number(route.params.article_id),
		}),
		beforeEnter,
	},
	{
		path: '/study/:id/:board_name/:article_id',
		name: 'BoardArticleDetail',
		props: route => ({
			id: Number(route.params.id),
			board_name: String(route.params.board_name),
			article_id: Number(route.params.article_id),
		}),
		component: () => import('@/components/common/ArticleCardDetail.vue'),
	},
	{
		path: '/login',
		name: 'login',
		component: () => import('@/views/accounts/LoginPage.vue'),
		beforeEnter(to, from, next) {
			store.getters['isLogin'] ? next({ path: '/category/추천' }) : next();
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
		path: '/profile/:userName',
		name: 'profile',
		props: route => ({
			userName: String(route.params.userName),
		}),
		component: () => import('@/views/profiles/ProfilePage.vue'),

		children: [
			{
				path: '',
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
		path: '/modifyprofile',
		name: 'modifyprofile',
		component: () => import('@/views/profiles/ModifyProfilePage.vue'),
	},
	{
		path: '/category/:upperCategoryName',
		name: 'categorydetail',
		props: route => ({
			upperCategoryName: String(route.params.upperCategoryName),
			lowerCategoryName: String(route.params.lowerCategoryName),
		}),
		component: () => import('@/views/categories/CategoryPage.vue'),
		children: [
			{
				path: '',
				name: 'upperCategoryList',
				component: () =>
					import('@/views/categories/children/UpperCategoryList.vue'),
			},
			{
				path: ':lowerCategoryName',
				name: 'lowerCategoryList',
				props: route => ({
					upperCategoryName: String(route.params.upperCategoryName),
					lowerCategoryName: String(route.params.lowerCategoryName),
				}),
				component: () =>
					import('@/views/categories/children/LowerCategoryList.vue'),
			},
		],
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
		next('/login');
	}
}

export default router;
