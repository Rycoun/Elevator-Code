# Component Communication Tutorial

In this tutorial, you'll make a todo list, but using separate components instead of one component that handles everything. To make the todo list, you'll:

- Move the data for the todo list into a Vuex datastore.
- Create a new component that creates a new todo item and adds it to the list.
- Create a new component that summarizes the todo list so a user can see how many todos they have left and how many are completed.

Your starting code is a simple todo list in the `TodoList.vue` component. This component's design focuses only on showing todos and allow the user to interact with them. All of the data is currently hard-coded.

All of these components need access to the todo list data, so your first task is to move the data into a Vuex datastore.

> Before you start, make sure to run `npm install` to install any dependencies.

>**NOTE:** If you work with the page in the browser, refreshing the page causes it to lose any values you entered, boxes you've checked, or any other action you performed since the page doesn't persist changes.

## Step One: Move the data to a Vuex datastore

First, you'll move the todo list data to the Vuex datastore. The Vuex datastore acts as a central location to keep all of the application's data and provide access to that data to all components in the application.

Looking at the `TodoList` component now, the data for the list is in the component's data function:

``` JavaScript
export default {
  data() {
    return {
      todos: [
        { name: 'Wake up', done: false, category: 'Home' },
        { name: '5 Minute Morning Movement', done: false, category: 'Home' },
        { name: 'Meditate', done: false, category: 'Home' },
        { name: 'Brush teeth', done: false, category: 'Home' },
        { name: 'Shower', done: false, category: 'Home' },
        { name: 'Answer email', done: false, category: 'Work' },
        { name: 'Stand up meeting', done: false, category: 'Work' },
        { name: 'Fix a bug', done: false, category: 'Work' },
      ]
    }
  }
}
```

To move this from this component to the Vuex datastore, open the file that defines the Vuex datastore. That file is at `src/store/index.js`:

``` JavaScript
import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
    state: {},
    mutations: {},
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: true
  })
}
```

When putting data in a Vuex datastore, the data goes into the `state` section of the datastore.

Remove the `todos` from the `data` property found in the component, and then add them to the `state` property in the datastore. After you complete this step, the script block for the component is empty, as follows:

``` JavaScript
export default {

}
```

Once you complete this step, the `state` property in the Vuex store looks like this:

``` JavaScript
state: {
  todos: [
    { name: "Wake up", done: false, category: "Home" },
    { name: "5 Minute Morning Movement", done: false, category: "Home" },
    { name: "Meditate", done: false, category: "Home" },
    { name: "Brush teeth", done: false, category: "Home" },
    { name: "Shower", done: false, category: "Home" },
    { name: "Answer email", done: false, category: "Work" },
    { name: "Stand up meeting", done: false, category: "Work" },
    { name: "Fix a bug", done: false, category: "Work" }
  ]
},
```

Notice that `state` isn't a function, so you don't need to include a `return` statement. `state` is an object, and the values go directly in the curly brackets.

Now that the data is in the store, how do you get it back into your component?

Because the application uses Vuex, all the components have access to the datastore from the `this.$store` variable. You can use `this.$store` in the `v-for` call to get the todo data kept in the datastore:

``` HTML
<li v-for="todo in $store.state.todos" v-bind:key="todo.name"  v-bind:class="{ 'todo-completed': todo.done }">
  <input type="checkbox" name="checkbox-done" v-model="todo.done"/>
  <span v-bind:class="{ completed: todo.done }">{{todo.name}}</span>
</li>
```

> Remember that in the HTML portion of the component, you don't have to use `this` to access component information. The same is true for referencing `this.$store` in your HTML.

## Step Two: Add a component to the todos

Now you'll add a new component to the project.

To do that, create a new file under `src/components` called `NewTodo.vue`. Start by adding the following template to the file:

``` HTML
<template>

</template>

<script>
export default {

}
</script>

<style>

</style>
```

Next, add a form that accepts a new todo name and a category:

``` HTML
<template>
  <section class="new-todo">
    <form>
      <input type="text" name="input-name" placeholder="Name">
      <select name="select-category">
        <option value="" disabled>--- Select a category ---</option>
        <option value="Home">Home</option>
        <option value="Work">Work</option>
      </select>
      <button>Add</button>
    </form>
  </section>
</template>
```

Add the following CSS to make the form look better:

``` CSS
.new-todo {
  width:450px;
  background: #fff;
  margin: auto;
  font-family: 'Roboto Condensed', sans-serif;
  border-radius: 10px;
}
input, select, button {
  padding: 5px 5px;
  margin: 5px;
}
```

You can see with the form that there are two form elements: the input box for the name and the select for the category. Make a new data property object—`newTodo`—to store those values:

``` JavaScript
export default {
  data() {
    return {
      newTodo: {}
    }
  }
}
```

Next, add them to the HTML elements. Try doing this on your own before looking at the solution:

``` HTML
<input type="text" name="input-name" placeholder="Name" v-model="newTodo.name">
<select name="select-category" v-model="newTodo.category">
```

Now that you are capturing the data from the form, you need a way to save a new todo to the `todos` list in the datastore. Remember that the new component already has access to the Vuex datastore because all components automatically have access through the `this.$store` variable.

But adding data to a Vuex datastore isn't as straightforward as adding a line to the array. Changes to data must go through a Mutation.

### Step Three: Add a mutation to the datastore

Open the `src/store/index.js` file. The store has the `todos` list. Now add a new mutation called `ADD_NEW_TODO()` that takes a todo object and adds it to the array:

``` JavaScript
mutations: {
  ADD_NEW_TODO(state, todo) {
    state.todos.push(todo);
  }
},
```

