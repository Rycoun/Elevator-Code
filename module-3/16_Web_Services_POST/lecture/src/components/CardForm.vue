<template>
  <form v-on:submit.prevent="submitForm" class="cardForm">
    <div class="form-group">
      <label for="title">Title:</label>
      <input id="title" type="text" class="form-control" v-model="editCard.title" autocomplete="off" />
    </div>
    <div class="form-group">
      <label for="tag">Tag:</label>
      <select id="tag" class="form-control" v-model="editCard.tag">
        <option value="Feature Request">Feature Request</option>
        <option value="Design">Design</option>
        <option value="Q&A">Q&A</option>
      </select>
      <label for="status">Status:</label>
      <select id="status" class="form-control" v-model="editCard.status">
        <option value="Planned">Planned</option>
        <option value="In Progress">In Progress</option>
        <option value="Completed">Completed</option>
      </select>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea id="description" class="form-control" v-model="editCard.description"></textarea>
    </div>
    <button class="btn btn-submit">Submit</button>
    <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
  </form>
</template>

<script>
import boardService from '../services/BoardService';

export default {
  props: {
    card: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      // Since props are read-only, don't bind to 'card' directly. Instead, create
      // a new object initialized with the same property values.
      editCard: {
        id: this.card.id,
        boardId: this.card.boardId,
        title: this.card.title,
        description: this.card.description,
        status: this.card.status,
        tag: this.card.tag,
        avatar: this.card.avatar,
        date: this.card.date
      },
    };
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        return;
      }
      if (this.editCard.id === 0) {
        // add
        boardService
          .addCard(this.editCard)
          .then(response => {
            if (response.status === 201) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: 'A new card was added.',
                  type: 'success'
                }
              );
              this.$router.push({ name: 'BoardView', params: { id: this.editCard.boardId } });
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'adding');
          });
      } else {
        boardService
          .updateCard(this.editCard)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: `Card ${this.editCard.id} was updated.`,
                  type: 'success'
                }
              );
              this.$router.push({ name: 'BoardView', params: { id: this.editCard.boardId } });
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'updating');
          });
      }
    },
    cancelForm() {
      this.$router.push({ name: 'BoardView', params: { id: this.editCard.boardId } });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
          "Error " + verb + " card. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " card. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " card. Request could not be created.");
      }
    },
    validateForm() {
      let msg = '';
      if (this.editCard.title.length === 0) {
        msg += 'The new card must have a title. ';
      }
      if (this.editCard.status.length === 0) {
        msg += 'The new card must have a status.';
      }
      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    },
  },
};
</script>


<style scoped>
.cardForm {
  padding: 10px;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}

label {
  display: inline-block;
  margin-bottom: 0.5rem;
}

.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}

textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}

select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}

</style>
