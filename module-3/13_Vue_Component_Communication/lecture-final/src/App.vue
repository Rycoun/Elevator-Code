<template>
  <div class="main">
    <h1>Product Reviews for {{ $store.state.name }}</h1>
    <p>{{ $store.state.description }}</p>

    <div class="well-display">
      <average-rating></average-rating>
      <!-- <star-rating v-bind:ratingValue="1"></star-rating>
      <star-rating v-bind:rating-value="2"></star-rating>
      <star-rating v-bind:rating-value="3"></star-rating>
      <star-rating v-bind:rating-value="4"></star-rating>
      <star-rating v-bind:rating-value="5"></star-rating> -->

      <star-rating v-for="n in 5" :key="n" :rating-value="n"></star-rating>   
      <!-- <star-rating :rating-value="0"></star-rating> -->
    </div>

    <add-review></add-review>

    <review-list></review-list>
  </div>
</template>

<script>
import AddReview from './components/AddReview.vue';
import AverageRating from './components/AverageRating.vue';
import ReviewList from './components/ReviewList.vue';
import StarRating from './components/StarRating.vue';

export default {

  components: {
    AddReview,
    AverageRating,
    ReviewList,
    StarRating
  },

  computed: {
    

    getShowHideFormText() {
      if (this.isFormShowing) {
        return 'Hide Form';
      }

      return 'Show Form';
    },
  },

  data() {
    return {
      selectAllCheckbox: false,

      selectedCheckboxes: [],

      names: ['Walt', 'Tom', 'Jennifer', 'Julie'],
      someObj: {
        myText: '',
        selectedValue: "2"
      },


       reviews: [{
        id: 1,
        reviewer: 'Tom Anderson',
        title: 'Great Book!',
        rating: 5,
        review: 'This was a really good breakdown of OOP',
        isFavorite: false
      },
      {
        id: 2,
        reviewer: 'Walt Impellicceiri',
        title: 'It was okay',
        rating: 3,
        review: 'Could use some updating',
        isFavorite: true
      }
      ],
      

      ratingFilter: 0
    };
  },

  methods: {
    numberOfStarReviews(rating) {
      return this.reviews.filter(review => review.rating === rating).length;
    },

    starReviewText(rating) {
      if (this.numberOfStarReviews(rating) === 1) {
        return `${rating} Star Review`;
      }

      return `${rating} Star Reviews`;
    },

    


    updateFilter(newValue) {
      this.ratingFilter = newValue;
    },

    selectAllChanged(event) {
      const selectAllCheckbox = event.currentTarget;

      if (selectAllCheckbox.checked) {
        // check all the boxes
        this.selectedCheckboxes = [];
        this.names.forEach(name => {
          this.selectedCheckboxes.push(name);
        });
        
      } else {
        // uncheck all the boxes
        this.selectedCheckboxes = [];
      }
    }
  }
};
</script>

<style scoped>
.main {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.main {
  margin: 1rem 0;
  padding: 0 10rem;
}

.well-display {
  display: flex;
  justify-content: space-around;
  margin-bottom: 1rem;
}

</style>
