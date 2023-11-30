let rowCount = 3;
/**
 * Event propagation is a useful mechanism whereby an event
 * will bubble upward toward the root node of the document.
 * 
 * This can be useful for the following reasons:
 * 
 * 1) An event target may be best handled on a parent element even 
 *    if a child element will cause the event to fire.
 * 
 *    This is demonstrated below by `tryWithManyEventListeners`
 * 
 *    The table row is what I want to add a class to, but the table cell
 *    is what is clicked. So I add the event handler to the table row
 *    and when the table cell is clicked, the event bubbles upward to the 
 *    table row.
 * 
 * 
 * 2) Less memory usage because you can accomplish the same task
 *    with fewer event handlers.
 * 
 *    This is demonstrated below by `tryWithOneEventListener`
 * 
 *    By applying the handler to the table instead of each row,
 *    there is a memory usage of O(1) rather than O(N).
 * 
 * 
 * 3) It can save the need to add/remove extra event handlers when 
 *    adding/removing content to/from the DOM.  
 * 
 *    Compare the event handler for btnAddRow in each implementation.
 *    `tryWithOneEventListener` has less code than 
 *    `tryWithManyEventListeners` to accomplish the same task. More 
 *    importantly, it has fewer events for us to keep track of.
 */

function helper_buildNewRow() {
  rowCount++;
  const newRow = document.createElement('tr');

  const newColumn1 = document.createElement('td');
  newColumn1.innerText = `Row ${rowCount}, Column 1`;
  const newColumn2 = document.createElement('td');
  newColumn2.innerText = `Row ${rowCount}, Column 2`;
  const newColumn3 = document.createElement('td');
  newColumn3.innerText = `Row ${rowCount}, Column 3`;

  newRow.appendChild(newColumn1);
  newRow.appendChild(newColumn2);
  newRow.appendChild(newColumn3);

  return newRow;
}

/**
 * e.currentTarget is the element I registered the event on. 
 * In this case, it will be the table row.
 */
function tryWithManyEventListeners() {
  const tableRowElements = document.querySelectorAll('tr');

  tableRowElements.forEach(row => row.addEventListener('click', (e) => {
    const tableRowElement = e.currentTarget;
    tableRowElement.classList.toggle('clicked');
  }));

  const btnAddNewRow = document.querySelector('button');
  btnAddNewRow.addEventListener('click', () => {
    const newRow = helper_buildNewRow();

    /* POINT #3
      I need this because I'm handling the event
      on each row rather than the table. I don't need this 
      in `tryWithOneEventListener`
    */
    newRow.addEventListener('click', (e) => {
      const tableRowElement = e.currentTarget;
      tableRowElement.classList.toggle('clicked');
    });

    const theTableElement = document.querySelector('table');
    theTableElement.appendChild(newRow);
  });
}

/**
 * e.target is the element the event is currently targeting. 
 * In this case, it will be the table cell that I clicked.
 */
function tryWithOneEventListener() {
  const tableElement = document.querySelector('table');

  tableElement.addEventListener('click', (e) => {
    const tableRowElement = e.target.parentNode;
    tableRowElement.classList.toggle('clicked');
  });

  const btnAddNewRow = document.querySelector('button');
  btnAddNewRow.addEventListener('click', () => {
    const newRow = helper_buildNewRow();

    const theTableElement = document.querySelector('table');
    theTableElement.appendChild(newRow);
  });
}

document.addEventListener('DOMContentLoaded', () => {
  // tryWithManyEventListeners();

  tryWithOneEventListener(); // uncomment this line
});



