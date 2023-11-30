import { createRouter as _createRouter, createWebHistory } from 'vue-router';

import BlahBlah from '@/views/BlahBlah';

const routes = [
  {
    name: 'home-page',
    path: '/',
    component: ProductsView
  },
  {
    name: 'product-details',
    path: '/products/:id',
    component: AddReviewView
  },
  {
    path: '/',
    component: ProductsView

  },
  {
    path: '/blah',
    component: BlahBlah
  }

];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
