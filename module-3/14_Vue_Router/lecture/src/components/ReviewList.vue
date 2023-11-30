<template>
  <div>
    <review-display
      v-for="review in filteredReviews"
      v-bind:key="review.id"
      v-bind:review="review"
    />
  </div>
</template>

<script>
import ReviewDisplay from './ReviewDisplay.vue';

export default {
  props: ['reviews'],
  components: {
    ReviewDisplay
  },
  computed: {
    filteredReviews() {
      const reviewsFilter = this.$store.state.filter;
      // Get reviews from props
      return this.reviews.filter(review => {
        return reviewsFilter === 0 || reviewsFilter === review.rating;
      });
    }
  }
};
</script>

