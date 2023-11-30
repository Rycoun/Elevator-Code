// Router
import { createRouter as _createRouter, createWebHistory } from 'vue-router';
// Components
import HomeView from '../views/HomeView.vue';
import BoardView from '../views/BoardView.vue';
import CardView from '../views/CardView.vue';
import AddCardView from '../views/AddCardView.vue';
import EditCardView from '../views/EditCardView.vue';

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
  },
  {
    path: '/board/:boardId/card/create',
    name: 'AddCardView',
    component: AddCardView
  },
  {
    path: '/board/:boardId/card/:cardId/edit',
    name: 'EditCardView',
    component: EditCardView
  }
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
