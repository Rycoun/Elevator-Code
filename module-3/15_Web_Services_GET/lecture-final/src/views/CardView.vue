<template>
  <div>
    <div v-if="isLoading">
      <div class="loading">
        <img src="../assets/ping_pong_loader.gif" />
      </div>
    </div>
    <div v-else>
      <router-link :to="{ name: 'BoardView', params: { id: $route.params.boardId } }">Back to Board</router-link>

      <card-detail v-bind:card="theCard" />
    </div>
  </div>
</template>

<script>
import boardService from '@/services/BoardService.js';
import CardDetail from "../components/CardDetail.vue";

export default {
  components: {
    CardDetail,
  },

  data() {
    return {
      isLoading: true,
      theCard: {
        id: 0,
        boardId: 0,
        title: "",
        description: "",
        status: "",
        tag: "",
        avatar:"",
        date: "",
        comments: [],
      },
    };
  },

  async created() {
    const cardId = parseInt(this.$route.params.cardId);

    // boardService
    //   .getCard(cardId)
    //   .then(response => {
    //     this.theCard = response.data;
    //     this.isLoading = false;
    //   });

    const response = await boardService.getCard(cardId);
    this.theCard = response.data;
    this.isLoading = false;
  }
};
</script>
