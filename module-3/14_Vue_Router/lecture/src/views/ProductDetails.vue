<template>
    <div>
        <p v-if="product === undefined">Product Not Found</p>
        <div v-else>
        <h1>{{ product.name }}</h1>
        <p class="description">{{ product.description }}</p>
        <div class="well-display">
            <average-summary :reviews="product.reviews"></average-summary>
            <star-summary :rating="product.reviews" v-for="n in 5" :key="n"></star-summary>
        </div>
        <review-list :reviews="product.reviews"></review-list>
    </div>
</template>

<script>
import AverageSummary from '@/components/AverageSummary.vue';
import StarSummary from '@/components/StarSummary.vue';
import 



export default {

    components: {
        averageSummary,
        StarSummary
    },


    computed: {
        product() {
            const currId = 2;
            return this.$store.products.find(p => p.id === currId);
        }
    }
}


</script>