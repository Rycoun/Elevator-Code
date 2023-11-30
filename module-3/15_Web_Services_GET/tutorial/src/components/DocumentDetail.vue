<template>
  <div>
    <h2>{{ document.name }}</h2>
    <p>
      <strong>Author:</strong>
      {{ document.author }} |
      <strong>Last Opened by Me:</strong>
      {{ new Date(document.lastOpened).toDateString() }}
    </p>
    <textarea id="content" name="content" v-model="document.content" spellcheck="false" />
    <div class="back">
      <router-link v-bind:to="{ name: 'HomeView' }">Return to Documents List</router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: ['document'],
};
</script>

<style scoped>
textarea {
  width: 500px;
  height: 500px;
}
.back {
  margin-top: 20px;
}

a:link,
a:visited {
  color: blue;
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
</style>
