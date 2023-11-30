<template>
  <div>
    <div class="board-actions">
      <router-link v-bind:to="{ name: 'HomeView' }">Back to Boards</router-link>
    </div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/ping_pong_loader.gif" />
    </div>
    <div v-else>
      <h1>{{ board.title }}</h1>
      <div class="boards">
        <board-section title="Planned" v-bind:cards="planned" />
        <board-section title="In Progress" v-bind:cards="inProgress" />
        <board-section title="Completed" v-bind:cards="completed" />
      </div>
    </div>
  </div>
</template>

<script>
import BoardSection from '../components/BoardSection.vue';
import boardService from '../services/BoardService';

export default {
  components: {
    BoardSection
  },
  data() {
    return {
      board: { title: '', cards: [] },
      isLoading: true
    };
  },
  computed: {
    planned() {
      return this.board.cards.filter(card => card.status === 'Planned');
    },
    inProgress() {
      return this.board.cards.filter(card => card.status === 'In Progress');
    },
    completed() {
      return this.board.cards.filter(card => card.status === 'Completed');
    }
  },
  created() {
    let boardId = parseInt(this.$route.params.id);
    boardService.getBoard(boardId)
      .then(response => {
        this.board = response.data;
        this.isLoading = false;
      });
  }
};
</script>

<style scoped>
.boards {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}
</style>