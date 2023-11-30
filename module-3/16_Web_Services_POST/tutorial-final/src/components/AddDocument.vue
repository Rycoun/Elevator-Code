<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="name">Name</label>
      <input id="name" name="name" type="text" v-model="document.name" />
    </div>
    <div class="field">
      <label for="author">Author</label>
      <input id="author" name="author" type="text" v-model="document.author" />
    </div>
    <div class="field">
      <label for="content">Content</label>
      <textarea  id="content" name="content" spellcheck="false" v-model="document.content" />
    </div>
    <div class="actions">
      <button type="button" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" v-on:click="saveDocument()">Save Document</button>
    </div>
  </form>
</template>

<script>
import docsService from '../services/DocsService';

export default {
  data() {
    return {
      document: {
        id: Math.floor(Math.random() * (1000 - 100) + 100),
        name: '',
        author: '',
        avatar: 'https://usefulimages.blob.core.windows.net/avatars/avataaars' + (Math.floor(Math.random() * 12) + 1) + '.png',
        content: '',
        lastOpened: new Date()
      }
    };
  },
  methods: {
    saveDocument() {
      docsService
        .create(this.document)
        .then(response => {
          if (response.status === 201) {
            this.$router.push({name: 'HomeView'});
          }
        })
        .catch(error => {
          console.error(error);
        });
    },
    cancel() {
      this.$router.push({name: 'HomeView'});
    }
  }
};
</script>

<style scoped>
form {
  padding: 20px;
  font-size: 16px;
  width: 500px;
}
form * {
  box-sizing: border-box;
  line-height: 1.5;
}
.field {
  display: flex;
  flex-direction: column;
}
.field label {
  margin: 4px 0;
  font-weight: bold;
}
.field input,
.field textarea {
  padding: 8px;
}
.field textarea {
  height: 300px;
}
.actions {
  text-align: right;
  padding: 10px 0;
}
</style>
