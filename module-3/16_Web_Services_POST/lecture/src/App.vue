<template>
  <div class="container">
    <!-- If there is a message in application state, display it  -->
    <div v-bind:class="notificationClass" v-show="notification" v-on:click="clearNotification">
      {{ notification?.message }}
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    notification() {
      return this.$store.state.notification;
    },
    notificationClass() {
      return {
        'status-message': true,
        error: this.notification?.type?.toLowerCase() === 'error',
        success: this.notification?.type?.toLowerCase() === 'success'
      };
    }
  },
  methods: {
    clearNotification() {
      this.$store.commit('CLEAR_NOTIFICATION');
    },
  }
};
</script>

<style>
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto,
    'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji',
    'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';
}

.container {
  width: 100%;
  max-width: 1100px;
  margin: 0 auto;
}

.loading {
  display: flex;
  justify-content: center;
  align-items: center;
}

.btn {
  display: inline-block;
  font-weight: 400;
  color: #212529;
  text-align: center;
  vertical-align: middle;
  border: 1px solid transparent;
  padding: .375rem .75rem;
  margin-right: 10px;
  margin-bottom: 5px;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: .25rem;
  cursor: pointer;
}

.btn-submit {
  color: rgba(255,255,255,0.7);
  background-color: #0062cc;
  border-color: #005cbf;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
}

.btn-submit:hover {
  color: rgba(255,255,255,1.0);
  border-color: #fff;
}
fieldset {
  border: none;
}

.btn-cancel {
  color: rgba(255,255,255,0.7);
  background-color: #dc3545;
  border-color: #dc3545;
}

.btn-cancel:hover {
  color: rgba(255,255,255,1.0);
  border-color: rgba(255,255,255,1.0);
}

.status-message {
  display: block;
  border-radius: 5px;
  font-weight: bold;
  font-size: 1rem;
  text-align: center;
  padding: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

.status-message.success {
  background-color: #90ee90;
}

.status-message.error {
  background-color: #f08080;
}

.board-actions a:link,
.board-actions a:visited {
  color: blue;
  text-decoration: none;
}

.board-actions a:hover {
  text-decoration: underline;
}
</style>
