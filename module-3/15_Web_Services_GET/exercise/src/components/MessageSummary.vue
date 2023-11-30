<template>
  <div class="message" 
      v-on:click="$router.push({ name: 'MessageDetailsView', params: { messageId: message.id } })">
    <header>
      <h3>{{ message.title }}</h3>
    </header>
    <p>{{ shortText }}</p>
  </div>
</template>

<script>
const MAX_SUMMARY_LENGTH = 100;

export default {
  props: {
    message: { type: Object, required: true }
  },
  computed: {
    shortText() {
      let text = this.message.messageText;
      if (text.length > MAX_SUMMARY_LENGTH) {
        text = text.substr(0, MAX_SUMMARY_LENGTH) + '...';
      }
      return text;
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
  cursor: pointer;
}
</style>
