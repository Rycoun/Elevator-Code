<template>
  <div>
    <div v-if="isLoading">
      <h1>My Kanban Boards</h1>
      <div class="loading">
        <img src="../assets/ping_pong_loader.gif" />
      </div>
    </div>
    <div v-else>
      <h1>My Kanban Boards</h1>
      <boards-list v-bind:boards="boards" />
      <div class="form-section">
        <button class="btn btn-submit" v-if="!showAddBoard" v-on:click="showAddBoard = true">Add
          Board</button>
        <form v-if="showAddBoard">
          <fieldset>
            <label for="board-title">Board Title: </label>
            <input type="text" id="board-title" name="board-title" class="form-control" v-model="newBoard.title" />
          </fieldset>
          <fieldset>
            <label for="background-color">Background Color: </label>
            <input type="text" id="background-color" name="background-color" class="form-control"
              v-model="newBoard.backgroundColor" />
          </fieldset>
          <button class="btn btn-submit" v-on:click.prevent="saveNewBoard">Save</button>
          <button class="btn btn-cancel" v-on:click="resetAddForm">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BoardsList from '../components/BoardsList.vue';
import boardService from '../services/BoardService';

export default {
  components: {
    BoardsList
  },
  data() {
    return {
      boards: [],
      isLoading: true,
      showAddBoard: false,
      newBoard: {
        title: '',
        backgroundColor: this.randomBackgroundColor()
      }
    };
  },
  methods: {
    retrieveBoards() {
      boardService.getBoards().then(response => {
        this.boards = response.data;
        this.isLoading = false;
      })
        .catch(error => {
          if (error.response) {
            this.$store.commit('SET_NOTIFICATION',
              "Error getting boards list. Response received was '" + error.response.statusText + "'.");
          } else if (error.request) {
            this.$store.commit('SET_NOTIFICATION', "Error getting boards list. Server could not be reached.");
          } else {
            this.$store.commit('SET_NOTIFICATION', "Error getting boards list. Request could not be created.");
          }
        });
    },
    validateAddForm() {
      let msg = '';
      if (this.newBoard.title.length === 0) {
        msg += 'The new board must have a title. ';
      }
      if (this.newBoard.backgroundColor.length === 0) {
        msg += 'The new board must have a background color.';
      }
      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    },
    saveNewBoard() {
    },
    resetAddForm() {
      this.showAddBoard = false;
      this.newBoard = {
        title: '',
        backgroundColor: this.randomBackgroundColor()
      }

    },
    randomBackgroundColor() {
      return "#" + this.generateHexCode();
    },
    generateHexCode() {
      var bg = Math.floor(Math.random() * 16777215).toString(16);
      if (bg.length !== 6) bg = this.generateHexCode();
      return bg;
    }
  },
  created() {
    this.retrieveBoards();
  }
};
</script>
<style scoped>
.form-section {
  margin: 15px 0;

}

.form-control {
  margin-bottom: 10px;
}

.btn {
  margin-bottom: 35px;
}

.loading {
  flex: 3;
}
</style>
