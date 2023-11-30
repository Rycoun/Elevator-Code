<template>
  <div class="well">
    <span class="amount" v-on:click="updateFilter()">{{ numberOfReviews }}</span>
    {{ rating }} Star Review{{ numberOfReviews === 1 ? '' : 's' }}
  </div>
</template>

<script>
export default {
  props: ['rating', 'reviews'],
  methods: {
    updateFilter() {
      this.$store.commit('UPDATE_FILTER', this.rating);
    }
  },
  computed: {
    numberOfReviews() {
      // Get reviews from props
      const reviews = this.reviews;
      const matchingReviews = reviews.filter((review) => {
        return review.rating === this.rating;
      });
      return matchingReviews.length;
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
}

.amount {
  color: darkslategray;
  display: block;
  font-size: 2.5rem;
}

.amount:hover {
  cursor: pointer;
}
</style>
