<template>
  <div>
    <a href="#" v-on:click.prevent="isFormShowing = !isFormShowing">
      {{ isFormShowing ? "Hide Form" : "Show Form" }}
    </a>

    <form v-show="isFormShowing" v-on:submit.prevent="saveReview">
      <div class="form-element">
        <label for="reviewer">Name:</label>
        <input type="text" id="reviewer" v-model="newReview.reviewer" />
      </div>
      <div class="form-element">
        <label for="title">Title:</label>
        <input type="text" id="title" v-model="newReview.title" />
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
      <input type="submit" value="Save" />
      <input type="button" value="Reset" v-on:click="resetForm" />
    </form>
  </div>
</template>

<script>

export default {
    data() {
        return {
            isFormShowing: false,
            newReview: {
                reviewer: '',
                title: '',
                rating: 1,
                review: ''
            },
        }
    },

    methods: {
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

            this.$store.commit('ADD_REVIEW', this.newReview);

            this.resetForm();

            this.isFormShowing = false;
        },
    }
}

</script>

<style scoped>

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