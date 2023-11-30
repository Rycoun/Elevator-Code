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
