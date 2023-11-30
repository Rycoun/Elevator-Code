import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store'
import { createRouter } from './router'

const store = createStore();
const router = createRouter();

const app = createApp(MyApp);
app.use(store);
app.use(router);
app.mount('#app');
