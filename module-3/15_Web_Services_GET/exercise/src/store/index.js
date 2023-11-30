import { createStore as _createStore } from 'vuex';

// Timeout in ms. Notifications are removed after they timeout.
const NOTIFICATION_TIMEOUT = 3000;

export function createStore() {
  return _createStore({
    state: {
      // notification is an object with the following properties:
      //    message: Required. String with message text
      //    type: 'error', 'success'. Default = 'error';
      //    timeout: Milliseconds to display the message. Default = NOTIFICATION_TIMEOUT
      notification: null,
    },
    mutations: {
      SET_NOTIFICATION(state, notification) {
        // Clear the current notification if one exists
        if (state.notification) {
          this.commit('CLEAR_NOTIFICATION');
        }

        if (typeof notification === 'string') {
          // If only a string was sent, create a notification object with defaults
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          }
        } else {
          // Else add default values if needed
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }

        // Set the notification in state
        state.notification = notification;

        // Clear the message after timeout (see https://developer.mozilla.org/en-US/docs/Web/API/setTimeout)
        notification.timer = window.setTimeout(() => {
          this.commit('CLEAR_NOTIFICATION');
        }, notification.timeout);
      },
      CLEAR_NOTIFICATION(state) {
        if (state.notification && state.notification.timer) {
          window.clearTimeout(state.notification.timer);
        }
        state.notification = null;
      },
    },
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: true
  })
}
