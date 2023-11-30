<template>
  <header class="flex">
    <h1>{{ topic.title }}</h1>
  </header>
  <div class="created">
    <label>Topic created on:</label>&nbsp;
    <span>{{ createdDate }} {{ createdTime }}</span>
  </div>
  <div class="flex">
    <h2>Messages</h2>
  </div>
  <message-summary v-for="message in topic.messages" v-bind:key="message.id" v-bind:message="message" />
</template>

<script>
import MessageSummary from '../components/MessageSummary.vue';

export default {
  components: {
    MessageSummary
  },
  props: {
    topic: {
      type: Object,
      required: true
    }
  },
  computed: {
    createdDate() {
      let created = new Date(this.topic.date);
      return created.toLocaleDateString();
    },
    createdTime() {
      let created = new Date(this.topic.date);
      return created.toLocaleTimeString();
    }
  }
};
</script>

<style scoped>
header {
  margin-bottom: 1rem;
}
h2 {
  font-size: large;
}
.created {
  margin-bottom: 1rem;
}
</style>
