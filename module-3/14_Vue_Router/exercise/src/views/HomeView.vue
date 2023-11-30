<template>
    <div class="home">
        <h1> Best Sellers</h1>
        <best-seller-list />
        <h1> New Releases</h1>
        <new-releases-list />
    </div>
  </template>
  
  <script>
  import BestSellerList from '../components/BestSellerList.vue'
  import NewReleasesList from '../components/NewReleasesList.vue'
  export default {
    components: { BestSellerList, NewReleasesList },
  
  }
  </script>
  
  <style>
  
  </style>