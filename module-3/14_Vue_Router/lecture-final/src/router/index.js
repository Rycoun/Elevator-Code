import { createRouter as _createRouter, createWebHistory } from 'vue-router';

import AddReviewView from '@/views/AddReviewView.vue';
import BlahBlah from '@/views/BlahBlah.vue';
import ProductsView from '@/views/ProductsView.vue';
import ProductDetails from '@/views/ProductDetails.vue';

const routes = [
  {
    name: 'home-page',
    path: '/',
    component: ProductsView
  },
  {
    name: 'product-details',
    path: '/products/:id',
    component: ProductDetails
  },
  {
    name: 'add-review',
    path: '/products/:id/reviews/create',
    component: AddReviewView
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
