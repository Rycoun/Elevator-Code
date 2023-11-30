// Router
import { createRouter as _createRouter, createWebHistory } from 'vue-router';
// Components
import HomeView from '../views/HomeView.vue';
import BoardView from '../views/BoardView.vue';
import CardView from '../views/CardView.vue';

// Create routes
const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/board/:id',
    name: 'BoardView',
    component: BoardView
  },
  {
    path: '/board/:boardId/card/:cardId',
    name: 'CardView',
    component: CardView
  }
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
