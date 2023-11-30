<template>
  <div class="boards">
    <router-link v-for="board in boards" v-bind:key="board.id"
      v-bind:to="{ name: 'BoardView', params: { id: board.id } }">
      <div class="board" v-bind:style="{ 'background-color': board.backgroundColor }">{{ board.title }}</div>
    </router-link>
  </div>
</template>

<script>
export default {
  props: ['boards'],
};
</script>

<style scoped>
.boards {
  display: flex;
  margin: 0 20px;
  flex-wrap: wrap;
  column-gap: 20px;
  row-gap: 25px;
  justify-content: flex-start;
  align-items: center;
}

.board {
  min-width: 150px;
  border-radius: 10px;
  padding: 40px;
  text-align: center;
}

a:link,
a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}
</style>
