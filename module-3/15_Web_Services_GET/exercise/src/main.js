import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store'
import { createRouter } from './router'
import axios from 'axios';

/* sets the base url for server API communication with axios */
axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;

const store = createStore();
const router = createRouter();

const app = createApp(MyApp);
app.use(store);
app.use(router);
app.mount('#app');
