import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { defineAsyncComponent } from 'vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: defineAsyncComponent(() => import('../components/Home.vue'))
    },
    {
        path: '/status',
        name: 'Status',
        component: defineAsyncComponent(() => import('../components/Status.vue'))
    },
    {
        path: '/counttime',
        name: 'CountTime',
        component: defineAsyncComponent({
            loader: () => import('../components/CountTime.vue'),
            delay: 200,
            timeout: 3000
        })
    },
    {
        path:'/userview',
        name:'UserView',
        component:defineAsyncComponent(()=> import('../components/UserView.vue'))
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router