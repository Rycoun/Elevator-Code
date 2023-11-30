<template>
  <div class="main">
    <h1>Product Reviews for {{ name }} </h1>
    <img alt="Vue logo" src="./assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
  <img src="./assets/star.png" alt="Rating Star">
  <v-for></v-for>

  
</template>

<script>


export default {
  components: {
    
  },
  data() {
    return {
      name: 'Head First Design Patterns',
      description: 'a brain friendly guild to building'

    }
  }
}
</script>

<style>
.main {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
