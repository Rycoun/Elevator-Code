<template>
  <div>
    <div class="board-actions">
      <router-link v-bind:to="{ name: 'HomeView' }">Back to Boards</router-link>
    </div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/ping_pong_loader.gif" />
    </div>
    <div v-else>
      <div class="header">
        <h1>{{ board.title }}</h1>
        <router-link class="btn btn-submit" :to="{ name: 'AddCardView', params: { boardId: board.id } }">Add
          New Card</router-link>
        <button class="btn btn-cancel deleteBoard" v-on:click="deleteBoard">Delete Board</button>
      </div>
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
  methods: {
    deleteBoard() {

    }
  },
  created() {
    let boardId = parseInt(this.$route.params.id);
    boardService.getBoard(boardId)
      .then(response => {
        this.board = response.data;
        this.isLoading = false;
      })
      .catch(error => {
        if (error.response) {
          if (error.response.status === 404) {
            this.$store.commit('SET_NOTIFICATION',
              "Error: Board " + boardId + " was not found. This board may have been deleted or you have entered an invalid board ID.");
            this.$router.push({ name: 'HomeView' });
          } else {
            this.$store.commit('SET_NOTIFICATION',
              "Error getting board " + boardId + ". Response received was '" + error.response.statusText + "'.");
          }
        } else if (error.request) {
          this.$store.commit('SET_NOTIFICATION', "Error getting board. Server could not be reached.");
        } else {
          this.$store.commit('SET_NOTIFICATION', "Error getting board. Request could not be created.");
        }
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

.header {
  display: flex;
  align-items: center;
}

.header h1 {
  flex-grow: 1;
}
</style>