<template>
  <div class="main">
    <review-stuff></review-stuff>
    <review-stuff></review-stuff>

    <h1>Product Reviews for {{ name }}</h1>
    <p>{{ description }}</p>

    <div class="well-display">
      <div class="well" v-bind:class="{ selected: ratingFilter === 0 }" v-on:click="ratingFilter = 0">
        <span class="amount">{{ averageRating }}</span>
        Average Rating
      </div>

      <!-- <div class="well" v-for="n in 5" v-bind:key="n" v-bind:class="{ selected: ratingFilter === n }" v-on:click="ratingFilter = n">
        <span class="amount">{{ numberOfStarReviews(n) }}</span>
        {{ starReviewText(n) }}
      </div> -->


      <div class="well" v-bind:class="{ selected: ratingFilter === 1 }" v-on:click="ratingFilter = 1">
        <span class="amount">{{ numberOf1StarReviews }}</span>
        1 Star Review
      </div>
      <div class="well" v-bind:class="{ selected: ratingFilter === 2 }" v-on:click="updateFilter(2)">
        <span class="amount">{{ numberOf2StarReviews }}</span>
        {{ twoStarReviewText }}
      </div>
      <div class="well" v-bind:class="{ selected: ratingFilter === 3 }" v-on:click="updateFilter(3)">
        <span class="amount">{{ numberOf3StarReviews }}</span>
        {{ numberOf3StarReviews === 1 ? '3 Star Review' : '3 Star Reviews' }}
      </div>
      <div class="well" v-bind:class="{ selected: ratingFilter === 4 }" v-on:click="updateFilter(4)">
        <span class="amount">{{ numberOf4StarReviews }}</span>
        4 Star Review{{ numberOf4StarReviews === 1 ? '' : 's' }}
      </div>
      <div class="well" v-bind:class="{ selected: ratingFilter === 5 }" @click="updateFilter(5)">
        <span class="amount">{{ numberOf5StarReviews }}</span>
        {{ starReviewText(5) }}
      </div>
    </div>

    <a href="#" v-on:click.prevent="isFormShowing = !isFormShowing">
      {{ isFormShowing ? 'Hide Form' : 'Show Form' }}
    </a>

    <form v-show="isFormShowing" v-on:submit.prevent="saveReview">
      <div class="form-element">
        <label for="reviewer">Name:</label>
        <input type="text" id="reviewer" v-model="newReview.reviewer">
      </div>
      <div class="form-element">
        <label for="title">Title:</label>
        <input type="text" id="title" v-model="newReview.title">
      </div>
      <div class="form-element">
        <label for="rating">Rating:</label>
        <select id="rating" v-model.number="newReview.rating">
          <option value="1">1 Star</option>
          <option value="2">2 Stars</option>
          <option value="3">3 Stars</option>
          <option value="4">4 Stars</option>
          <option value="5">5 Stars</option>
        </select>
      </div>
      <div class="form-element">
        <label for="review">Review:</label>
        <textarea id="review" v-model.lazy="newReview.review"></textarea>
      </div>
      <input type="submit" value="Save">
      <input type="button" value="Reset" v-on:click="resetForm">
    </form>

    <section class="review" v-bind:class="{ favorited: review.isFavorite }" v-for="review in filteredList" v-bind:key="review.id">
      <h4>{{ review.reviewer }}</h4>
      <div class="rating">
        <img v-bind:title="review.rating + ' star review'" class="ratingStar" v-for="n in review.rating" v-bind:key="n" src="./assets/star.png" alt="Rating Star">
      </div>
      <h3>{{ review.title }}</h3>
      <p>{{ review.review }}</p>
      <label for="chk-favorite">Favorite?</label><input type="checkbox" id="chk-favorite" v-model="review.isFavorite">
    </section>


    <input type="text" v-model="someObj.myText">

    <select v-model="someObj.selectedValue">
      <option value="1">One</option>
      <option value="2">Two</option>
      <option value="3">Three</option>
    </select>

    <br>

    <input type="checkbox" v-bind:checked="selectedCheckboxes.length === names.length" v-on:change="selectAllChanged"> Select All
    <div v-for="name in names" v-bind:key="name">
      <input type="checkbox" v-model="selectedCheckboxes" v-bind:value="name"> {{ name }}
    </div>


    <input type="button" value="Hello" v-bind:disabled="reviews.length === 2">
  </div>
</template>

<script>
import ReviewStuff from './components/ReviewStuff.vue';

export default {
  components: {
    ReviewStuff
  },

  computed: {
    filteredList() {
      return this.reviews.filter(review => {
        if (review.rating === this.ratingFilter || this.ratingFilter === 0) {
          return true;
        }

        return false;
      });
    },

    getShowHideFormText() {
      if (this.isFormShowing) {
        return 'Hide Form';
      }

      return 'Show Form';
    },

    averageRating() {
      const sum = this.reviews.reduce((prev, curr) => prev + curr.rating, 0);

      return (sum / this.reviews.length).toFixed(2);
    },

    numberOf1StarReviews() {
      return this.numberOfStarReviews(1);
    },

    numberOf2StarReviews() {
      return this.numberOfStarReviews(2);
    },

    numberOf3StarReviews() {
      return this.numberOfStarReviews(3);
    },

    numberOf4StarReviews() {
      return this.numberOfStarReviews(4);
    },

    numberOf5StarReviews() {
      return this.numberOfStarReviews(5);
    },


    twoStarReviewText() {
      if (this.numberOf2StarReviews === 1) {
        return '2 Star Review';
      }

      return '2 Star Reviews';
    }
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


      name: "Head First Design Patterns",
      description: 'A brain friendly guide to building extensible and maintainable object-oriented software',
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
      newReview: {
        reviewer: '',
        title: '',
        rating: 1,
        review: ''
      },
      isFormShowing: false,
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

    resetForm() {
      this.newReview = {
        reviewer: '',
        title: '',
        rating: 1,
        review: ''
      };
    },

    saveReview(event) {
      // event.preventDefault(); did this up above using .prevent modifer

      let largestId = 1;
      for (let i = 0; i < this.reviews.length; i++) {
        if (this.reviews[i].id > largestId) {
          largestId = this.reviews[i].id;
        }
      }

      this.newReview.id = largestId + 1;

      this.reviews.unshift(this.newReview);

      this.resetForm();

      this.isFormShowing = false;
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

<style>
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


.form-element {
  margin-top: 10px;
}

.form-element label {
  display: block;
}

.form-element input,
.form-element select {
  height: 30px;
  width: 300px;
}

.form-element textarea {
  height: 60px;
  width: 300px;
}

form input[type=button] {
  width: 100px;
}

form input[type=submit] {
  width: 100px;
  margin-right: 10px;
}

</style>
