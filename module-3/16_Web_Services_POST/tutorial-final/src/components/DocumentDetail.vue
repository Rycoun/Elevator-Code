<template>
  <div>
    <h2>{{ document.name }}</h2>
    <p>
      <strong>Author:</strong>
      {{ document.author }} |
      <strong>Last Opened by Me:</strong>
      {{ new Date(document.lastOpened).toDateString() }}
    </p>
    <textarea id="content" name="content" v-model="content"
      spellcheck="false"
    />
    <div>
      <button type="submit" v-on:click="saveDocument()">Save Document</button>
    </div>
    <div class="back">
      <router-link v-bind:to="{ name: 'HomeView' }">Return to Documents List</router-link>
    </div>
  </div>
</template>

<script>
import docsService from '../services/DocsService';

export default {
  props: ['document'],
  data() {
    return {
      content: this.document.content
    };
  },
  methods: {
    saveDocument() {
      const current = this.document;
      const doc = {
        id: current.id,
        name: current.name,
        author: current.author,
        avatar: current.avatar,
        content: this.content,
        lastOpened: new Date()
      };
      docsService
        .update(doc.id, doc)
        .then(response => {
          if (response.status === 200) {
            this.$router.push({name: 'HomeView'});
          }
        })
        .catch(error => {
          console.error(error);
        });
    }
  }
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
