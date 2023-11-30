import { createRouter as _createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import DocumentView from '../views/DocumentView.vue';
import CreateDocumentView from '../views/CreateDocumentView.vue';
import NotFoundView from '../views/NotFoundView.vue';

// Create routes
const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/document/create',
    name: 'CreateDocumentView',
    component: CreateDocumentView
  },
  {
    path: '/document/:id',
    name: 'DocumentView',
    component: DocumentView
  },
  {
    path: '/404',
    name: 'NotFoundView',
    component: NotFoundView
  }
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
