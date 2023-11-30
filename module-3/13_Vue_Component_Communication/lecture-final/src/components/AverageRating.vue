<template>
  <div
    class="well"
    v-bind:class="{ selected: $store.state.ratingFilter === 0 }"
    v-on:click="updateRatingFilter"
  >
    <span class="amount">{{ averageRating }}</span>
    Average Rating
  </div>
</template>

<script>
export default {
  computed: {
    averageRating() {
      const reviews = this.$store.state.reviews;

      const sum = reviews.reduce((prev, curr) => prev + curr.rating, 0);

      return (sum / reviews.length).toFixed(2);
    },
  },

  methods: {
      updateRatingFilter() {
          this.$store.commit('UPDATE_RATING_FILTER', 0);
      }
  }
};
</script>

<style scoped>

.well {
  display: inline-block;
  width: 15%;
  border: 1px black solid;
  border-radius: 6px;
  text-align: center;
  margin: 0.25rem;
  padding: 0.25rem;
  cursor: pointer;
}

.well.selected {
  background-color: skyblue;
}

.amount {
  color: darkslategray;
  display: block;
  font-size: 2.5rem;
}

</style>