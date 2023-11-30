import { createApp } from 'vue'
import MyApp from './App.vue'
import { createRouter } from './router'

const router = createRouter();

const app = createApp(MyApp);
app.use(router);
app.mount('#app');
