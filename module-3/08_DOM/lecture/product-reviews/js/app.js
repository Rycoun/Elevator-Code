const bookName = 'Cigar Parties for Dummies';
const description = 'Host and plan the perfect cigar party for all of your squirrelly friends.';
const reviews = [
  {
    reviewer: 'Malcolm Madwell',
    title: 'What a book!',
    review:
    "It certainly is a book. I mean, I can see that. Pages kept together with glue and there's writing on it, in some language. Yes indeed, it is a book!",
    rating: 3
  },
  {
    reviewer: 'Tim Ferriss',
    title: 'Had a cigar party started in less than 4 hours.',
    review:
      "It should have been called the four hour cigar party. That's amazing. I have a new idea for muse because of this.",
    rating: 4
  },
  {
    reviewer: 'Ramit Sethi',
    title: 'What every new entrepreneurs needs. A door stop.',
    review:
      "When I sell my courses, I'm always telling people that if a book costs less than $20, they should just buy it. If they only learn one thing from it, it was worth it. Wish I learned something from this book.",
    rating: 1
  },
  {
    reviewer: 'Gary Vaynerchuk',
    title: 'And I thought I could write',
    review:
      "There are a lot of good, solid tips in this book. I don't want to ruin it, but prelighting all the cigars is worth the price of admission alone.",
    rating: 3
  }
];

/**
 * Add the product name to the page title
 * Get the page title by the id and the query the .name selector
 * once you have the element you can add the product name to the span.
 */
function setPageTitle() {
const h1Element = document.getElementById('page-title');
const spanElement = h1Element.querySelector('.name');
spanElement.innerText - bookName;

}

/**
 * Add the product description to the page.
 */
function setPageDescription() {
  document.querySelector('.description').innerText = description;

}

/**
 * Display all of the reviews on the page.
 * Loop over the array of reviews and use some helper functions
 * to create the elements needed for the markup and add them to the DOM.
 */
function displayReviews() {
  const mainElement = document.getElementById('main');

  const reviewContain = document.createElement('section');



  reviews.forEach(reviewObj => {
    
    const reviewDiv = document.createElement('div');
    reviewDiv.classList.add('review');

    const reviewerH2 = document.createElement('h2');
    reviewerH2.innerText = reviewObj.reviewer; 

    addReviewer(reviewDiv, reviewObj.reviewer);
    addRating(reviewDiv, reviewObj.rating);
    addTitle(reviewDiv, reviewObj.title);
    addReview(reviewDiv, reviewObj.review);


    reviewDiv.appendChild(reviewerH2);
    mainElement.appendChild(reviewDiv);
  });

}

/**
 * Create a new h2 element with the name of the reviewer and append it to
 * the parent element that is passed to me.
 *
 * @param {HTMLElement} parent: The element to append the reviewer to
 * @param {string} name The name of the reviewer
 */
function addReviewer(parent, name) {}

/**
 * Add the rating div along with a star image for the number of ratings 1-5
 * @param {HTMLElement} parent
 * @param {Number} numberOfStars
 */
function addRating(parent, numberOfStars) {
  const ratingDiv = document.createElement('div');
  ratingDiv.classList.add('rating');

  for (let i = 0; i < numberOfStars; i++) {
    const startElement = document.createElement('img');
    startElement.src = 'img/star.png';
    startElement.classList.add('ratingStar');

    ratingDiv.appendChild(startElement);
  }
  parent.appendChild(ratingDiv);
}

/**
 * Add an h3 element along with the review title
 * @param {HTMLElement} parent
 * @param {string} title
 */
function addTitle(parent, title) {
  const titleElement = document.createElement('h3');
  titleElement.innerText = title;
  
  parent.appendChild(titleElement);
}

/**
 * Add the product review
 * @param {HTMLElement} parent
 * @param {string} review
 */
function addReview(parent, review) {
  const reviewElement = document.createElement('p');
  reviewElement.innerText = review;

  parent.appendChild(reviewElement);
}

// set the product reviews page title
setPageTitle();
// set the product reviews page description
setPageDescription();
// display all of the product reviews on the page
displayReviews();
