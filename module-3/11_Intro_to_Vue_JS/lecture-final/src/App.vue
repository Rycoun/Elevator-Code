<template>
  <div class="main">
    <h1>Product Reviews for {{ name }}</h1>
    <p>{{ description }}</p>

    <div class="well-display">
      <div class="well">
        <span class="amount">{{ averageRating }}</span>
        Average Rating
      </div>
      <div class="well">
        <span class="amount">{{ numberOf1StarReviews }}</span>
        1 Star Review
      </div>
      <div class="well">
        <span class="amount">{{ numberOf2StarReviews }}</span>
        2 Star Reviews
      </div>
      <div class="well">
        <span class="amount">{{ numberOf3StarReviews }}</span>
        3 Star Reviews
      </div>
      <div class="well">
        <span class="amount">{{ numberOf4StarReviews }}</span>
        4 Star Reviews
      </div>
      <div class="well">
        <span class="amount">{{ numberOf5StarReviews }}</span>
        5 Star Reviews
      </div>
    </div>

    <section class="review" v-bind:class="{ favorited: review.isFavorite }" v-for="review in reviews" v-bind:key="review.id">
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

  </div>
</template>

<script>
export default {
  components: {},

  computed: {
    averageRating() {
      const sum = this.reviews.reduce((prev, curr) => prev + curr.rating, 0);

      return (sum / this.reviews.length).toFixed(2);
    },

    numberOf1StarReviews() {
      return this.reviews.filter(review => review.rating === 1).length;
    },

    numberOf2StarReviews() {
      return this.reviews.filter(review => review.rating === 2).length;
    },

    numberOf3StarReviews() {
      return this.reviews.filter(review => review.rating === 3).length;
    },

    numberOf4StarReviews() {
      return this.reviews.filter(review => review.rating === 4).length;
    },

    numberOf5StarReviews() {
      return this.reviews.filter(review => review.rating === 5).length;
    }
  },

  data() {
    return {
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
      ]
    };
  },
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

</style>
