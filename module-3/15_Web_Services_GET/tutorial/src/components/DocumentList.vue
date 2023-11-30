<template>
  <table>
    <thead>
      <tr>
        <th>&nbsp;</th>
        <th>Document Name</th>
        <th>Author</th>
        <th>Last Opened by me</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="doc in sortedDocs" v-bind:key="doc.id" v-on:click="viewDocument(doc.id)">
        <td class="docs-icon">
          <img src="../assets/icons8-google-docs-48.png" />
        </td>
        <td class="name">{{ doc.name }}</td>
        <td>
          <img v-bind:src="doc.avatar" class="avatar" />
          <span class="ownedby">{{ doc.author }}</span>
        </td>
        <td>{{ new Date(doc.lastOpened).toDateString() }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  data() {
    return {
      docs: []
    };
  },
  computed: {
    sortedDocs() {
      return this.docs
        .slice()
        .sort(
          (a, b) =>
            new Date(b.lastOpened) -
            new Date(a.lastOpened)
        );
    }
  },
  methods: {
    viewDocument(id) {
      this.$router.push({name: 'DocumentView', params: { id: id }});
    }
  },
  created() {

  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
}
th {
  font-family: "Work Sans", sans-serif;
  font-weight: 500;
  text-align: left;
}

tr {
  margin: 20px;
  padding: 10px;
}
tr:hover {
  background-color: #e8f0fe;
  cursor: pointer;
}
td {
  padding: 8px;
  font-family: "Work Sans", sans-serif;
}

td.name {
  font-weight: 400;
}
.docs-icon img {
  height: 32px;
}
.avatar {
  border-radius: 20px;
  width: 32px;
  vertical-align: middle;
  padding-right: 5px;
}
.ownedby {
  vertical-align: middle;
}
</style>
