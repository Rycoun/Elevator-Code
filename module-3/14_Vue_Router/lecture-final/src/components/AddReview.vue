<template>
  <form v-on:submit.prevent="addNewReview">
    <div class="form-element">
      <label for="reviewer">Name:</label>
      <input id="reviewer" type="text" v-model="newReview.reviewer" />
    </div>
    <div class="form-element">
      <label for="title">Title:</label>
      <input id="title" type="text" v-model="newReview.title" />
    </div>
    <div class="form-element">
      <label for="rating">Rating:</label>
      <!-- NOTE: Use .number modifier here so newReview.rating is a number, not a string -->
      <select id="rating" v-model.number="newReview.rating">
        <option value="1">1 Star</option>
        <option value="2">2 Stars</option>
        <option value="3">3 Stars</option>
        <option value="4">4 Stars</option>
        <option value="5">5 Stars</option>
      </select>
    </div>
    <div class="form-element">
      <label for="review">Review</label>
      <textarea id="review" v-model="newReview.review"></textarea>
    </div>
    <input type="submit" value="Save" />
    <input type="button" value="Cancel" v-on:click="resetForm" />
  </form>
</template>

<script>
export default {
  props: ['productId'],
  data() {
    return {
      newReview: {}
    };
  },
  methods: {
    addNewReview() {
      this.newReview.productId = this.productId;
      this.$store.commit('ADD_REVIEW', this.newReview);
      // TODO: send the visitor back to the product page to see the new review
      this.$router.push({ name: 'product-details', params: { id: this.productId }, query: { rating: 1 } });
    },
    resetForm() {
      this.newReview = {};
    }
  }
};
</script>

<style scoped>
.form-element {
  margin-top: 10px;
}

.form-element label {
  display: block;
}

.form-element input,
.form-element select {
  height: 30px;
  width: 300px;
}

.form-element textarea {
  height: 60px;
  width: 300px;
}

form input[type=button] {
  width: 100px;
}

form input[type=submit] {
  width: 100px;
  margin-right: 10px;
}
</style>
