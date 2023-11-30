// add pageTitle
const pageTitle = 'My Shopping List';

// add groceries
const groceries = ['Juice', 'Taco Meat', 'Steak', 'Rice', 'Salad', 'Sun Chips',
'Protein Bar', 'Milk', 'Candy', 'Pizza'];
/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
function setPageTitle() {
  const title = document.querySelector('#title');
  title.innerText = pageTitle;
}
/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() {
  const list = document.querySelector('#groceries');

  for (let i = 0; i < groceries.length; i++) {
    const groceryItem = document.createElement('li');
    groceryItem.innerText = groceries[i];
    list.insertAdjacentElement('beforeend', groceryItem)
  }
}
/**
 * This function will be called when the button is clicked. You will need to get a reference
 * to every list item and add the class completed to each one
 */
function markCompleted() {
  const checkItem = document.querySelectorAll('#groceries li');
  checkItem.forEach(element => {
    element.setAttribute('class','completed');
  })
}

setPageTitle();

displayGroceries();

// Don't worry too much about what is going on here, we will cover this when we discuss events.
document.addEventListener('DOMContentLoaded', () => {
  // When the DOM Content has loaded attach a click listener to the button
  const button = document.querySelector('.btn');
  button.addEventListener('click', markCompleted);
});
