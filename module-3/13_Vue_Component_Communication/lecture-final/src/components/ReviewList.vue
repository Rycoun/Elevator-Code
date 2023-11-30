<template>
    <div>
        <review-display :review="reviewObj" v-for="reviewObj in filteredList" v-bind:key="reviewObj.id"></review-display>
    </div>
</template>

<script>
import ReviewDisplay from './ReviewDisplay.vue';

export default {
    components: {
        ReviewDisplay
    },

    computed: {
        filteredList() {
            return this.$store.state.reviews.filter(review => {
                if (review.rating === this.$store.state.ratingFilter || this.$store.state.ratingFilter === 0) {
                    return true;
                }

                return false;
            });
        },
    }
}

</script>
