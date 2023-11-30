import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
    state: {
      todos: [
        { name: "Wake up", done: false, category: "Home" },
        { name: "5 Minute Morning Movement", done: false, category: "Home" },
        { name: "Meditate", done: false, category: "Home" },
        { name: "Brush teeth", done: false, category: "Home" },
        { name: "Shower", done: false, category: "Home" },
        { name: "Answer email", done: false, category: "Work" },
        { name: "Stand up meeting", done: false, category: "Work" },
        { name: "Fix a bug", done: false, category: "Work" }
      ]
    },
    mutations: {
      ADD_NEW_TODO(state, todo) {
        state.todos.push(todo);
      },
      FLIP_DONE(state, todoToChange) {
        todoToChange.done = ! todoToChange.done;
      }
    },
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: true
  })
}
