<template>
    <div>
        <ul>
            <li v-for="(groceryItem, index) in groceries" v-bind:key="index" v-bind:class="classIsHere">
                {{ groceryItem }}
            </li>
        </ul>

        <input type="text" v-model="userTypesHere">

        <span>How many letters: {{ howManyLettersWereTyped }}</span>
    </div>
</template>

<script>
export default {
    computed: { 
        classIsHere() {
            const hasBananas = this.groceries.some(groceryItem => groceryItem === 'Bananas');

            if (hasBananas) {
                return 'bananas'
            } else {
                return 'no-bananas'
            }
        },

        howManyLettersWereTyped() {
            return this.userTypesHere.length;
        }
    },

    data() {
        return {
            groceries: ['Bananas', 'Oats', 'Milk', 'Bread'],
            userTypesHere: ''
        }
    }
}
</script>

<style>

</style>