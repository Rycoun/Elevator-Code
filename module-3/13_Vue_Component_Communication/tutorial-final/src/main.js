import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store'

const store = createStore();

const app = createApp(MyApp);
app.use(store);
app.mount('#app');
