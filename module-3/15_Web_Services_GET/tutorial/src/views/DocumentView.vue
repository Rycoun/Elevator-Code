<template>
  <div>
    <h1>Document Detail</h1>
    <document-detail v-bind:document="document" />
  </div>
</template>

<script>
import DocumentDetail from '../components/DocumentDetail.vue';

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

  }
};
</script>
