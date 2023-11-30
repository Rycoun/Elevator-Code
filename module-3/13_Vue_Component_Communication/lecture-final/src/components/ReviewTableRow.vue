<template>
  <tr v-bind:class="{favorited : review.favorited}">
    <td>{{ review.title }}</td>
    <td nowrap>{{ review.reviewer }}</td>
    <td>{{ review.review }}</td>
    <td class="stars">
      <img
        src="../assets/star.png"
        class="star"
        v-bind:title="review.rating + ' Star Review'"
        v-for="n in review.rating"
        v-bind:key="n"
      />
    </td>
    <td>
      <input type="checkbox" name="favorite-checkbox" v-bind:checked="review.favorited" v-on:change="onFavoritedChange(review)" />
    </td>
  </tr>
</template>

<script>
export default {
  props: ['review'],
  methods: {
    onFavoritedChange() {
      this.$store.commit('FLIP_FAVORITED', this.review);
    }
  }
};
</script>

<style scoped>
td {
  text-align: left;
  padding-right: 10px;
  vertical-align: top;
}

tr:nth-child(even) {
  background-color: rgb(238, 238, 238);
}

/*
  Table rows are awkward to apply a border to. outline is a good alternative that provides
  a similar visual effect and doesn't affect the page layout
*/
.favorited {
  outline: 3px solid rgba(255, 255, 0, 0.5);
}

.stars {
  display: flex;
}

.star {
  width: 20px;
}
</style>
