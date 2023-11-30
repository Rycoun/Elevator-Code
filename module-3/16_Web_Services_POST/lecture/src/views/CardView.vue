<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/ping_pong_loader.gif" />
    </div>
    <div v-else>
      <div class="board-actions">
        <router-link v-bind:to="{ name: 'BoardView', params: { id: card.boardId } }">Back to Board</router-link>
      </div>
      <card-detail v-bind:card="card" />
    </div>
  </div>
</template>

<script>
import CardDetail from '../components/CardDetail.vue';
import boardService from '../services/BoardService';

export default {
  components: {
    CardDetail
  },
  data() {
    return {
      card: {
        title: '',
        description: '',
        status: '',
        comments: []
      },
      isLoading: true
    };
  },
  created() {
    boardService
      .getCard(this.$route.params.cardId)
      .then(response => {
        this.card = response.data;
        this.isLoading = false;
      });
  }
};
</script>
