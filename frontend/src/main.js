import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router';
import store from './store';
import dayjs from 'dayjs';
import 'dayjs/locale/ko';

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { faGithub } from '@fortawesome/free-brands-svg-icons'

library.add(fas)
library.add(faGithub);

const app =  createApp(App);

app.config.globalProperties.$dayjs = dayjs;
app.config.globalProperties.$dayjs.locale('ko');

app.use(router)
app.use(store)
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
