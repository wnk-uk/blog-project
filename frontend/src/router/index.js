import { createRouter, createWebHistory } from 'vue-router';
import RedirectView from '../components/RedirectView.vue'
import MainLayout from '../views/MainLayout.vue'
import PostList from '../views/PostList.vue'
import writeLayout from '../views/WriteLayout.vue'
import viewsLayout from '../views/ViewLayout.vue'

const routes = [
    { 
        path: '/oauth2/redirect',
        component: RedirectView
    },
    { 
        path: '/oauth2/logout',
        component: RedirectView
    },
    {
        path: '/',
        component : MainLayout,
        props:true
    },
    {
        path: '/tags/:id',
        component : PostList,
    },
    {
        path: '/posts/write',
        component : writeLayout,
    },
    {
        path: '/posts/view/:id',
        component : viewsLayout,
    },
]

const router = createRouter({
    history : createWebHistory(),
    routes: routes
});

export default router;