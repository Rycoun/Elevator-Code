<template>
  <div>
    <div v-if="isLoading">
      <h1>My Kanban Boards</h1>
      <div class="loading">
        <img src="../assets/ping_pong_loader.gif" />
      </div>
    </div>
    <div v-else>
      <h1>My Kanban Boards</h1>
      <boards-list v-bind:boards="boards" />
    </div>
  </div>
</template>

<script>
import BoardsList from '../components/BoardsList.vue';
import boardService from '../services/BoardService';

export default {
  components: {
    BoardsList
  },
  data() {
    return {
      boards: [],
      isLoading: true
    };
  },
  methods: {
    async retrieveBoards() {
      // boardService.getBoards().then(response => {
      //   console.log(response);
      //   this.boards = response.data;
      //   this.isLoading = false;
      // });

      const response = await boardService.getBoards();
      console.log(response);
      this.boards = response.data;
      this.isLoading = false;
    }
  },
  async created() {
    await this.retrieveBoards();
  },
};
</script>
