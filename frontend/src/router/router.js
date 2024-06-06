import { createRouter, createWebHistory } from 'vue-router';
import RedirectView from '../components/RedirectView.vue'
import MainSection from '../components/MainSection.vue'
//import PostList from '../Pages/PostList.Vue'

const routes = [
    { 
        path: '/oauth2/redirect',
        component: RedirectView 
    },
    {
        path: '/',
        component : MainSection
    },
    // {
    //     path: '/post',
    //     component : PostList
    // }
]

const router = createRouter({
    history : createWebHistory(),
    routes: routes
});

export default router;