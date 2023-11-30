# Vue Router Exercise

In this exercise, you'll take components from the previous exercise and turn them into a full, single page application using the Vue Router. You may need to make some small changes to some of the existing components, but most of the work is done by creating views and setting their routes.

Take a moment to review the store's `index.js` file. Each book now contains two additional pieces of information: the number of pages and a link to more info. `NewBookForm.vue` prompts for the additional info. The usage of the new data is described below. 

## Step One: Show a home page with best sellers and new releases

Implement a `HomeView` view that displays the `BestSellerList` and `NewReleasesList` components. Make this view appear when the path is `/`.

After you complete this step, all tests under "Step One Tests" pass.

![Best Seller and New Releases Page at /](./img/best-seller-and-new-releases-page.png)

>**NOTE:** If you work with the page in the browser, refreshing the page causes it to lose any values you entered, boxes you've checked, or any other action you performed since the page doesn't persist changes.

## Step Two: Display the reading list

Implement a `MyBooksView` view that displays the `ReadingList` component. Make this view appear when the path is `/myBooks`.

After you complete this step, all tests under "Step Two Tests" pass.

![Reading List at /](./img/vue-router-exercise-step-two.png)

## Step Three: Implement navigation in the App component

Implement navigation in a `<nav>` element at the top of the `App` component. There must be a link to the `HomeView` view and a link to the `MyBooksView` view.

After you complete this step, all tests under "Step Three Tests" pass.

![Top Navigation in App](./img/top-navigation.png)

## Step Four: Display the book form

Implement a `NewBookView` view that displays the `NewBookForm` component at the path `/addBook`.

After you complete this step, all tests under "Step Four Tests" pass.

![New Book Page at /addBook](./img/new-book-page.png)

## Step Five: Integrate the new book form into application

Add a link to the bottom of the `MyBooksView` view that links to the `NewBookView` view. Also, change the submission logic of the `NewBookForm` component so that it goes back to the `MyBooksView` view when a book is successfully added to the list.

> Hint: You need to change the `NewBookForm` component to do that navigation using the `this.$router` object.

![Link to the new book form](./img/new-book-link.png)

After you complete this step, all tests under "Step Five Tests" pass.

## Step Six: Create a book detail view

Create a new component called `BookDetails` that shows all of a book's details on the screen. A basic version would be something like this:

![Book Detail Page](./img/book-detail-page.png)

The <u>More Information</u> link opens the book's `moreInfoLink` in a new browser tab. (Hint: Refer to the `target` attribute of the [anchor tag](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a) for how to do this.)

Create a view and a dynamic route with the path `/book/` plus the `isbn` number on the end—for example, `/book/9781400079278`. Use that number to look up the book for the `BookDetails` component.

Finally, link the `BookCard` components in the `ReadingList` component to this new route, making sure to have the book's `isbn` as part of the link when the `BookCard` is clicked.

> Hint: Look at the `ReadingList` component to determine the source of the data you'll use in the `BookDetails` component. Consider what array methods you know that return an element or elements with a specific value. `BookDetails` must receive a single `book` object.

![Reading List with individual book links](./img/vue-router-exercise-completed.png)

After you complete this step, all tests now pass.