Now you can call this mutation from the `NewTodo` component. Remember that mutations aren't called like methods. They're committed to the store, much like a change in `git` is committed to the repository.

Next, add a new method called `saveTodo` to the `NewTodo` component that takes the entered todo, gives it a `done` status of `false` and then commits it to the datastore. It can then clear the data for the next todo:

``` JavaScript
methods: {
  saveTodo() {
    this.newTodo.done = false;
    this.$store.commit('ADD_NEW_TODO', this.newTodo);
    this.newTodo = {};
  }
}
```

Configure the form to call this method. Try this on your own first before looking at the answer that follows:

``` HTML
<form v-on:submit.prevent="saveTodo">
```

Now add the `NewTodo` component to the `App.vue` component. Open the `src/App.vue` file and in the JavaScript section, change the following:

``` JavaScript
import TodoList from './components/TodoList.vue';
import NewTodo from './components/NewTodo.vue';

export default {
  components: {
    TodoList,
    NewTodo
  }
}
```

Afterwards, add the component's HTML tag to the `App` component's HTML:

``` HTML
<div id="todo-app">
  <todo-list />
  <new-todo />
</div>
```

Viewing the application now, you see the form underneath the todo list. When you fill out and submit the form, a new task is added to the datastore and displayed on the page with the other todos.

## Step Four: Recreate checkbox handling in TodoList

There's a problem with the application.

Since you need a mutation to change data in the Vuex store, you can't use `v-model` on data coming from a Vuex store. You'll notice that if you run this code in the browser and use any of the checkboxes, errors appear in the console even though the checkboxes still work.

Every change to Vuex data must go through a mutation, so the `TodoList` needs to change. To correct this, remove `v-model` from the checkbox and replace it with `v-on:click`. When you click the checkbox, you can call a method to commit a mutation.

First, change  how the checkbox is handled in the `TodoList`.

``` HTML
<input type="checkbox" name="checkbox-done" v-bind:checked="todo.done" v-on:click="checkTodoBox(todo)"/>
```

This binds the status of the checkbox to the `todo.done` value from Vuex, but binds the action of the change to a method called `checkTodoBox`. Now, write that method to send status changes to Vuex using a `FLIP_DONE` mutation.

``` JavaScript
methods: {
  checkTodoBox(todo) {
    this.$store.commit('FLIP_DONE', todo);
  }
}
```

Then, in the `store/index.js` file, you can add the new `FLIP_DONE` mutation:

``` JavaScript
mutations: {
  ADD_NEW_TODO(state, todo) {
    state.todos.push(todo);
  },
  FLIP_DONE(state, todo) {
    todo.done = ! todo.done;
  }
},
```

Since the `todo` is already in the `state`, you can reference it directly. And since its value changes using a mutation, you're following the rules of Vuex when updating the value.

You see in the interface that everything works as it did before, but now an error won't show in the console because you're following the rule that states that all data changes must happen in a mutation.

## Step Five: Add a component to show summary information

Now create a new component called `TodoSummary.vue` in the `src/components` folder. This component shows summary information about your list.

First, set up two boxes at the top to hold information for your "Home" todos and your "Work" todos:

``` HTML
<template>
  <section class="todo-summary">
    <div>
      <h3>Home</h3>
      <p>{{ completedHomeTodos }} / {{ totalHomeTodos }}</p>
    </div>
    <div>
      <h3>Work</h3>
      <p>{{ completedWorkTodos }} / {{ totalWorkTodos }}</p>
    </div>
  </section>
</template>
```

The following CSS makes these boxes better fit with the layout:

``` HTML
<style>
.todo-summary {
  width:600px;
  background: #fff;
  margin: auto;
  font-family: 'Roboto Condensed', sans-serif;
  border-radius: 10px;
  display: flex;
  justify-content:space-evenly;
}

.todo-summary div {
  border: 1px black solid;
  padding: 20px;
  border-radius: 5px;
  display: inline-block;
  text-align: center;
  width: 40%;
}

.todo-summary div p {
  font-size: 2em;
  margin: 3px;
}
</style>
```

Then add new computed properties, using the state from the datastore, to calculate how many of each todo category exists.

Try to complete this without looking at the following solutions and then check your solution against the included one:

``` JavaScript
computed: {
  totalHomeTodos() {
    return this.$store.state.todos.filter((todo) => {
      return todo.category === 'Home';
    }).length;
  },
  completedHomeTodos() {
    return this.$store.state.todos.filter((todo) => {
      return todo.done === true && todo.category === 'Home';
    }).length;
  },
  totalWorkTodos() {
    return this.$store.state.todos.filter((todo) => {
      return todo.category === 'Work';
    }).length;
  },
  completedWorkTodos() {
    return this.$store.state.todos.filter((todo) => {
      return todo.done === true && todo.category === 'Work';
    }).length;
  },
}
```

Then add the `TodoSummary` component to the `App` component:

``` HTML
<template>
  <div id="todo-app">
    <todo-summary />
    <todo-list />
    <new-todo />
  </div>
</template>

<script>
import TodoList from './components/TodoList.vue';
import NewTodo from './components/NewTodo.vue';
import TodoSummary from './components/TodoSummary.vue';

export default {
  components: {
    TodoList,
    NewTodo,
    TodoSummary
  }
}
</script>
```

You'll now see auto-updating summary information before the todo list.

## Summary

In this tutorial, you learned how to:

- Implement an application that utilizes components that work together
- Access data stored in Vuex from multiple components
- Modify data stored in Vuex using mutations
- Use data stored in Vuex within computed properties