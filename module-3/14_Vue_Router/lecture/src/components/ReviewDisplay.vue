<template>
  <div class="review" v-bind:class="{ favorited: review.favorited }" v-bind:key="review.id">
    <h4>{{ review.reviewer }}</h4>
    <div class="rating">
      <img
        src="../assets/star.png"
        v-bind:title="review.rating + ' Star Review'"
        class="rating-star"
        v-for="n in review.rating"
        v-bind:key="n"
      />
    </div>
    <h3>{{ review.title }}</h3>
    <p>{{ review.review }}</p>
    <p>
      Favorite?
      <input
        type="checkbox" v-bind:id="'favorite_' + review.id"
        v-bind:checked="review.favorited"
        v-on:change="onFavoritedChange(review)"
      />
    </p>
  </div>
</template>

<script>
export default {
  props: ['review'],
  methods: {
    onFavoritedChange(review) {
      this.$store.commit('FLIP_FAVORITED', review);
    }
  }
};
</script>

<style scoped>
.review {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
}

.review p {
  margin: 20px;
}

.review h3 {
  display: inline-block;
}

.review h4 {
  font-size: 1rem;
}

.favorited {
  background-color: lightyellow;
}

.rating {
  height: 2rem;
  display: inline-block;
  vertical-align: top;
  margin: 0 0.5rem;
}

.rating-star {
  height: 100%;
}
</style>
