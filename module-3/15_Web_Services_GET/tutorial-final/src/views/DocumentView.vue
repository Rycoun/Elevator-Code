<template>
  <div>
    <h1>Document Detail</h1>
    <document-detail v-bind:document="document" />
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
      document: {}
    };
  },
  created() {
    docsService
      .get(this.$route.params.id)
      .then(response => {
        this.document = response.data;
      });
  }
};
</script>
