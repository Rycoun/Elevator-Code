<template>
  <div>
    <h1>{{ card.title }}</h1>
    <p>{{ card.description }}</p>
    <router-link v-bind:to="{ name: 'EditCardView', params: { cardId: $route.params.cardId } }" class="btn btn-submit">Edit
      Card</router-link>
    <button class="btn btn-cancel" v-on:click="deleteCard">Delete Card</button>
    <comments-list v-bind:comments="card.comments" />
  </div>
</template>

<script>
import boardService from '../services/BoardService';
import CommentsList from '../components/CommentsList.vue';

export default {
  components: {
    CommentsList
  },
  props: ['card'],
  methods: {
    deleteCard() {
      if (
        confirm(
          'Are you sure you want to delete this card? This action cannot be undone.'
        )
      ) {
        boardService
          .deleteCard(this.card.id)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: `Card ${this.card.id} was successfully deleted.`,
                  type: 'success'
                }
              );
              this.$router.push({ name: 'BoardView', params: { id: this.card.boardId } });
            }
          })
          .catch(error => {
            if (error.response) {
              this.$store.commit('SET_NOTIFICATION',
                `Error deleting card. Response received was "${error.response.statusText}".`);
            } else if (error.request) {
              this.$store.commit('SET_NOTIFICATION', 'Error deleting card. Server could not be reached.');
            } else {
              this.$store.commit('SET_NOTIFICATION', 'Error deleting card. Request could not be created.');
            }
          });
      }
    },
  },
};
</script>
