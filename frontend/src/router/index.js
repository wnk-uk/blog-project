import { createRouter, createWebHistory } from 'vue-router';
import RedirectView from '../components/RedirectView.vue'
import MainLayout from '../views/MainLayout.vue'
import TagList from '../views/TagList.vue'
import writeLayout from '../views/WriteLayout.vue'

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
        component : TagList,
    },
    {
        path: '/write',
        component : writeLayout,
    }
]

const router = createRouter({
    history : createWebHistory(),
    routes: routes
});

export default router;