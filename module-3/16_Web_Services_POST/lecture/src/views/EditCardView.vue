<template>
  <div>
    <h1>Edit Card</h1>
    <div class="loading" v-if="isLoading">
      <img src="../assets/ping_pong_loader.gif" />
    </div>
    <card-form v-else v-bind:card="card" />
  </div>
</template>

<script>
import CardForm from '../components/CardForm.vue';
import boardService from '../services/BoardService';

export default {
  components: {
    CardForm
  },
  data() {
    return {
      card: {
        title: '',
        description: '',
        status: 'Planned',
        tag: '',
        avatar: '',
        date: null
      },
      isLoading: true,
    };
  },
  created() {
    let cardId = parseInt(this.$route.params.cardId);
    if (cardId != 0) {
      boardService
        .getCard(cardId)
        .then(response => {
          this.card = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            this.$store.commit('SET_NOTIFICATION', `Error getting card ${cardId}. This card may have been deleted or you have entered an invalid card ID.`);
            this.$router.push({ name: 'HomeView' });
          }
        });
    }
  }
};
</script>
