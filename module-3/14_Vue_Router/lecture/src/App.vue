<template>
  <div class="main">
    <header>Heads</header>
      
      <router-view></router-view>

      <footer>foots</footer>
  </div>
</template>

<script>
import { routerKey } from 'vue-router';

export default { components: { routerKey } };
</script>

<style>
.main {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  max-width: 800px;
  margin: 60px auto 0 auto;
}
</style>
