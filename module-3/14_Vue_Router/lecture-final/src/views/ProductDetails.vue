<template>
    <div>
        <p v-if="product === undefined">Product Not Found</p>
        <div v-else>
            <nav class="actions">
                <router-link :to="{ name: 'home-page' }">Back Home</router-link>
                |
                <router-link :to="{ name: 'add-review', params: { id: product.id } }">Add Review</router-link>
            </nav>
            <h1>{{ product.name }}</h1>
            <p class="description">{{ product.description }}</p>
            <div class="well-display">
                <average-summary :reviews="product.reviews"></average-summary>
                <star-summary :reviews="product.reviews" :rating="n" v-for="n in 5" :key="n"></star-summary>
            </div>
            <review-list :reviews="product.reviews"></review-list>
        </div>
    </div>
</template>

<script>
import AverageSummary from '@/components/AverageSummary.vue';
import StarSummary from '@/components/StarSummary.vue';
import ReviewList from '@/components/ReviewList.vue';

export default {

    components: {
        AverageSummary,
        StarSummary,
        ReviewList
    },

    computed: {
        product() {
            const currId = Number(this.$route.params.id);
            return this.$store.state.products.find(p => p.id === currId);
        }
    }
}

</script>