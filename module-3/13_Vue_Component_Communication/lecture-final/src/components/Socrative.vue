<template>
  <div>
    <button v-on:click="buttonClicked(1)">
      {{ buttonTextCustomized("hello") }}
    </button>

    <input
      type="button"
      v-bind:id="firstButtonText"
      v-bind:value="firstButtonText"
    />

    <div
      v-bind:class="{ inactive: user.status === 'Inactive' }"
      v-for="(user, index) in users"
      v-bind:key="index"
    >
      <p></p>
    </div>
  </div>
</template>

<script>
export default {
  components: {

  },

  data() {
      return {
          myData: 0
      }
  },

  computed: {
    firstButtonText() {
      if ("one".length === 3) {
        return "One";
      }

      return "Two";
    },
  },

  methods: {
    buttonTextCustomized(blah) {
      return "blah " + blah;
    },

    buttonClicked(id) {
        console.log(id);

        this.myData = id;
    },
  },
};
</script>

<style>
.inactive {
  color: red;
}
</style>