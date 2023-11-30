<template>
    <section class="review" v-bind:class="{ favorited: review.favorited }">
        <h4>{{ review.reviewer }}</h4>
        <div class="rating">
        <img v-bind:title="review.rating + ' star review'" class="ratingStar" v-for="n in review.rating" v-bind:key="n" src="@/assets/star.png" alt="Rating Star">
        </div>
        <h3>{{ review.title }}</h3>
        <p>{{ review.review }}</p>
        <label for="chk-favorite">Favorite?</label><input type="checkbox" id="chk-favorite" v-bind:checked="review.favorited" v-on:change="favoritedChanged">
    </section>
</template>

<script>

export default {
    props: ['review'],

    methods: {
      favoritedChanged() {
        this.$store.commit('FAVORITED_CHANGED', this.review);
      }
    }
}

</script>


<style scoped>
.favorited {
  background-color: lightyellow;
}

.rating {
  height: 2rem;
  display: inline-block;
  vertical-align: top;
  margin: 0 0.5rem;
}

.rating img {
  height: 100%;
}

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
</style>