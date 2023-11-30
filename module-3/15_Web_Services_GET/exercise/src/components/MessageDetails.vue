<template>
  <header class="flex">
    <h1>{{ message.title }}</h1>
  </header>
  <div class="created">
    <label>Created on:</label>&nbsp;
    <span>{{ createdDate }} {{ createdTime }}</span>
  </div>
  <div class="message">
    <div class="wrap">
      {{ message.messageText }}
    </div>
  </div>
</template>

<script>
export default {
  props: {
    message: { type: Object, required: true }
  },
  computed: {
    createdDate() {
      let created = new Date(this.message.created);
      return created.toLocaleDateString();
    },
    createdTime() {
      let created = new Date(this.message.created);
      return created.toLocaleTimeString();
    }
  }
};
</script>

<style scoped>
.message {
  background: rgb(245, 245, 245);
  border: solid 2px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px;
  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.8);
  word-wrap: break-word;
}
.wrap {
  white-space: pre-wrap;
}
.created {
  margin-bottom: 1rem;
}
</style>
