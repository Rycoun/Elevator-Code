import { createApp } from 'vue'
import MyApp from './App.vue'
import { createRouter } from './router'
import axios from 'axios';

/* sets the base url for server API communication with axios */
axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;


const router = createRouter();


const app = createApp(MyApp);
app.use(router);
app.mount('#app');
