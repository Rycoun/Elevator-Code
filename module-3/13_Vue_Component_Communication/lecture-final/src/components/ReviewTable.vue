<template>
  <table>
    <thead>
      <tr>
        <th>Title</th>
        <th>Reviewer</th>
        <th>Review</th>
        <th>Rating</th>
        <th>Favorited</th>
      </tr>
    </thead>
    <tbody>
      <review-table-row
        v-for="review in filteredReviews"
        v-bind:key="review.id"
        v-bind:review="review"
      />
      <tr v-show="filteredReviews.length == 0">
        <td colspan="5">There are no reviews</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import ReviewTableRow from './ReviewTableRow.vue';

export default {
  components: {
    ReviewTableRow
  },
  computed: {
    filteredReviews() {
      const reviewsFilter = this.$store.state.filter;
      return this.$store.state.reviews.filter(review => {
        return reviewsFilter === 0 ? true : reviewsFilter === review.rating;
      });
    }
  }
};
</script>

<style scoped>
th {
  text-align: left;
}
</style>
