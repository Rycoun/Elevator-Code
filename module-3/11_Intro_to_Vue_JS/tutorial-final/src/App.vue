<template>
  <div id="todo-app">
    <todo-list></todo-list>
  </div>
</template>

<script>
import TodoList from './components/TodoList.vue';

export default {
  components: {
    TodoList
  }
}
</script>

<style>
body {
  background: rgb(74,162,244);
}
</style>
