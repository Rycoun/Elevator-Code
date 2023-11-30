import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
    state: {},
    mutations: {},
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: true
  })
}
