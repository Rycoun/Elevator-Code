// Router
import { createRouter as _createRouter, createWebHistory } from 'vue-router'

// Components
import HomeView from '../views/HomeView.vue';
import TopicDetailsView from '../views/TopicDetailsView.vue';
import MessageDetailsView from '../views/MessageDetailsView.vue';

// Create routes
const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/topic/:topicId',
    name: 'TopicDetailsView',
    component: TopicDetailsView
  },
  {
    path: '/topic/:topicId/message/:messageId',
    name: 'MessageDetailsView',
    component: MessageDetailsView
  },
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
