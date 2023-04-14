import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../views/login/LoginPage.vue'
import Holdings from '../views/main/holdings/Holdings.vue'
import HoldingDetail from '../views/main/holding_detail/HoldingDetail.vue'
import Setting from '../views/main/Setting.vue'
import BaseComponents from '../components/base/BaseComponents.vue'

import '../css/style.less'
import '@/js/main'

import initAuthCheck from '@/router/auth-checker'
import initWebTitle from "@/router/web-title";

Vue.use(VueRouter)

const routes = [
    {
        path: '/table',
        name: 'table',
        component: BaseComponents
    },
    {
        path: '/',
        name: 'home',
        component: Holdings
    },
    {
        path: '/login',
        name: 'login',
        component: LoginPage
    },
    {
        path: '/holdings',
        name: 'holdings',
        component: Holdings
    },
    {
        path: '/holding-detail/hid/:hid',
        name: 'holding-detail',
        component: HoldingDetail
    },
    {
        path: '/holding-detail/id/:id',
        name: 'holding-detail',
        component: HoldingDetail
    },
    {
        path: '/setting',
        name: 'setting',
        component: Setting
    },

];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

initAuthCheck(router);

initWebTitle(router);

export default router
