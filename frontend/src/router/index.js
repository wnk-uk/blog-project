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
        path: '/oauth2/logout',
        component: RedirectView
    },
    {
        path: '/',
        component : MainSection
        ,props:true
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