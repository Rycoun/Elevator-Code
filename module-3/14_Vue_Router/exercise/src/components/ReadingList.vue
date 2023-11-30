<template>
  <div class="book-container">
    <router-link v-bind:to="{ name: 'book', params: { id: book.isbn } }" v-for="book in $store.state.books" v-bind:key="book.isbn">
      <book-card v-bind:book="book" />
    </router-link>
  </div>
</template>

<script>
import BookCard from '@/components/BookCard.vue';

export default {
    name: 'reading-list',
    components: {
        BookCard
    }
}
</script>

<style>
.book-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>