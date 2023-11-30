<template>
  <div>
    <h1>Add New Card</h1>
    <card-form v-bind:card="card" />
  </div>
</template>

<script>
import CardForm from '../components/CardForm.vue';

export default {
  components: {
    CardForm
  },
  data() {
    return {
      card: {
        id: 0,
        boardId: parseInt(this.$route.params.boardId),
        title: '',
        description: '',
        status: 'Planned',
        tag: '',
        avatar: '',
        date: new Date()
      },
    };
  },
};
</script>
