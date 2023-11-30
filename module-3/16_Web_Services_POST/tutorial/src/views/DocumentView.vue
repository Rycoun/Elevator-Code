<template>
  <div>
    <h1>Document Detail</h1>
    <div class="loading" v-if="isLoading">
      <img src="../assets/hourglass.gif" />
    </div>
    <document-detail v-else v-bind:document="document" />
  </div>
</template>

<script>
import DocumentDetail from '../components/DocumentDetail.vue';
import docsService from '../services/DocsService';

export default {
  components: {
    DocumentDetail
  },
  data() {
    return {
      isLoading: true,
      document: {}
    };
  },
  created() {
    docsService
      .get(this.$route.params.id)
      .then(response => {
        this.document = response.data;
        this.isLoading = false;
      });
  }
};
</script>
