<template>
    <div>
        <h1>Add Review</h1>
        <add-review :product-id="$route.params.id"></add-review>
    </div>
</template>

<script>
import AddReview from '@/components/AddReview.vue';

export default {
    components: {
        AddReview
    }
}

</script>