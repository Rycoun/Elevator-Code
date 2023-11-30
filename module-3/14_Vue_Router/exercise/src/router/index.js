import { createRouter as _createRouter, createWebHistory } from 'vue-router';

import Home from "../views/HomeView.vue";
import MyBooks from "../views/MyBooksView.vue";
import NewBook from "../views/NewBookView.vue";
import BookDetail from "../views/BookDetailView.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/myBooks',
    name: 'myBooks',
    component: MyBooks
  },
  {
    path: '/addBook',
    name: 'newBook',
    component: NewBook
  },
  {
    path: '/book/:id',
    name: 'book',
    component: BookDetail
  }
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
