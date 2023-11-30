<template>
  <div>
    <card-detail />
  </div>
</template>

<script>
import boardService from '@/services/BoardService.js';
import CardDetail from '../components/CardDetail.vue';

export default {
  components: {
    CardDetail
  },


  data() {
    return {
      isLoading: true,
      card:{
        id: 0,
        boardId:0,

      },
    }
  },

  created() {
    const cardId = parseInt(this.$route.params.cardId);
    boardService
    .getCard(cardId)
    .then(response => {

      this.card = response.data;
      this.isLoading = false;
    }) 
  }


};
</script>
