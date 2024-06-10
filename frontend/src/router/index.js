import { createRouter, createWebHistory } from 'vue-router';
import RedirectView from '../components/RedirectView.vue'
import MainLayout from '../components/MainLayout.vue'
import TagList from '../views/TagList.vue'
import TagSection from '@/components/TagSection.vue';

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
        props:true,
        children : [
            {
                path: '/',
                component:TagSection
            }
        ]
    },
     {
         path: '/tags/:id',
         component : TagList,
         children : [
            {
                path: '/tags/:id',
                component:TagSection
            }
        ]

     }
]

const router = createRouter({
    history : createWebHistory(),
    routes: routes
});

export default router;