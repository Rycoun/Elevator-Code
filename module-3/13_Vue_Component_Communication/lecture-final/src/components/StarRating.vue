<template>
    <div class="well" 
        v-bind:class="{ selected: $store.state.ratingFilter === ratingValue }" 
        v-on:click="updateRatingFilter"
    >
        <span class="amount">{{ numberOfStarReviews }}</span>
        {{ ratingValue }} Star Review
    </div>
</template>

<script>

export default {
    // props: ['ratingValue'],
    props: {
        ratingValue: {
            type: Number,
            required: true,
            validator(value) {
                return value >= 1 && value <= 5;
            } 
        },
        blah: {}
    },

    computed: {
        numberOfStarReviews() {
            return this.$store.state.reviews.filter(review => review.rating === this.ratingValue).length;
        },
    },

    methods: {
        updateRatingFilter() {
            this.$store.commit('UPDATE_RATING_FILTER', this.ratingValue);
        }
    }
}

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